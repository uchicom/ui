// (C) 2018 uchicom
package com.uchicom.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * 画像を描画するパネル.
 *
 * @author uchicom: Shigeki Uchiyama
 */
public class ImagePanel extends JPanel {

  private static final long serialVersionUID = 1L;

  Image image;

  public void setImage(Image image) {
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
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
    if (image.getWidth(this) < getWidth()) {
      x = (getWidth() - image.getWidth(this)) / 2;
    }
    if (image.getHeight(this) < getHeight()) {
      y = (getHeight() - image.getHeight(this)) / 2;
    }
    // 画像描画
    g.drawImage(image, x, y, this);
  }
}
