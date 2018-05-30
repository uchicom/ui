// (c) 2017 uchicom
package com.uchicom.ui;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ResumeFrame extends ConfigFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PROP_SPLIT_CHAR = ":";
	private static final String PROP_WINDOW_POSITION = "window.position";
	private static final String PROP_WINDOW_STATE = "window.state";
	public ResumeFrame(File configFile, String windowKey) {
		super(configFile);
		initComponents(windowKey);
	}
	public ResumeFrame(File configFile, Properties config, String windowKey) {
		super(configFile, config);
		initComponents(windowKey);
	}
	private void initComponents(String windowKey) {
		String positionKey = PROP_WINDOW_POSITION + "." + windowKey;
		String stateKey = PROP_WINDOW_STATE + "." + windowKey;
		setWindowPosition(this, positionKey);
		setWindowState(this, stateKey);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if (ResumeFrame.this.getExtendedState() == JFrame.NORMAL) {
					// 画面の位置を保持する
					storeWindowPosition(ResumeFrame.this, positionKey);
				}
				storeWindowState(ResumeFrame.this, stateKey);
			}

		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent ce) {
				if (getExtendedState() == JFrame.NORMAL) {
					storeWindowPosition(ResumeFrame.this, positionKey);
				}
			}
			@Override
			public void componentResized(ComponentEvent ce) {
				if (getExtendedState() == JFrame.NORMAL) {
					storeWindowPosition(ResumeFrame.this, positionKey);
				}
			}
		});

	}

	/**
	 * 画面の位置をプロパティに設定する。
	 *
	 * @param window
	 * @param key
	 */
	private void storeWindowPosition(Window window, String key) {
		String value = window.getLocation().x + PROP_SPLIT_CHAR + window.getLocation().y + PROP_SPLIT_CHAR
				+ window.getWidth() + PROP_SPLIT_CHAR + window.getHeight() + PROP_SPLIT_CHAR;
		config.setProperty(key, value);
	}
	/**
	 * 画面の位置をプロパティに設定する。
	 *
	 * @param frame
	 * @param key
	 */
	private void storeWindowState(JFrame frame, String key) {
		String value = frame.getState() + PROP_SPLIT_CHAR
				+ frame.getExtendedState();
		config.setProperty(key, value);
	}

	/**
	 * 画面のサイズをプロパティから設定する。
	 *
	 * @param window
	 * @param key
	 */
	public void setWindowPosition(Window window, String key) {
		if (config.containsKey(key)) {
			String initPoint = config.getProperty(key);
			String[] points = initPoint.split(PROP_SPLIT_CHAR);
			if (points.length > 3) {
				window.setLocation(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
				window.setPreferredSize(new Dimension(Integer.parseInt(points[2]), Integer.parseInt(points[3])));
			}
		}
	}
	public void setWindowState(JFrame frame, String key) {
		if (config.containsKey(key)) {
			String initPoint = config.getProperty(key);
			String[] points = initPoint.split(PROP_SPLIT_CHAR);
			if (points.length > 1) {
				frame.setState(Integer.parseInt(points[0]));
				frame.setExtendedState(Integer.parseInt(points[1]));
			}
		}
	}


}
