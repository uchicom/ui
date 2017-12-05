// (c) 2017 uchicom
package com.uchicom.ui.util;

import java.awt.Component;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class DialogUtil {

	public static void showMessageDialog(String message, Dialog.ModalityType modalityType) {
		JDialog dialog = new JDialog();
		dialog.add(new JLabel(message));
		dialog.pack();
		dialog.setModalityType(modalityType);
		dialog.setVisible(true);
	}
	public static int showConfirmDialog(Component parentComponent, String message, String title, int optionType) {
		return showConfirmDialog(parentComponent, message, title, optionType, Dialog.ModalityType.DOCUMENT_MODAL);
	}
	public static int showConfirmDialog(Component parentComponent, String message) {
		return showConfirmDialog(parentComponent, message, UIManager.getString("OptionPane.titleText"), JOptionPane.YES_NO_CANCEL_OPTION, Dialog.ModalityType.DOCUMENT_MODAL);
	}
	public static int showConfirmDialog(Component parentComponent, String message, String title, int optionType, Dialog.ModalityType modalityType) {
		JOptionPane pane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, optionType);
		JDialog dialog = pane.createDialog(parentComponent, title);
		dialog.pack();
		dialog.setModalityType(modalityType);
		dialog.setVisible(true);
		Object selectedValue = pane.getValue();
		if (selectedValue instanceof Integer)
			return ((Integer) selectedValue).intValue();
		return JOptionPane.CLOSED_OPTION;
	}

}
