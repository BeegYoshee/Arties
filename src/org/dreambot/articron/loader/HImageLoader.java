package org.dreambot.articron.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * Created by: Niklas Date: 18.09.2017 Alias: Dinh Time: 23:07
 */

public class HImageLoader {
	private static Map<String, BufferedImage> images = new HashMap<>();

	public static ImageIcon getImage(String description) {
		return description != null ? images.containsKey(description)
				? images.get(description) != null ? new ImageIcon(images.get(description)) : loadIconImage(findImage(description))
				: loadIconImage(findImage(description)) : null;
	}

	public static Icon getUIManagerIcon(String name) {
		return UIManager.getIcon(name);
	}

	public static ImageIcon loadIconImage(String URL) {
		if (URL == null) return null;
		else if(images.containsKey(URL))return new ImageIcon(images.get(URL));
		try {
			BufferedImage image = ImageIO.read(new URL(URL));
			images.put(URL, image);
			return new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage loadImage(String URL) {
		if (URL == null) return null;
		else if(images.containsKey(URL))return images.get(URL);
		try {
			BufferedImage image = ImageIO.read(new URL(URL));
			images.put(URL, image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String findImage(String description) {
		for (HImage image : HImage.values()) {
			if (image.getDescription().equals(description))
				return image.getURL();
		}
		return null;
	}
}
