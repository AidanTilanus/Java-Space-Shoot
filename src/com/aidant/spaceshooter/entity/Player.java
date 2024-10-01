package com.aidant.spaceshooter.entity;

import com.aidant.spaceshooter.GamePanel;
import com.aidant.spaceshooter.KeyHandler;
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
    }
}
