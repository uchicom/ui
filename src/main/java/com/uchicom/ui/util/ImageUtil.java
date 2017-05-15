// (c) 2016 uchicom
package com.uchicom.ui.util;

import javax.swing.ImageIcon;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ImageUtil {

	public static ImageIcon getImageIcon(String path) {
		return new ImageIcon(ImageUtil.class.getClassLoader().getResource(path));
	}
}
