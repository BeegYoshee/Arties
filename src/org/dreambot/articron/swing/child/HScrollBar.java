package org.dreambot.articron.swing.child;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 16:52
 */

class HScrollBar extends BasicScrollBarUI {
    private final Dimension DIMENSION = new Dimension(16, 16);
    private final Color TRACK_COLOR = new Color(81, 81, 81);

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new HButton(HImageLoader.getImage("up"), HFrame.ELEMENT_BG, HFrame.HOV_GREEN) {
            @Override
            public Dimension getPreferredSize() {
                return DIMENSION;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new HButton(HImageLoader.getImage("down"), HFrame.ELEMENT_BG, HFrame.HOV_GREEN) {
            @Override
            public Dimension getPreferredSize() {
                return DIMENSION;
            }
        };
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        g.setColor(TRACK_COLOR);
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color;
        JScrollBar sb = (JScrollBar) c;
        if (!sb.isEnabled() || r.width > r.height) {
            return;
        } else if (isDragging) {
            color = HFrame.SEL_GREEN;
        } else if (isThumbRollover()) {
            color = HFrame.HOV_GREEN;
        } else {
            color = HFrame.LOGO_GREEN;
        }
        g2.setPaint(color);
        g2.fillRoundRect(r.x, r.y, r.width - 1, r.height, 10, 10);
        g2.setPaint(Color.BLACK);
        g2.drawRoundRect(r.x, r.y, r.width - 1, r.height, 10, 10);
        g2.dispose();
    }

    @Override
    protected void setThumbBounds(int x, int y, int width, int height) {
        super.setThumbBounds(x, y, width, height);
        scrollbar.repaint();
    }
}
