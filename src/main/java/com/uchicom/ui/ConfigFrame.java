// (c) 2017 uchicom
package com.uchicom.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ConfigFrame extends JFrame {

	protected File configFile;
	protected Properties config = new Properties();

	public ConfigFrame(File configFile) {
		this.configFile = configFile;
		initComponents();
	}

	/**
	 *
	 */
	private void initComponents() {
		initProperties();
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
		if (configFile.exists() && configFile.isFile()) {
			try (FileInputStream fis = new FileInputStream(configFile);) {
				config.load(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

}
