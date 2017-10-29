package org.dreambot.articron.swing.child;

import javax.swing.JTextArea;

import org.dreambot.articron.swing.HFrame;

public class HTextArea extends JTextArea{
	
	public HTextArea(String text) {
		setBackground(HFrame.ELEMENT_BG);
		setForeground(HFrame.FOREGROUND);
		setEditable(false);
		setLineWrap(true);
		setText(text);
	}

}
