// (c) 2018 uchicom
package com.uchicom.ui;

import java.awt.Component;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.JMenuBar;

import com.uchicom.ui.util.MenuUtil;
import com.uchicom.ui.util.UIStore;

public abstract class MenuFrame<T extends Component> extends ResumeFrame implements UIStore<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuFrame(File configFile, String windowKey, Properties resource, String menuKey) {
		super(configFile, windowKey);
		initMenu(resource, menuKey);
	}
	public MenuFrame(File configFile, Properties config, String windowKey, Properties resource, String menuKey) {
		super(configFile, config, windowKey);
		initMenu(resource, menuKey);
	}

	private void initMenu(Properties resource, String menuKey) {
		Map<String, Action> actionMap = new HashMap<>();
		JMenuBar menuBar = new JMenuBar();
		String[] menus = resource.getProperty(menuKey).split(",");
		for (String menuProp : menus) {
			menuBar.add(MenuUtil.createMenu(this, resource, actionMap, menuKey + "." + menuProp));
		}
		setJMenuBar(menuBar);
	}
}
