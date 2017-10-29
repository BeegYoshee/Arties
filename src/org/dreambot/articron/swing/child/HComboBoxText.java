package org.dreambot.articron.swing.child;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import org.dreambot.articron.swing.HPanel;

/**
 * Created by: Niklas Date: 16.10.2017 Alias: Dinh Time: 23:25
 */

public class HComboBoxText<E> extends HPanel {
	private HComboBox<E> comboBox;

	public HComboBoxText(String text, E[] elements) {
		this(text, elements, new Font("Arial", Font.PLAIN, 14), 16);
	}

	public HComboBoxText(String text, E[] elements, int preferredHeight) {
		this(text, elements, new Font("Arial", Font.PLAIN, 14), preferredHeight);
	}

	public HComboBoxText(String text, E[] elements, Font font, int preferredHeight) {
		setLayout(new BorderLayout());
		HLabel label = new HLabel(text);
		label.setPreferredSize(new Dimension(70, 0));
		add(label, BorderLayout.WEST);
		add(comboBox = new HComboBox<>(elements, font, preferredHeight), BorderLayout.CENTER);

	}

	public HComboBox<E> getComboBox() {
		return comboBox;
	}
}
