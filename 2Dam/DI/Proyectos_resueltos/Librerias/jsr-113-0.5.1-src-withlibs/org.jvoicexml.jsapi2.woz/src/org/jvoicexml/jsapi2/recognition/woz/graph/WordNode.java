package org.jvoicexml.jsapi2.recognition.woz.graph;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.jvoicexml.jsapi2.recognition.GrammarDefinition;
import org.jvoicexml.processor.srgs.EmptyGrammarNode;
import org.jvoicexml.processor.srgs.GrammarGraph;
import org.jvoicexml.processor.srgs.GrammarNode;
import org.jvoicexml.processor.srgs.SrgsXmlGrammarParser;
import org.jvoicexml.processor.srgs.TokenGrammarNode;
import org.jvoicexml.xml.srgs.SrgsXmlDocument;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WordNode {
	
	static private SrgsXmlGrammarParser srgsParser = new SrgsXmlGrammarParser();
	
	public boolean isFinal;
	public String word;
	public String grammarName;
	private boolean isSimplified;

	private CopyOnWriteArrayList<WordNode> successors;
	private CopyOnWriteArrayList<WordNode> predecessors;

	public WordNode() {
		super();
		isFinal = false;
		isSimplified = false;
		word = "";
		grammarName = "";
		successors = new CopyOnWriteArrayList<WordNode>();
		predecessors = new CopyOnWriteArrayList<WordNode>();
	}
	
	public WordNode(boolean isFinal, String word) {
		this();
		this.isFinal = isFinal;
		this.word = word;
	}

	public CopyOnWriteArrayList<WordNode> getSuccessors() {
		return successors;
	}

	public void setSuccessors(CopyOnWriteArrayList<WordNode> successors) {
		this.successors = successors;
	}

    public void addSuccessor(WordNode wordNode) {
    	successors.add(wordNode);
    	isSimplified = false;
    	if (!wordNode.predecessors.contains(this))
    		wordNode.predecessors.add(this);
	}

    public void removeSuccessor(WordNode wordNode) {
    	successors.remove(wordNode);
    	isSimplified = false;
    	if (wordNode.predecessors.contains(this))
    		wordNode.predecessors.remove(this);
	}

	public CopyOnWriteArrayList<WordNode> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(CopyOnWriteArrayList<WordNode> predecessors) {
		this.predecessors = predecessors;
	}

	public static WordNode buildWordGraph(GrammarDefinition grammarDef) {
		WordNode startNode = null;
		try {
			GrammarGraph gGraph = srgsParser.parse(new SrgsXmlDocument(new InputSource(new StringReader(grammarDef.getGrammar()))));
			startNode = new WordNode();
			buildWordGraph(gGraph, startNode, new HashMap<GrammarNode, WordNode>());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return startNode;
	}
		
	private static void buildWordGraph(GrammarNode grammarNode, WordNode word, HashMap<GrammarNode, WordNode> visited) {
		if (grammarNode == null)
			return;
		
		if (!GrammarGraph.class.isAssignableFrom(grammarNode.getClass()) && grammarNode.isFinalNode()) {
			word.isFinal = true;
			return;
		}
			
		
		if (grammarNode.getClass().equals(TokenGrammarNode.class)) {
			TokenGrammarNode token = (TokenGrammarNode)grammarNode;

			if (visited.containsKey(token)) {
				word.addSuccessor(visited.get(token));
				return;
			}
			
			WordNode newWord = new WordNode(token.isFinalNode(), token.getToken());
			visited.put(token, newWord);
			word.addSuccessor(newWord);
			word = newWord;
		}

		if (EmptyGrammarNode.class.isAssignableFrom(grammarNode.getClass())) {
			for(GrammarNode childNode : grammarNode.getNextNodes()) {
				buildWordGraph(childNode, word, visited);
			}
		}
		if (GrammarGraph.class.isAssignableFrom(grammarNode.getClass())) {
			GrammarGraph grammarGraph = (GrammarGraph)grammarNode;
			buildWordGraph(grammarGraph.getStartNode(), word, visited);
		}
	}
	
	@Override
	public String toString() {
		return word + " ";
	}

	public void dump(OutputStream out, Collection visited, String prefix) throws IOException {
        if (visited.contains(this))
            return;
        visited.add(this);
        
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(this.toString());
        out.write(sb.toString().getBytes());
        prefix += "  ";
        int i = 0;
        for(WordNode child : successors) {
            out.write(("\nnextNode(" + i++ + "):\t").getBytes());
            child.dump(out, visited, prefix);
        }
    }
	
	public void simplify() {
		if (isSimplified)
			return;
		
		// intermittent node with no word - remove from graph
		if (word.isEmpty() && predecessors.size() > 0) {
			for (WordNode predecessor : predecessors) {
				for (WordNode successor : successors) {
					removeSuccessor(successor);
					predecessor.addSuccessor(successor);
				}
				predecessor.removeSuccessor(this);
			}
		}
		
		// check whether we have redundant childs in a node
		for (WordNode childRef : successors) {
			for (WordNode childCmp : successors) {
				if (childRef == childCmp)
					continue;
				if (childRef.word.equals(childCmp.word)) {
					for (WordNode redundantChild : childCmp.successors) {
						childRef.addSuccessor(redundantChild);
					}
					removeSuccessor(childCmp);
				}
			}
		}
		
		isSimplified = true;
		for (WordNode child : successors) {
			child.simplify();
		}
	}
}
