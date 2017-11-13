package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas Date: 18.10.2017 Alias: Dinh Time: 23:30
 */

public class HTextField extends HPanel {

	private JTextField textField;

	public HTextField(String info, Color background, Color foreground) {
		super(new BorderLayout());
		add(new HLabel(info), BorderLayout.WEST);

		textField = new JTextField();
		textField.setBorder(null);
		textField.setForeground(foreground);
		textField.setBackground(background);
		add(textField, BorderLayout.CENTER);
		textField.setBorder(
				BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 5)));
	}
	
	public JTextField getTextField() {
		return textField;
	}

	@Override
	public String toString() {
		return textField.getText();
	}
}
