// (c) 2018 uchicom
package com.uchicom.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 画像を描画するパネル.
 * 
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public void setImage(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		// 背景色を塗りつぶし
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		// 中央に表示
		int x = 0, y = 0;
		if (image.getWidth() < getWidth()) {
			x = (getWidth() - image.getWidth()) / 2;
		}
		if (image.getHeight() < getHeight()) {
			y = (getHeight() - image.getHeight()) / 2;
		}
		// 画像描画
		g.drawImage(image, x, y, this);
	}
}
