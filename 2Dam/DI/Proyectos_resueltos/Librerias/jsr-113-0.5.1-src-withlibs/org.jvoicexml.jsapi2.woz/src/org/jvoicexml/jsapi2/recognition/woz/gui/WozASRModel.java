package org.jvoicexml.jsapi2.recognition.woz.gui;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.speech.recognition.GrammarException;
import javax.speech.recognition.ResultEvent;
import javax.speech.recognition.ResultToken;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.jvoicexml.jsapi2.recognition.BaseResult;
import org.jvoicexml.jsapi2.recognition.BaseResultToken;
import org.jvoicexml.jsapi2.recognition.BaseRuleGrammar;
import org.jvoicexml.jsapi2.recognition.woz.WozRecognizer;
import org.jvoicexml.jsapi2.recognition.woz.graph.WordNode;

public class WozASRModel implements MutableComboBoxModel {

	private WordNode wordCursor;
	private WordNode selected;
	private WozRecognizer recognizer;
	private CopyOnWriteArrayList<ListDataListener> observers;
	private ArrayList<WordNode> nodeStack;;
	private JTextField resultTextField;
	private JLabel statusLabel;
	
	public WozASRModel() {
		this.observers = new CopyOnWriteArrayList<ListDataListener>();
		this.nodeStack = new ArrayList<WordNode>();
	}

	public void undo() {
		if (nodeStack.size() < 2)
			return;
		WordNode lastNode = nodeStack.remove(nodeStack.size() - 1);
		statusLabel.setText("undo ");
		resultTextField.setText(resultTextField.getText().substring(0, resultTextField.getText().length() - (lastNode.word.length() + 1)));
		updateWordGraph(nodeStack.get(nodeStack.size() - 1));
	}

	public void select() {
		nodeStack.add(selected);
		statusLabel.setText("selected ");
		resultTextField.setText(resultTextField.getText() + selected.word + " ");
		if (selected.isFinal) {
			statusLabel.setText("sending final result. ");
			sendResult();
		}
		updateWordGraph(selected);
	}

	private void sendResult() {
		BaseResult currentResult = null;
		try {
			currentResult = new BaseResult(new BaseRuleGrammar(recognizer, null));
		} catch (GrammarException e) {
			e.printStackTrace();
		}

        currentResult.setResultState(BaseResult.ACCEPTED);
        currentResult.setNumTokens(nodeStack.size() - 1);
        
        final ResultToken[] rt = new ResultToken[currentResult.getNumTokens()];
        for (int i = 0; i < currentResult.getNumTokens(); ++i) {
            WordNode tok = nodeStack.get(i + 1);
            rt[i] = new BaseResultToken(currentResult, tok.word);
        }
        currentResult.setTokens(rt);

        final ResultEvent accepted = new ResultEvent(currentResult,
                ResultEvent.RESULT_ACCEPTED, false, false);
        recognizer.postResultEvent(accepted);
		
	}

	private void updateWordGraph(WordNode wordGraph) {
		int oldSize  = 0;
		if (wordCursor != null)
			oldSize = wordCursor.getSuccessors().size();
		
		this.wordCursor = wordGraph;
		
		if (wordCursor.getSuccessors().size() > 0)
			setSelectedItem(wordCursor.getSuccessors().get(0));
		
		synchronized(observers) {
			for(ListDataListener observer : observers) {
				observer.intervalRemoved(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, oldSize));
				observer.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, 0, wordCursor.getSuccessors().size()));
			}
		}	
	}
	
	public void setWordGraph(WordNode wordGraph) {
		nodeStack.clear();
		wordCursor = null;
		selected = null;
		resultTextField.setText("");
		statusLabel.setText("New WordGraph ");
		nodeStack.add(wordGraph);
		updateWordGraph(wordGraph);
	}


	// simple getter / setter
	
	public JTextField getResultTextField() {
		return resultTextField;
	}

	public void setResultTextField(JTextField resultTextField) {
		this.resultTextField = resultTextField;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public void setRecognizer(WozRecognizer recognizer) {
		this.recognizer = recognizer;
	}

	// MutableComboBoxModel interface below
	
	@Override
	public void setSelectedItem(Object anItem) {
		selected = (WordNode)anItem;
	}

	@Override
	public Object getSelectedItem() {
//		System.out.println("getSelectedItem");
		return selected;
	}

	@Override
	public int getSize() {
//		System.out.println("getSize");
		if (wordCursor == null)
			return 0;
		return wordCursor.getSuccessors().size();
	}

	@Override
	public Object getElementAt(int index) {
//		System.out.println("getElementAt");
		return wordCursor.getSuccessors().get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		synchronized(observers) {
			if (!observers.contains(l)) {
				observers.add(l);
			}
		}
	}
	
	@Override
	public void removeListDataListener(ListDataListener l) {
		synchronized(observers) {
			if (observers.contains(l)) {
				observers.remove(l);
			}
		}
	}

	@Override
	public void addElement(Object obj) {
		throw new RuntimeException("Setting items takes place by adding grammars");
	}

	@Override
	public void removeElement(Object obj) {
		throw new RuntimeException("Setting items takes place by adding grammars");
	}

	@Override
	public void insertElementAt(Object obj, int index) {
		throw new RuntimeException("Setting items takes place by adding grammars");
	}

	@Override
	public void removeElementAt(int index) {
		throw new RuntimeException("Setting items takes place by adding grammars");
	}

}
