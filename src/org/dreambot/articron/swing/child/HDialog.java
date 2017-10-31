package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 19.09.2017
 * Alias: Dinh
 * Time: 23:02
 */

public class HDialog extends JDialog {

    public HDialog(Container parent, String title, Object message) {
        this(parent, title, message, null);
    }

    public HDialog(Container parent, String title, Object message, Icon image) {
        setModal(true);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

        setLayout(new BorderLayout());
        setUndecorated(true);

        add(new HTitleBar(this, title), BorderLayout.NORTH);

        HPanel center = new HPanel(new BorderLayout());

        if (message instanceof String) {
            center.add(getContainer(
                    (image == null ? new HLabel((String) message) : new HImageLabel((String) message, image, 5))));
        } else {
            center.add((Container) message);
        }

        add(center, BorderLayout.CENTER);

        HPanel bottom = new HPanel(new FlowLayout());
        bottom.add(new HButton("OK", listener -> dispose(), new Dimension(80, 30)));
        add(bottom, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private Container getContainer(Component component) {
        HPanel panel = new HPanel(new FlowLayout());
        panel.add(component);
        return panel;
    }
}
