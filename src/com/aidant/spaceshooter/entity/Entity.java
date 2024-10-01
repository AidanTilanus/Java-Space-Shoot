package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {

    GamePanel gp;

    public int x, y;
    public int width, height;
    public int speed;

    public BufferedImage image;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage getImageFromSpriteSheet(BufferedImage spritesheet, int x, int y, int width, int height) {
        try {

            BufferedImage subimage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Ships.png"))).getSubimage(x, y, width, height);
            return subimage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, width * gp.scale, height * gp.scale, null);
    }
}
