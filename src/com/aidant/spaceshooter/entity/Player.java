package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage(){
        BufferedImage spritesheet = null;
        try {
            spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Ships.png")));
            image = getImageFromSpriteSheet(spritesheet, 8, 32, width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        width = 8;
        height = 8;
        speed = 4;
    }
}
