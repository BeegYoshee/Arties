package org.dreambot.articron.swing.child;

import javax.swing.Icon;

import org.dreambot.articron.swing.special.HListItemTransferHandler;

/**
 * Created by: Niklas
 * Date: 20.10.2017
 * Alias: Dinh
 * Time: 19:57
 */

public class HBin<T> extends HImageLabel {
    public HBin(String text, Icon image, int distance) {
        super(text, image, distance);
        setTransferHandler(new HListItemTransferHandler<T>());
    }
}
