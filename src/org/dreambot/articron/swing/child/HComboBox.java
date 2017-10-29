package org.dreambot.articron.swing.child;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComboBox;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 18:28
 */

public class HComboBox<E> extends JComboBox<E> {

    private Font font;

    public HComboBox(E[] elements) {
        this(elements, new Font("Arial", Font.PLAIN, 14), 16);
    }

    public HComboBox(E[] elements, int preferredHeight) {
        this(elements, new Font("Arial", Font.PLAIN, 14), preferredHeight);
    }

    public HComboBox(E[] elements, Font font, int preferredHeight) {
        super(elements);
        this.font = font;
        setRenderer(new HComboBoxRenderer(font));
        setEditor(new HComboBoxEditor(font));
        setUI(new HComboBoxUI());
        setEditable(true);
        setPreferredSize(new Dimension(determine(elements) + preferredHeight, preferredHeight));

    }

    private int determine(E[] elements) {
        Graphics graphics = new BufferedImage(1, 1, 1).createGraphics();
        graphics.setFont(font);

        int width = 0, calculatedWidth;
        FontMetrics metrics = graphics.getFontMetrics();
        for (E e : elements) {
            if ((calculatedWidth = metrics.stringWidth(String.valueOf(e))) > width) width = calculatedWidth;
        }
        return width + 6;
    }
}
