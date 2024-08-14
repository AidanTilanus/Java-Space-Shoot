package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

	public GamePanel gp;

	public int x, y;

	public BufferedImage image = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
	public int imageSize = 8;

	public void update() { }

	public void draw(Graphics2D g2) {

		int halfWidth = (image.getWidth() * gp.scale) / 2;
		g2.drawImage(image, x - halfWidth, y, image.getWidth() * gp.scale, image.getHeight() * gp.scale, null);
	}

}
