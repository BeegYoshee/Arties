package org.dreambot.articron.swing.child;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 19:33
 */

class HCheckButton extends JToggleButton {

    /*
     * 1 = Left
     * 3 = Right
     */

    private int position;

    HCheckButton(ActionListener listener, int position) {
        for (ActionListener ab : getActionListeners()) {
            removeActionListener(ab);
        }
        addActionListener(listener);
        this.position = position;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Rectangle r = getBounds();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(0, 0, r.width, r.height);

        if (isSelected()) {
            if (position == 1) g2.setColor(Color.RED);
            else g2.setColor(Color.GREEN);
            g2.fillOval(position == 1 ? 3 : getWidth() - 23, 6, 20, 20);
        }

        g2.setColor(Color.GRAY);
        g2.drawArc(position == 1 ? 0 : getWidth() - 26, 3, 25, 25, 90 * position, 180);
        g2.drawLine(position == 1 ? 13 : 0, 3, position == 1 ? getWidth() : getWidth() - (25 / 2), 3);
        g2.drawLine(position == 1 ? 13 : 0, 28, position == 1 ? getWidth() : getWidth() - (25 / 2), 28);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

}
