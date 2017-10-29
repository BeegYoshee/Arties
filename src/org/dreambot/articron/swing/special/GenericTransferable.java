package org.dreambot.articron.swing.special;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by: Niklas
 * Date: 20.10.2017
 * Alias: Dinh
 * Time: 20:03
 */

public class GenericTransferable<T> implements Transferable {
    static DataFlavor FLAVOR;
    private T rewardItem;

    GenericTransferable(T rewardItem) {
        GenericTransferable.FLAVOR = new DataFlavor(rewardItem.getClass(), rewardItem.getClass().getCanonicalName());
        this.rewardItem = rewardItem;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return rewardItem;
    }
}
