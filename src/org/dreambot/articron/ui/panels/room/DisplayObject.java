package org.dreambot.articron.ui.panels.room;

import java.awt.image.BufferedImage;

public class DisplayObject {
	private BufferedImage image;
	private String text;

	public DisplayObject(String text, BufferedImage image) {
		this.text = text;
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	@Override
	public String toString() {
		return text;
	}
}
