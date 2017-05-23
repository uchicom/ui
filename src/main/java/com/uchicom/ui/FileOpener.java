// (c) 2017 uchicom
package com.uchicom.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public interface FileOpener {

	public void open(File file) throws IOException;
	public void open(List<File> fileList);

	public static void installDragAndDrop(JComponent component, FileOpener opener) {
		component.setTransferHandler(new TransferHandler() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean canImport(TransferSupport support) {
				DataFlavor[] dfs = support.getDataFlavors();
				for (DataFlavor df : dfs) {
					if (DataFlavor.javaFileListFlavor.equals(df)) {
						return support.getDropAction() == MOVE;
					}
				}
				return false;
			}

			@SuppressWarnings("unchecked")
			@Override
			public boolean importData(TransferSupport support) {
				if (support.isDrop()) {
					// ドロップ処理
					int action = support.getDropAction();
					if (action == COPY || action == MOVE) {
						Transferable t = support.getTransferable();
						try {
							List<File> fileList = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
							opener.open(fileList);
						} catch (UnsupportedFlavorException e) {
							return false;
						} catch (IOException e) {
							return false;
						}
					}
					return true;
				}
				return false;
			}
		});
	}
}
