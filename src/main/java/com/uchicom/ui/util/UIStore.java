package com.uchicom.ui.util;

import java.awt.Component;
import java.util.Properties;

import javax.swing.undo.UndoManager;

/**
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public interface UIStore<T extends Component> {

	public T getMainComponent();

	public UndoManager getUndoManager();

	public Properties getActionResource();
}
