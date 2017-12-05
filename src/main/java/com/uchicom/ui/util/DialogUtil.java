// (c) 2017 uchicom
package com.uchicom.ui.util;

import java.awt.Component;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class DialogUtil {

	public static void showMessageDialog(Component parentComponent, String message) {
		showDialog(parentComponent,
				message,
				UIManager.getString(
						"OptionPane.messageDialogTitle"),
				JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.DEFAULT_OPTION,
				Dialog.ModalityType.DOCUMENT_MODAL);
	}

	public static int showConfirmDialog(Component parentComponent, String message, String title, int optionType) {
		return showDialog(parentComponent,
				message,
				title,
				optionType,
				JOptionPane.QUESTION_MESSAGE,
				Dialog.ModalityType.DOCUMENT_MODAL);
	}

	public static int showConfirmDialog(Component parentComponent, String message) {
		return showDialog(parentComponent,
				message,
				UIManager.getString("OptionPane.titleText"),
				JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_CANCEL_OPTION,
				Dialog.ModalityType.DOCUMENT_MODAL);
	}

	public static int showDialog(Component parentComponent,
			String message,
			String title,
			int mesasgeType,
			int optionType,
			Dialog.ModalityType modalityType) {
		JOptionPane pane = new JOptionPane(message, mesasgeType, optionType);
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
