package com.aidant.spaceshooter.entity.obstacles;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;
import com.aidant.spaceshooter.entity.Entity;

import java.awt.image.BufferedImage;

public class Obstacle extends Entity {
	KeyHandler keyH;

	public int speed;

	public boolean isOfScreen() { return y > (gp.screenHeight * gp.scale) + 16; }
}
