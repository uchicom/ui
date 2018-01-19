// (c) 2017 uchicom
package com.uchicom.ui;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JDialog;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ResumeDialog extends JDialog {

	protected Properties config;
	private static final String PROP_SPLIT_CHAR = ":";
	private static final String PROP_WINDOW_POSITION = "window.position";

	public ResumeDialog(Window owner, Properties config, String dialogKey) {
		super(owner);
		this.config = config;
		initComponents(dialogKey);
	}
	public ResumeDialog(Properties config, String dialogKey) {
		this.config = config;
		initComponents(dialogKey);
	}

	private void initComponents(String windowKey) {
		String positionKey = PROP_WINDOW_POSITION + "." + windowKey;
		setWindowPosition(this, positionKey);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// 画面の位置を保持する
				storeWindowPosition(ResumeDialog.this, positionKey);
			}

		});
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent ce) {
				storeWindowPosition(ResumeDialog.this, positionKey);
			}

			@Override
			public void componentResized(ComponentEvent ce) {
				storeWindowPosition(ResumeDialog.this, positionKey);
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

}
