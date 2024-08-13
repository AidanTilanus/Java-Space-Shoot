package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

	KeyHandler keyH;

	public int speed = 4;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = gp.screenWidth / 2; // half of the screen width.
		y = gp.screenHeight - 100; // 100 pixel above the bottom of the screen.
		speed = 5;
	}

	public void getPlayerImage() {
		try {

			BufferedImage spriteAtlas = ImageIO.read(getClass().getResourceAsStream("/Ships.png"));

			image = spriteAtlas.getSubimage(8, 32, 8, 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {

		if(keyH.leftPressed) {
			x -= speed;
		}
		if(keyH.rightPressed) {
			x += speed;
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		g2.drawImage(image, x, y, image.getWidth() * gp.scale, image.getHeight() * gp.scale, null);
	}
}
