package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Laser extends Entity {

	KeyHandler keyH;

	public int speed;

	public Laser(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getLaserImage();
	}

	public void setDefaultValues() {
		speed = 5;
	}

	public void getLaserImage() {
		try {

			BufferedImage spriteAtlas = ImageIO.read(getClass().getResourceAsStream("/Projectiles.png"));

			image = spriteAtlas.getSubimage(0, 8, 8, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		y -= speed;
	}

	public boolean isOfScreen() { return y < -16; }
}
