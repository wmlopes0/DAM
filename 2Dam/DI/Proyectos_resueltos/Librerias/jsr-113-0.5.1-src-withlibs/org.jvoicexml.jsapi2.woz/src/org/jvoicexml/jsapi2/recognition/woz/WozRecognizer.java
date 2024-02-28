package org.jvoicexml.jsapi2.recognition.woz;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.speech.EngineStateException;
import javax.speech.recognition.ResultEvent;

import org.jvoicexml.jsapi2.jse.recognition.JseBaseRecognizer;
import org.jvoicexml.jsapi2.recognition.GrammarDefinition;
import org.jvoicexml.jsapi2.recognition.woz.graph.WordNode;
import org.jvoicexml.jsapi2.recognition.woz.gui.WozGUI;

public class WozRecognizer extends JseBaseRecognizer {

	private static final Logger LOGGER = Logger.getLogger(WozRecognizer.class
			.getName());
	private WozGUI gui;
	private WordNode wordGraph;
	private CopyOnWriteArrayList<GrammarDefinition> grammarDefs;
	
	public WozRecognizer(WozRecognizerMode wozRecognizerMode) {
        super(wozRecognizerMode);
		grammarDefs = new CopyOnWriteArrayList<GrammarDefinition>();
		wordGraph = new WordNode();
		gui = new WozGUI();
		gui.setVisible(true);
	}

	@Override
	protected void handleAllocate() throws EngineStateException,
			EngineException, AudioException, SecurityException {
		System.err.println("handleAllocate");
	}

	@Override
	protected void handleDeallocate() {
		System.err.println("handleDeallocate");
	}

	@Override
	protected void handlePause() {
		System.err.println("handlePause");
	}

	@Override
	protected void handlePause(int flags) {
		handlePause();
	}

	@Override
	protected boolean handleResume() throws EngineStateException {
		System.err.println("handleResume");
		return false;
	}

	@Override
	protected boolean setGrammars(Vector grammarDefinition) {
		// is there a new grammar?
		boolean hasNewGrammar = false;
		for(Object obj: grammarDefinition) {
			GrammarDefinition newGrammar = (GrammarDefinition)obj;
			boolean addGrammar = true;
			for(GrammarDefinition exGrammar : grammarDefs) {
				if (exGrammar.getName().equals(newGrammar.getName())) {
					addGrammar = false;;
				}
			}
			if (addGrammar) {
				grammarDefs.add(newGrammar);
				hasNewGrammar = true;
			}
		}
		// was an existing grammar deleted?
		boolean delExGrammar = false;
		for(GrammarDefinition exGrammar : grammarDefs) {
			boolean keepGrammar = false;
			for(Object obj: grammarDefinition) {
				GrammarDefinition newGrammar = (GrammarDefinition)obj;
				if (exGrammar.getName().equals(newGrammar.getName())) {
					keepGrammar = true;
				}
			}
			if (!keepGrammar) {
				grammarDefs.remove(exGrammar);
				delExGrammar = true;
			}
		}
		System.err.print("Current Grammars: ");
		for(GrammarDefinition grammar : grammarDefs) {
			System.err.println(grammar.getName() + " ");
		}
		System.err.println();
		if (hasNewGrammar || delExGrammar) {
			commitChanges();
		}
		return hasNewGrammar || delExGrammar;
	}

	private void commitChanges() {
		// rebuild the WordGraph
		wordGraph.getSuccessors().clear();
		for(GrammarDefinition grammarDef : grammarDefs) {
			wordGraph.addSuccessor(WordNode.buildWordGraph(grammarDef));
		}
		wordGraph.simplify();
		gui.getAsrModel().setWordGraph(wordGraph);
		gui.getAsrModel().setRecognizer(this);
		gui.getComboBox().setPopupVisible(true);
		gui.getComboBox().requestFocus();
		
	}

    public void postResultEvent(final ResultEvent resultEvent) {
        super.postResultEvent(resultEvent);
    }

	@Override
	protected void handleRequestFocus() {
		System.err.println("handleRequestFocus");
	}

	@Override
	protected void handleReleaseFocus() {
		System.err.println("handleReleaseFocus");
	}

	@Override
	public Vector getBuiltInGrammars() {
		return null;
	}
}
