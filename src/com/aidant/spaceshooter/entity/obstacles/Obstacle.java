package com.aidant.spaceshooter.entity.obstacles;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;
import com.aidant.spaceshooter.entity.Entity;

import java.awt.image.BufferedImage;

public class Obstacle extends Entity {
	KeyHandler keyH;

	public int speed;

	@Override
	public void update() {
		y += speed;

		// move them out of the border
		int borderMargin = (image.getWidth() * gp.scale) / 2;
		if(x > gp.screenWidth - borderMargin) {
			x = gp.screenWidth - borderMargin;
		}
		else if (x < borderMargin) {
			x = borderMargin;
		}
	}

	public boolean isOfScreen() { return y > (gp.screenHeight * gp.scale) + 16; }
}
