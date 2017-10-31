package org.dreambot.articron.swing;

import org.dreambot.articron.swing.child.HTitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by: Niklas Date: 18.09.2017 Alias: Dinh Time: 23:02
 */

public class HFrame extends JFrame {
	public static final Color BACKGROUND = new Color(0.2f, 0.2f, 0.2f);
	public static final Color FOREGROUND = new Color(240, 240, 240);
	public static final Color LOGO_GREEN = new Color(46, 204, 113);
	public static final Color HOV_GREEN = new Color(46, 220, 113);
	public static final Color SEL_GREEN = new Color(46, 174, 113);
	public static final Color ELEMENT_BG = new Color(60, 60, 60);
	public static final Color LIST_BG = new Color(40, 40, 40);
	public static final Color TITLE_BG = new Color(37, 37, 37);

	protected HPanel contentPane;

	public HFrame(String title) {
		this(title, null);
	}

	public HFrame(String title, BufferedImage icon) {
		setContentPane(contentPane = new HPanel(new BorderLayout()));
		setTitle(title);
		setUndecorated(true);
		if (icon != null) {
			setIconImage(createResizedCopy(icon, 32, 32, true));
		}
		contentPane.add(new HTitleBar(this, title, new ImageIcon(createResizedCopy(icon, 16, 16, true))), BorderLayout.NORTH);
	}

	BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

	protected void prepare(Container parent) {
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

}
