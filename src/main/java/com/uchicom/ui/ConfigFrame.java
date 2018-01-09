// (c) 2017 uchicom
package com.uchicom.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import com.uchicom.util.ResourceUtil;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ConfigFrame extends JFrame {

	protected File configFile;
	protected Properties config;

	public ConfigFrame(File configFile) {
		this.configFile = configFile;
		initProperties();
		initComponents();
	}

	public ConfigFrame(File configFile, Properties config) {
		this.configFile = configFile;
		this.config = config;
		initComponents();
	}
	/**
	 *
	 */
	private void initComponents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent we) {
				storeProperties();
			}
		});

	}

	/**
	 *
	 */

	private void initProperties() {
		config = ResourceUtil.createProperties(configFile, "UTF-8");
	}
	private void storeProperties() {
		try {
			if (!configFile.exists()) {
				configFile.getParentFile().mkdirs();
				configFile.createNewFile();
			}
			try (FileOutputStream fos = new FileOutputStream(configFile);) {
				config.store(fos, getTitle());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return config.getProperty(key);
	}
	public int getInt(String key, int defaultValue) {
		if (config.containsKey(key)) {
			try {
				return Integer.parseInt(config.getProperty(key));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return defaultValue;
	}

}
