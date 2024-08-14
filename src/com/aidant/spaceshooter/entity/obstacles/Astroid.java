package com.aidant.spaceshooter.entity.obstacles;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;
import com.aidant.spaceshooter.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Astroid extends Obstacle {

	KeyHandler keyH;

	public int speed;

	public Astroid(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getImage();
	}

	public void setDefaultValues() {
		speed = 4;
	}

	public void getImage() {
		try {

			BufferedImage spriteAtlas = ImageIO.read(getClass().getResourceAsStream("/Miscellaneous.png"));

			image = spriteAtlas.getSubimage(8, 24, 8, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		y += speed;
	}
}
