package org.dreambot.articron.swing.child;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.dreambot.articron.swing.HFrame;

/**
 * Created by: Niklas
 * Date: 20.09.2017
 * Alias: Dinh
 * Time: 17:54
 */

public class HList<E> extends JList<E> {

    private DefaultListModel<E> model;

    public HList() {
        setBorder(BorderFactory.createLineBorder(HFrame.LOGO_GREEN, 1));
        setModel(model = new DefaultListModel<>());
        setBackground(HFrame.LIST_BG);
        setCellRenderer(new HCellRenderer());
    }

    public void addElement(E element) {
        model.addElement(element);
    }

}
