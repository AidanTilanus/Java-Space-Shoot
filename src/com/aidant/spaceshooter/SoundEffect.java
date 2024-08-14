package com.aidant.spaceshooter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect {

	public static void playSound(String soundFilePath) {
		try {
			URL soundURL = SoundEffect.class.getResource(soundFilePath);
			if(soundURL == null) {
				throw new Exception("No sound file at " + soundFilePath);
			}

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);

			Clip clip = AudioSystem.getClip();

			clip.open(audioIn);

			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
