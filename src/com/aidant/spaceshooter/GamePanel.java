package com.aidant.spaceshooter;

import com.aidant.spaceshooter.entity.Laser;
import com.aidant.spaceshooter.entity.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	public final int scale = 4;

	public final int screenWidth = 128 * scale;
	public final int screenHeight = 256 * scale;

	//FPS
	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	public ArrayList<Laser> lasers = new ArrayList<>();

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

		for(Laser laser : lasers) {
			laser.update();
		}
		lasers.removeIf(Laser::isOfScreen);

		player.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		for(Laser laser : lasers) {
			laser.draw(g2);
		}

		player.draw(g2);

		g2.dispose();
	}
}
