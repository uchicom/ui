// (c) 2018 uchicom
package com.uchicom.ui;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ImagePanel extends JPanel {

	private BufferedImage image;

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
