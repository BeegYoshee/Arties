package org.dreambot.articron.swing.child;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:04
 */

public class HTitleBar extends HPanel {

    private Point initialClick;

    public HTitleBar(Window parent, String text) {
        this(parent, text, null);
    }

    public HTitleBar(Window parent, String text, Icon icon) {
        setLayout(new BorderLayout());
        JComponent title;
        add(icon == null ? title = new HLabel(text) : (title = new HImageLabel(text, icon, 5)), BorderLayout.WEST);
        title.setBorder(new EmptyBorder(0, 2, 0, 0));
        if(icon!=null)((HImageLabel)title).getTextLabel().setBackground(HFrame.TITLE_BG);

        HRootButton button = new HRootButton();
        add(button, BorderLayout.EAST);

        if (parent instanceof JFrame) {
            button.addLabel(new JLabel(HImageLoader.getImage("iconify")), new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    ((JFrame) parent).setState(JFrame.ICONIFIED);
                }
            });
        }

        button.addLabel(new JLabel(HImageLoader.getImage("dispose")), new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.dispose();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;

                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                int x = thisX + xMoved;
                int y = thisY + yMoved;

                parent.setLocation(x, y);
            }
        });
        setAllBackgrounds(HFrame.TITLE_BG);
    }

    private void setAllBackgrounds(Color color) {
        setBackground(HFrame.TITLE_BG);
        for (Component component : getComponents()) {
            component.setBackground(color);
        }
    }
}