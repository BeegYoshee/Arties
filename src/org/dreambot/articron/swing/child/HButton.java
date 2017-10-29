package org.dreambot.articron.swing.child;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 19.09.2017
 * Alias: Dinh
 * Time: 23:03
 */

public class HButton extends JButton {

    private final Color HOVER_COLOR;

    public HButton(ImageIcon icon, Color background, Color hover) {
        this(null, null, null, background, hover, icon);
    }

    public HButton(String text, ActionListener listener) {
        this(text, listener, null, HFrame.LOGO_GREEN, HFrame.HOV_GREEN, null);
    }

    public HButton(String text, ActionListener listener, Icon icon) {
        this(text, listener, null, HFrame.LOGO_GREEN, HFrame.HOV_GREEN, icon);
    }

    public HButton(String text, Color background, Color hover) {
        this(text, null, null, background, hover, null);
    }

    public HButton(String text, ActionListener listener, Dimension dimension) {
        this(text, listener, dimension, HFrame.LOGO_GREEN, HFrame.HOV_GREEN, null);
    }

    public HButton(String text, Dimension dimension, Color background, Color hover) {
        this(text, null, dimension, background, hover, null);
    }

    public HButton(String text, ActionListener listener, Dimension dimension, Icon icon) {
        this(text, listener, dimension, HFrame.LOGO_GREEN, HFrame.HOV_GREEN, icon);
    }

    public HButton(String text, ActionListener listener, Dimension dimension, Color background, Color hover) {
        this(text, listener, dimension, background, hover, null);
    }

    public HButton(String text, ActionListener listener, Dimension dimension, Color background, Color hover,
                   Icon icon) {
        HOVER_COLOR = hover;

        if (icon == null) setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
                new EmptyBorder(10, 10, 10, 10)));
        else setBorder(null);

        setText(text);
        setIcon(icon);
        setBackground(background);
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFocusable(false);

        if (listener != null)
            addActionListener(listener);
        if (dimension != null)
            setPreferredSize(dimension);
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(HOVER_COLOR);
        } else if (getModel().isRollover()) {
            g.setColor(HOVER_COLOR);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}