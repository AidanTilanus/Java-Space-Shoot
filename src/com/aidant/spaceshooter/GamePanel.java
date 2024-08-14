package com.aidant.spaceshooter;

import com.aidant.spaceshooter.entity.Entity;
import com.aidant.spaceshooter.entity.Laser;
import com.aidant.spaceshooter.entity.Player;
import com.aidant.spaceshooter.entity.obstacles.Astroid;
import com.aidant.spaceshooter.entity.obstacles.Obstacle;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	public final int scale = 4;

	public final int screenWidth = 128 * scale;
	public final int screenHeight = 256 * scale;

	//FPS
	int FPS = 60;

	Random random = new Random(69420);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	public int spawnCooldown = 30; // 1 second is 60
	public int currentSpawnCooldownTime = 0;

	public BufferedImage backgroundImage;

	public ArrayList<Laser> lasers = new ArrayList<>();
	public ArrayList<Obstacle> obstacles = new ArrayList<>();

	// Declare Entities
	Player player = new Player(this, keyH);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/BackGrounds.png"));
			backgroundImage = backgroundImage.getSubimage(0, 0, 128, 256);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double drawInterval = 1_000_000_000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		//GAME LOOP
		while(gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if(timer >= 1_000_000_000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update() {

		//FIXME - make it so it can have more that just Astroid
		if (currentSpawnCooldownTime <= 0) {
			Obstacle newObstacle = new Astroid(this, keyH);

			int borderMargin = (newObstacle.image.getWidth() * scale) / 2;
			newObstacle.x = random.nextInt(borderMargin, screenWidth - borderMargin);
			newObstacle.y = -16;

			obstacles.add(newObstacle);

			currentSpawnCooldownTime = spawnCooldown;
		}

		currentSpawnCooldownTime--;

		for(Entity obstacle : obstacles) {
			obstacle.update();
		}
		obstacles.removeIf(Obstacle::isOfScreen);

		for(Laser laser : lasers) {
			laser.update();
		}
		lasers.removeIf(Laser::isOfScreen);

		player.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

		for(Entity obstacle : obstacles) {
			obstacle.draw(g2);
		}

		for(Laser laser : lasers) {
			laser.draw(g2);
		}

		player.draw(g2);

		g2.dispose();
	}
}
