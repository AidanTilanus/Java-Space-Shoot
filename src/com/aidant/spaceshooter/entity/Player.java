package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;
import com.aidant.spaceshooter.SoundEffect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

	KeyHandler keyH;

	public int speed;
	private int shootCooldown = 0;

	BufferedImage leftImage, middleImage, rightImage;

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
		shootCooldown = 20;
	}

	public void getPlayerImage() {
		try {

			int shipAtlasY = 32;
			BufferedImage spriteAtlas = ImageIO.read(getClass().getResourceAsStream("/Ships.png"));

			leftImage = spriteAtlas.getSubimage(0, shipAtlasY, 8, 8);
			middleImage = spriteAtlas.getSubimage(8, shipAtlasY, 8, 8);
			rightImage = spriteAtlas.getSubimage(16, shipAtlasY, 8, 8);
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

		if(keyH.spacePressed && shootCooldown <= 0) {
			SoundEffect.playSound("/sfx/shoot.wav", 0.5f);

			Laser laser = new Laser(gp, keyH);

			laser.x = x;
			laser.y = y;

			gp.lasers.add(laser);

			shootCooldown = 10; // the amount of frames between shot. (min)
		}

		if(shootCooldown > 0) {
			shootCooldown--;
		}

		update_animation();

		int borderMargin = (image.getWidth() * gp.scale) / 2;
		if(x > gp.screenWidth - borderMargin) {
			x = gp.screenWidth - borderMargin;
		}
		else if (x < borderMargin) {
			x = borderMargin;
		}
	}

	void update_animation() {
		if (keyH.leftPressed && !keyH.rightPressed) {
			image = leftImage;
		}
		else if(keyH.rightPressed && !keyH.leftPressed) {
			image = rightImage;
		}
		else {
			image = middleImage;
		}
	}
}
