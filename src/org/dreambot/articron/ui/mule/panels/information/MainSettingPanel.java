package org.dreambot.articron.ui.mule.panels.information;

import java.awt.GridLayout;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HComboBoxText;
import org.dreambot.articron.swing.child.HTextField;

public class MainSettingPanel extends HPanel {

	private HTextField port;
	private HComboBoxText<String> location;

	public MainSettingPanel() {
		setLayout(new GridLayout(0, 1,0,10));
		add(port = new HTextField("Port number:", HFrame.ELEMENT_BG, HFrame.FOREGROUND));
		add(location = new HComboBoxText<>("Location: ", new String[] { "MTA Upstairs", "AL-Kharid" }));
	}
	
	public int getPort() {
		return Integer.parseInt(port.toString());
	}
	
	public int getMuleLocation() {
		return location.getComboBox().getSelectedIndex();
	}

}
