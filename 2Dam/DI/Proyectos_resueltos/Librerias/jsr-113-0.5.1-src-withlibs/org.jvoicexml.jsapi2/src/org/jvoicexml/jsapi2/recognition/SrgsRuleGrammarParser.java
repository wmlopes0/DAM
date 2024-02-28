/*
 * File:    $HeadURL: https://svn.sourceforge.net/svnroot/jvoicexml/trunk/src/org/jvoicexml/Application.java$
 * Version: $LastChangedRevision: 68 $
 * Date:    $LastChangedDate $
 * Author:  $LastChangedBy: schnelle $
 *
 * JSAPI - An independent reference implementation of JSR 113.
 *
 * Copyright (C) 2007-2012 JVoiceXML group - http://jvoicexml.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.jvoicexml.jsapi2.recognition;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import javax.speech.recognition.Rule;
import javax.speech.recognition.RuleAlternatives;
import javax.speech.recognition.RuleComponent;
import javax.speech.recognition.RuleCount;
import javax.speech.recognition.RuleReference;
import javax.speech.recognition.RuleSequence;
import javax.speech.recognition.RuleSpecial;
import javax.speech.recognition.RuleTag;
import javax.speech.recognition.RuleToken;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: INESC-ID L2F</p>
 *
 * @author Renato Cassaca
 * @version 1.0
 */
public class SrgsRuleGrammarParser {

    private XPath xpath;
    private static EntityResolver entityResolver = new EmptyEntityResolver();
    private Map<String, String> attributes;

    public static class EmptyEntityResolver implements EntityResolver {
        @Override
        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException, IOException {
            return new InputSource(new StringReader(""));
        }
    }

    public SrgsRuleGrammarParser() {
        // Create a new XPath
        XPathFactory factory = XPathFactory.newInstance();

        xpath = factory.newXPath();
        attributes = new java.util.HashMap<String,String>();
    }

    public Rule[] load(final Reader reader) {
        final InputSource source = new InputSource(reader);
        return load(source);
    }

    public Rule[] load(final InputStream stream) {
        final InputSource source = new InputSource(stream);
        return load(source);
    }

