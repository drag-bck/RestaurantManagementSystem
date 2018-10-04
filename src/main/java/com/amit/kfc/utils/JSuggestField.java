package com.amit.kfc.utils;

import ch.rakudave.suggest.SuggestMatcher;
import ch.rakudave.suggest.matcher.ContainsMatcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class JSuggestField<T> extends JTextField {
	private JDialog d;
	private Point location;
	private JList<T> list;
	private Vector<T> data;
	private Vector<T> suggestions;
	private InterruptableMatcher matcher;
	private Font busy;
	private Font regular;
	private String lastWord;
	private String lastChosenExistingVariable;
	private String hint;
	private LinkedList<ActionListener> listeners;
	private SuggestMatcher suggestMatcher;
	private boolean caseSensitive;
	
	public JSuggestField(Frame owner) {
		this.lastWord = "";
		this.suggestMatcher = new ContainsMatcher();
		this.caseSensitive = false;
		this.data = new Vector<>();
		this.suggestions = new Vector<>();
		this.listeners = new LinkedList<>();
		owner.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
				JSuggestField.this.relocate();
			}
			
			public void componentResized(ComponentEvent e) {
				JSuggestField.this.relocate();
			}
			
			public void componentMoved(ComponentEvent e) {
				JSuggestField.this.relocate();
			}
			
			public void componentHidden(ComponentEvent e) {
				JSuggestField.this.relocate();
			}
		});
		owner.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}
			
			public void windowIconified(WindowEvent e) {
				JSuggestField.this.d.setVisible(false);
			}
			
			public void windowDeiconified(WindowEvent e) {
			}
			
			public void windowDeactivated(WindowEvent e) {
			}
			
			public void windowClosing(WindowEvent e) {
				JSuggestField.this.d.dispose();
			}
			
			public void windowClosed(WindowEvent e) {
				JSuggestField.this.d.dispose();
			}
			
			public void windowActivated(WindowEvent e) {
			}
		});
		this.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				JSuggestField.this.d.setVisible(false);
				if (JSuggestField.this.getText().equals("") && e.getOppositeComponent() != null && e.getOppositeComponent().getName() != null) {
					if (!e.getOppositeComponent().getName().equals("suggestFieldDropdownButton")) {
						JSuggestField.this.setText(JSuggestField.this.hint);
					}
				} else if (JSuggestField.this.getText().equals("")) {
					JSuggestField.this.setText(JSuggestField.this.hint);
				}
				
			}
			
			public void focusGained(FocusEvent e) {
				if (JSuggestField.this.getText().equals(JSuggestField.this.hint)) {
					JSuggestField.this.setText("");
				}
				
				JSuggestField.this.showSuggest();
			}
		});
		this.d = new JDialog(owner);
		this.d.setUndecorated(true);
		this.d.setFocusableWindowState(false);
		this.d.setFocusable(false);
		this.list = new JList<>();
		this.list.addMouseListener(new MouseListener() {
			private int selected;
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseReleased(MouseEvent e) {
				if (this.selected == JSuggestField.this.list.getSelectedIndex()) {
					JSuggestField.this.setText((String) JSuggestField.this.list.getSelectedValue());
					JSuggestField.this.lastChosenExistingVariable = JSuggestField.this.list.getSelectedValue().toString();
					JSuggestField.this.fireActionEvent();
					JSuggestField.this.d.setVisible(false);
				}
				
				this.selected = JSuggestField.this.list.getSelectedIndex();
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
			public void mouseClicked(MouseEvent e) {
			}
		});
		this.d.add(new JScrollPane(this.list, 20, 31));
		this.d.pack();
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				JSuggestField.this.relocate();
			}
			
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 27) {
					JSuggestField.this.d.setVisible(false);
				} else {
					if (e.getKeyCode() == 40) {
						if (JSuggestField.this.d.isVisible()) {
							JSuggestField.this.list.setSelectedIndex(JSuggestField.this.list.getSelectedIndex() + 1);
							JSuggestField.this.list.ensureIndexIsVisible(JSuggestField.this.list.getSelectedIndex() + 1);
							return;
						}
						
						JSuggestField.this.showSuggest();
					} else {
						if (e.getKeyCode() == 38) {
							JSuggestField.this.list.setSelectedIndex(JSuggestField.this.list.getSelectedIndex() - 1);
							JSuggestField.this.list.ensureIndexIsVisible(JSuggestField.this.list.getSelectedIndex() - 1);
							return;
						}
						
						if (e.getKeyCode() == 10 & JSuggestField.this.list.getSelectedIndex() != -1 & JSuggestField.this.suggestions.size() > 0) {
							JSuggestField.this.setText((String) JSuggestField.this.list.getSelectedValue());
							JSuggestField.this.lastChosenExistingVariable = JSuggestField.this.list.getSelectedValue().toString();
							JSuggestField.this.fireActionEvent();
							JSuggestField.this.d.setVisible(false);
							return;
						}
					}
					
					JSuggestField.this.showSuggest();
				}
			}
		});
		this.regular = this.getFont();
		this.busy = new Font(this.getFont().getName(), 2, this.getFont().getSize());
	}
	
	T getSelectedValue() {
		return this.list.getSelectedValue();
	}
	
	int getSelectedIndex() {
		return this.list.getSelectedIndex();
	}
	
	public JSuggestField(Frame owner, Vector<T> data) {
		this(owner);
		this.setSuggestData(data);
	}
	
	public boolean setSuggestData(Vector<T> data) {
		if (data == null) {
			return false;
		} else {
			data.sort(Comparator.comparing(Object::toString));
			this.data = data;
			this.list.setListData(data);
			return true;
		}
	}
	
	public Vector<T> getSuggestData() {
		return (Vector<T>) this.data.clone();
	}
	
	public void setPreferredSuggestSize(Dimension size) {
		this.d.setPreferredSize(size);
	}
	
	public void setMinimumSuggestSize(Dimension size) {
		this.d.setMinimumSize(size);
	}
	
	public void setMaximumSuggestSize(Dimension size) {
		this.d.setMaximumSize(size);
	}
	
	public void showSuggest() {
		if (!this.getText().toLowerCase().contains(this.lastWord.toLowerCase())) {
			this.suggestions.clear();
		}
		
		if (this.suggestions.isEmpty()) {
			this.suggestions.addAll(this.data);
		}
		
		if (this.matcher != null) {
			this.matcher.stop = true;
		}
		
		this.matcher = new InterruptableMatcher();
		SwingUtilities.invokeLater(this.matcher);
		this.lastWord = this.getText();
		this.relocate();
	}
	
	public void hideSuggest() {
		this.d.setVisible(false);
	}
	
	public boolean isSuggestVisible() {
		return this.d.isVisible();
	}
	
	private void relocate() {
		try {
			this.location = this.getLocationOnScreen();
			this.location.y += this.getHeight();
			this.d.setLocation(this.location);
		} catch (IllegalComponentStateException var2) {
		}
	}
	
	public void addSelectionListener(ActionListener listener) {
		if (listener != null) {
			this.listeners.add(listener);
		}
		
	}
	
	public void removeSelectionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}
	
	private void fireActionEvent() {
		ActionEvent event = new ActionEvent(this, 0, this.getText());
		Iterator var3 = this.listeners.iterator();
		
		while (var3.hasNext()) {
			ActionListener listener = (ActionListener) var3.next();
			listener.actionPerformed(event);
		}
		
	}
	
	public String getLastChosenExistingVariable() {
		return this.lastChosenExistingVariable;
	}
	
	public String getHint() {
		return this.hint;
	}
	
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	public void setSuggestMatcher(SuggestMatcher suggestMatcher) {
		this.suggestMatcher = suggestMatcher;
	}
	
	public boolean isCaseSensitive() {
		return this.caseSensitive;
	}
	
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	
	private class InterruptableMatcher extends Thread {
		private volatile boolean stop;
		
		private InterruptableMatcher() {
		}
		
		public void run() {
			try {
				JSuggestField.this.setFont(JSuggestField.this.busy);
				Iterator<T> it = JSuggestField.this.suggestions.iterator();
				String word = JSuggestField.this.getText();
				
				while (it.hasNext()) {
					if (this.stop) {
						return;
					}
					
					if (JSuggestField.this.caseSensitive) {
						if (!JSuggestField.this.suggestMatcher.matches((String) it.next(), word)) {
							it.remove();
						}
					} else if (!JSuggestField.this.suggestMatcher.matches(it.next().toString().toLowerCase(), word.toLowerCase())) {
						it.remove();
					}
				}
				
				JSuggestField.this.setFont(JSuggestField.this.regular);
				if (JSuggestField.this.suggestions.size() > 0) {
					JSuggestField.this.list.setListData(JSuggestField.this.suggestions);
					JSuggestField.this.list.setSelectedIndex(0);
					JSuggestField.this.list.ensureIndexIsVisible(0);
					JSuggestField.this.d.setVisible(true);
				} else {
					JSuggestField.this.d.setVisible(false);
				}
				
			} catch (Exception var3) {
				var3.printStackTrace();
			}
		}
	}
}
