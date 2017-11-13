package org.dreambot.articron.swing.special;

import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import org.dreambot.articron.swing.child.HBin;

/**
 * Created by: Niklas Date: 19.10.2017 Alias: Dinh Time: 18:54
 */

@SuppressWarnings("unchecked")
public class HListItemTransferHandler<T> extends TransferHandler {

	@Override
	protected Transferable createTransferable(JComponent component) {
		JList<T> list = (JList<T>) component;
		index = list.getSelectedIndex();
		T transferredObject = list.getSelectedValue();
		return new GenericTransferable<>(transferredObject);
	}

	@Override
	public boolean canImport(TransferSupport info) {
		return info.isDataFlavorSupported(GenericTransferable.FLAVOR);
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	@Override
	public boolean importData(TransferSupport info) {
		if (!canImport(info)) {
			return false;
		} 

		JList<Object> target = (JList<Object>) info.getComponent();
		JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
		DefaultListModel<Object> listModel = (DefaultListModel<Object>) target.getModel();
		int index = dl.getIndex();
		int max = listModel.getSize();

		if (index < 0 || index > max)
			index = max;

		addIndex = index;

		try {
			Object object = info.getTransferable().getTransferData(GenericTransferable.FLAVOR);
			listModel.add(index, object);
			target.addSelectionInterval(index, index);
			return moveAllowed = true;
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected void exportDone(JComponent c, Transferable data, int action) {
		if (moveAllowed)
			cleanup(c, action == MOVE, false);
		else
            cleanup(c, true, true);
	}

	private void cleanup(JComponent component, boolean remove, boolean bin) {
		if (remove && index != -1) {
			JList<T> source = (JList<T>) component;
			DefaultListModel<T> model = (DefaultListModel<T>) source.getModel();
			int removeAt = index > addIndex ? index + 1 : index;
			model.remove(bin ? removeAt - 1 : removeAt);
		}

		index = -1;
		addIndex = -1;
		moveAllowed = false;
	}

	private int index = -1;
	private int addIndex = -1;
	private boolean moveAllowed = false;
}