    public Rule[] loadRule(final Reader reader) {
        try{
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().
                                      newDocumentBuilder();
            builder.setEntityResolver(entityResolver);
            final InputSource source = new InputSource(reader);
            return parseGrammar(builder.parse(source));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Rule[] loadRule(InputStream stream){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().
                                      newDocumentBuilder();
            builder.setEntityResolver(entityResolver);
            return parseGrammar(builder.parse(new InputSource(stream)));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private Rule[] load(final InputSource inputSource) {
        try {
            final DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(entityResolver);

            final Node grammarNode = (Node) xpath.evaluate("/grammar",
                    builder.parse(inputSource), XPathConstants.NODE);
            final Rule[] rules = parseGrammar(grammarNode);

            //Extract header from grammar
            final NamedNodeMap docAttributes = grammarNode.getAttributes();
            for (int i = 0; i < docAttributes.getLength(); i++) {
                attributes.put(docAttributes.item(i).getNodeName(),
                               docAttributes.item(i).getNodeValue());
            }

            return rules;
        } catch (XPathExpressionException ex2) {
            ex2.printStackTrace();
            return null;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (SAXException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Rule[] parseGrammar(Node grammarNode) {
        ArrayList<Rule> rules = new ArrayList<Rule>();

//        try{
//            String root = xpath.evaluate("@root", grammarNode);
//            attributes.remove("root");
//            attributes.put("root", root);
//            System.out.println("new root : "+ root);
//            
//        }catch (XPathExpressionException ex) {
//            ex.printStackTrace();
//        }
        
        
        try {
            NodeList ruleNodes = (NodeList) xpath.evaluate("rule", grammarNode,
                    XPathConstants.NODESET);
            for (int i = 0; i < ruleNodes.getLength(); i++) {
                Node ruleNode = ruleNodes.item(i);
                String ruleId = xpath.evaluate("@id", ruleNode);
                int scope = Rule.PRIVATE;
                String scopeStr = xpath.evaluate("@scope", ruleNode);
                if (scopeStr != "") {
                    if (scopeStr.equalsIgnoreCase("public"))
                        scope = Rule.PUBLIC;
                }

                ArrayList<RuleComponent> rcs = evalChildNodes(ruleNode);
                if (rcs.size() == 1) {
                    Rule rule = new Rule(ruleId, rcs.get(0), scope);
                    rules.add(rule);
                } else if (rcs.size() > 1) {
                    RuleSequence rs = new RuleSequence(rcs.toArray(new
                            RuleComponent[] {}));
                    Rule rule = new Rule(ruleId, rs, scope);
                    rules.add(rule);
                }
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return rules.toArray(new Rule[] {});
    }


    private ArrayList<RuleComponent> evalNode(final Node node) throws
            XPathExpressionException {
        final ArrayList<RuleComponent> ruleComponents = new ArrayList<RuleComponent>();
        final String nodeName = node.getNodeName();
        if (nodeName.equalsIgnoreCase("#text")) {
            final Text textNode = (Text) node;
            final String text = textNode.getWholeText().trim();
            if (text.length() > 0) {
                RuleToken ruleToken = new RuleToken(text);
                ruleComponents.add(ruleToken);
            }
        } else if (nodeName.equalsIgnoreCase("one-of")) {
            ArrayList<RuleComponent> rcs = evalChildNodes(node);
            RuleAlternatives ra = new RuleAlternatives(rcs.toArray(new
                    RuleComponent[] {}));
            ruleComponents.add(ra);
        } else if (nodeName.equalsIgnoreCase("item")) {
            int repeatMin = -1;
            int repeatMax = -1;
            double repeatProb = -1;
            try {
                String repeatStr = xpath.evaluate("@repeat", node);
                String repeatProbStr = xpath.evaluate("@repeat-prob", node);

                if (repeatStr.length() != 0) {
                    int toIndex = repeatStr.indexOf('-');
                    if (toIndex < 0) {
                        repeatMin = Integer.parseInt(repeatStr);
                        repeatMax = repeatMin;
                    } else {
                         String minStr = repeatStr.substring(0, toIndex);
                         String maxStr = repeatStr.substring(toIndex + 1);
                         if (minStr.trim().length() > 0) {
                             repeatMin = Integer.parseInt(minStr);
                         }
                         if (maxStr.trim().length() > 0) {
                                repeatMax = Integer.parseInt(maxStr);
                         }
                    }
                }

                if (repeatProbStr.length() != 0) {
                    repeatProb = Double.parseDouble(repeatProbStr);
                }

            } catch (XPathExpressionException ex) {
                ex.printStackTrace();
            }

            ArrayList<RuleComponent> rcs = evalChildNodes(node);
            RuleSequence rs = new RuleSequence(rcs.toArray(new RuleComponent[] {}));

            if ((repeatMin != -1) && (repeatMax != -1) && (repeatProb != -1)) {
                RuleCount rc = new RuleCount(rs, repeatMin, repeatMax, (int)(repeatProb*RuleCount.MAX_PROBABILITY));
                ruleComponents.add(rc);
            }
            else if ((repeatMin != -1) && (repeatMax != -1)) {
                RuleCount rc = new RuleCount(rs, repeatMin, repeatMax);
                ruleComponents.add(rc);
            }
            else if (repeatMin != -1) {
                if (repeatProb != -1){
                    RuleCount rc = new RuleCount(rs, repeatMin, RuleCount.REPEAT_INDEFINITELY, (int)(repeatProb*RuleCount.MAX_PROBABILITY));
                    ruleComponents.add(rc);
                } else {
                    RuleCount rc = new RuleCount(rs, repeatMin);
                    ruleComponents.add(rc);
                }
            }
            else {
                ruleComponents.add(rs);
            }

        } else if (nodeName.equalsIgnoreCase("ruleref")) {
            String specialStr = (String) xpath.evaluate("@special", node);
            if (specialStr != "") {
                if (specialStr.equalsIgnoreCase("NULL")) {
                    ruleComponents.add(RuleSpecial.NULL);
                } else if (specialStr.equalsIgnoreCase("VOID")) {
                    ruleComponents.add(RuleSpecial.VOID);
                } else if (specialStr.equalsIgnoreCase("GARBAGE")) {
                    ruleComponents.add(RuleSpecial.GARBAGE);
                }
            } else {
                final String uriStr = (String) xpath.evaluate("@uri", node).trim();
                if (uriStr.indexOf("#") == -1) {
                    final RuleReference reference = new RuleReference(uriStr);
                    ruleComponents.add(reference);
                } else {
                    final String ruleName =
                        uriStr.substring(uriStr.indexOf("#") + 1).trim();;
                    final String grammarName = uriStr.substring(0, uriStr.indexOf("#"));
                    final String typeStr = (String) xpath.evaluate("@type", node).trim();
                    if (grammarName.isEmpty()) {
                        final RuleReference reference = new RuleReference(
                                ruleName);
                        ruleComponents.add(reference);
                    } else if (typeStr.isEmpty()) {
                        final RuleReference reference = new RuleReference(
                                grammarName, ruleName);
                        ruleComponents.add(reference);
                    } else {
                        final RuleReference reference = new RuleReference(
                                grammarName, typeStr);
                        ruleComponents.add(reference);
                    }
                }
            }
        } else if (nodeName.equalsIgnoreCase("token")) {
            String tokenText = (String) xpath.evaluate(".", node,
                    XPathConstants.STRING);
            RuleToken ruleToken = new RuleToken(tokenText);
            ruleComponents.add(ruleToken);
        } else if (nodeName.equalsIgnoreCase("tag")) {
            Object tagObject = xpath.evaluate(".", node, XPathConstants.STRING);
            RuleTag ruleTag = new RuleTag(tagObject);
            ruleComponents.add(ruleTag);
        } else if (nodeName.equalsIgnoreCase("example")) {
            //Ignore
        }

        return ruleComponents;
    }

    private ArrayList<RuleComponent> evalChildNodes(Node nodes) throws
            XPathExpressionException {
        ArrayList<RuleComponent> ruleComponents = new ArrayList<RuleComponent>();
        NodeList children = (NodeList) xpath.evaluate("child::node()", nodes,
                XPathConstants.NODESET);
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            final ArrayList<RuleComponent> components = evalNode(child);
            ruleComponents.addAll(components);
        }

        return ruleComponents;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
