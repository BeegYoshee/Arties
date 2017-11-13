package org.dreambot.articron.ui.bot.panels.misc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HCheckSliderText;
import org.dreambot.articron.swing.child.HComboBoxText;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.swing.child.HTextField;

/**
 * Created by: Niklas Date: 18.10.2017 Alias: Dinh Time: 23:02
 */

public class MulePanel extends HPanel {

	private HTextField name, ip, port;
	private HComboBoxText<String> location;

	private HCheckSliderText slider;

	public MulePanel() {
		setLayout(new BorderLayout());
		add(new HLabel("Muling", JLabel.CENTER, new Font("Arial", Font.PLAIN, 17)), BorderLayout.NORTH);
		HPanel grid = new HPanel(new GridLayout(0, 1, 0, 3));
		HPanel main = new HPanel(new BorderLayout());

		slider = new HCheckSliderText("Enable Muling");
		slider.getSlider().getEnabledButton().addActionListener(listener -> {
			toggle(slider.getSlider().isToggledActive());
		});
		slider.getSlider().getDisabledButton().addActionListener(listener -> {
			toggle(!slider.getSlider().isToggledActive());
		});
		grid.add(slider);

		grid.add(location = new HComboBoxText<>("Location: ", new String[] { "MTA Upstairs", "AL-Kharid" }));
		grid.add(name = new HTextField("Mule-Name: ", HFrame.ELEMENT_BG, HFrame.FOREGROUND));
		grid.add(ip = new HTextField("Mule-IP:        ", HFrame.ELEMENT_BG, HFrame.FOREGROUND));
		grid.add(port = new HTextField("Mule-Port:    ", HFrame.ELEMENT_BG, HFrame.FOREGROUND));

		main.add(grid, BorderLayout.CENTER);
		main.add(slider, BorderLayout.NORTH);
		add(main, BorderLayout.CENTER);
		
		TitledBorder border = new TitledBorder(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 1), "Settings");
		border.setTitleColor(HFrame.FOREGROUND);
		border.setTitleFont(new Font("Arial", Font.PLAIN, 15));
		grid.setBorder(border);
		toggle(false);
	}

	private void toggle(boolean status) {
		name.getTextField().setEnabled(status);
		ip.getTextField().setEnabled(status);
		port.getTextField().setEnabled(status);
		location.getComboBox().setEnabled(status);
	}

	public boolean isMulingActive() {
		return slider.getSlider().isToggledActive();
	}

	public String getMuleName() {
		return name.toString();
	}

	public String getMuleIP() {
		return ip.toString();
	}

	public String getMulePort() {
		return port.toString();
	}

	public int getMuleLocation() {
		return location.getComboBox().getSelectedIndex();
	}
}
