package org.dreambot.articron.ui.bot.panels.reward;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by: Niklas Date: 19.10.2017 Alias: Dinh Time: 22:06
 */

public class RewardIcon extends BufferedImage {

	public RewardIcon(BufferedImage bufferedImage) {
		super(36, 32, BufferedImage.TYPE_INT_ARGB);
		if (bufferedImage == null) return;
		int x = 18 - bufferedImage.getWidth() / 2;
		int y = 16 - bufferedImage.getHeight() / 2;
		Graphics2D graphics2D = createGraphics();
		graphics2D.drawImage(bufferedImage, x, y, null);
	}

}
