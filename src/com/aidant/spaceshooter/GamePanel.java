package com.aidant.spaceshooter;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int scale = 3;

	final int screenWidth = 128 * scale;
	final int screenHeight = 256 * scale;

	//FPS
	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	// Set player varibles
	int playerX = 100;
	int playerSpeed = 4;

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

		if(keyH.leftPressed) {
			playerX -= playerSpeed;
		}
		if(keyH.rightPressed) {
			playerX += playerSpeed;
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(Color.white);
		g2.fillRect(playerX, screenHeight - 100, 48, 48);

		g2.dispose();
	}
}
