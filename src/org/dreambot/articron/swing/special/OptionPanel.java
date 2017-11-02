package org.dreambot.articron.swing.special;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.dreambot.articron.swing.child.HCheckSliderText;

/**
 * Created by: Niklas
 * Date: 14.07.2017
 * Time: 20:25
 */

public class OptionPanel implements Border, MouseListener, SwingConstants {
    private int offsetX = 15;
    private int offsetY = 10;

    private Component comp;
    private JComponent container;
    private Rectangle rect;
    private Border border;

    OptionPanel(Component comp, JComponent container, Border border) {
        this.comp = comp;
        this.container = container;
        this.border = border;
        container.addMouseListener(this);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets borderInsets = border.getBorderInsets(c);
        Insets insets = getBorderInsets(c);
        int temp = (insets.top - borderInsets.top) / 2;
        border.paintBorder(c, g, x, y + temp, width, height - temp);
        Dimension size = comp.getPreferredSize();
        rect = new Rectangle(offsetX, offsetY, size.width, size.height);
        Rectangle d = ((HCheckSliderText)comp).getSlider().getDisabledButton().getBounds();
        SwingUtilities.paintComponent(g, ((HCheckSliderText)comp).getSlider().getDisabledButton(), (Container) c, new Rectangle(15, 15, 25, 40));
        SwingUtilities.paintComponent(g, ((HCheckSliderText)comp).getSlider().getEnabledButton(), (Container) c, new Rectangle(40, 15, 25, 40));
        SwingUtilities.paintComponent(g, ((HCheckSliderText)comp).getLabel(), (Container) c, new Rectangle(70, 15, 80, 30));
    }

    public Insets getBorderInsets(Component c) {
        Dimension size = comp.getPreferredSize();
        Insets insets = border.getBorderInsets(c);
        insets.top = Math.max(insets.top, size.height);
        return insets;
    }

    private void dispatchEvent(MouseEvent me) {
        Point pt = me.getPoint();
        System.out.println(pt.toString());
        if (rect != null && rect.contains(me.getX(), me.getY())) {
            pt.translate(-offsetX, -offsetY);
            comp.setBounds(rect);
            comp.dispatchEvent(new MouseEvent(comp, me.getID()
                    , me.getWhen(), me.getModifiers()
                    , pt.x, pt.y, me.getClickCount()
                    , me.isPopupTrigger(), me.getButton()));

        } else {
            comp.dispatchEvent(new MouseEvent(comp, me.getID()
                    , me.getWhen(), me.getModifiers()
                    , pt.x, pt.y, me.getClickCount()
                    , me.isPopupTrigger(), me.getButton()));
        }
        if (!comp.isValid()) {
            container.repaint();
        }
    }

    public void mouseClicked(MouseEvent me) {
        dispatchEvent(me);
    }

    public void mouseEntered(MouseEvent me) {
        dispatchEvent(me);
    }

    public void mouseExited(MouseEvent me) {
        dispatchEvent(me);
    }

    public void mousePressed(MouseEvent me) {
        dispatchEvent(me);
    }

    public void mouseReleased(MouseEvent me) {
        dispatchEvent(me);
    }

}