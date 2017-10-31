package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.ui.panels.room.DisplayObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HComboBoxIconRenderer<E> extends DefaultListCellRenderer {
	private List<E> elementList = new ArrayList<>();

	@SafeVarargs
	public HComboBoxIconRenderer(E... e) {
		elementList.addAll(Arrays.asList(e));
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel component = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		component.setBackground(isSelected ? HFrame.LOGO_GREEN : HFrame.ELEMENT_BG);
		component.setForeground(HFrame.FOREGROUND);
		DisplayObject object = (DisplayObject) value;
		if (object.getImage() != null)
			component.setIcon(new ImageIcon(object.getImage()));
		return component;
	}
}
