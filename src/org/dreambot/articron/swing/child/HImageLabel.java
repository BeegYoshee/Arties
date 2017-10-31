package org.dreambot.articron.swing.child;

import org.dreambot.articron.swing.HPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Niklas
 * Date: 18.09.2017
 * Alias: Dinh
 * Time: 23:14
 */

public class HImageLabel extends HPanel {

    private Object object;
    private HLabel text;
    
    public HImageLabel(Object object, Icon image, int distance) {
        this.object = object;
        setLayout(new BorderLayout());
        Box box = Box.createHorizontalBox();
        box.add(new JLabel(image));
        box.add(Box.createHorizontalStrut(distance));
        box.add(text=new HLabel(object.toString()));
        add(box, BorderLayout.CENTER);
    }

    public Object getObject() {
        return object;
    }
    
    public HLabel getTextLabel() {
    	return text;
    }
}
