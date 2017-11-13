package org.dreambot.articron.ui.mule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.ui.bot.MainUI;
import org.dreambot.articron.ui.mule.panels.information.MainInfoPanel;
import org.dreambot.articron.ui.mule.panels.reward.MuleRunePanel;

public class MuleUI extends HFrame {

	private ScriptContext context;

	private MuleRunePanel rewardPanel;

	public MuleUI(MainUI mainUI, String text, BufferedImage icon, ScriptContext context) {
		super(text, icon);
		this.context = context;
		contentPane.setPreferredSize(new Dimension(500, 250));

		add(rewardPanel = new MuleRunePanel(BorderFactory.createLineBorder(HFrame.ELEMENT_BG, 2)), BorderLayout.EAST);
		rewardPanel.setPreferredSize(new Dimension(170, 0));

		add(new MainInfoPanel(this, rewardPanel), BorderLayout.CENTER);
		prepare(null);
	}

	public static void main(String[] args) {
		new MuleUI(null, "ArtiMTA PRO s", HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"), null);
	}

	public ScriptContext getContext() {
		return context;
	}
}
