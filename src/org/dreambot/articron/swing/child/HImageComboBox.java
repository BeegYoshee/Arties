package org.dreambot.articron.swing.child;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas Date: 20.09.2017 Alias: Dinh Time: 18:28
 */

public class HImageComboBox<E> extends JComboBox<E> {

	private HComboBoxIconRenderer<E> renderer;
	private HComboBoxIconEditor editor;

	public HImageComboBox(E... e) {
		this(new Font("Arial", Font.PLAIN, 14), 32,e);
	}

	public HImageComboBox(int preferredHeight,E... e) {
		this(new Font("Arial", Font.PLAIN, 14), preferredHeight,e);
	}

	@SuppressWarnings("unchecked")
	public HImageComboBox(Font font, int preferredHeight,E... e) {
		super(e);
		setFont(font);
		setRenderer(renderer = new HComboBoxIconRenderer<>());
		setEditor(editor = new HComboBoxIconEditor(font));
		setUI(new HComboBoxUI());
		setEditable(true);
		setPreferredSize(new Dimension(0, preferredHeight));
	}

	public HComboBoxIconEditor getEditor() {
		return editor;
	}

	public HComboBoxIconRenderer<E> getCustomRenderer() {
		return renderer;
	}
	
	public void clear() {
		final int size = getModel().getSize();
		for (int i = 1; i <= size; i++) {
			E element = getModel().getElementAt(1);
			if(element==null)continue;
			removeItem(element);
		}
	}
}
