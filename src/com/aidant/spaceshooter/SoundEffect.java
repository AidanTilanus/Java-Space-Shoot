package com.aidant.spaceshooter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundEffect {

	public static void playSound(String soundFilePath, float volume) {
		try {
			URL soundURL = SoundEffect.class.getResource(soundFilePath);
			if(soundURL == null) {
				throw new Exception("No sound file at " + soundFilePath);
			}

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(20f * (float) Math.log10(volume));

			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Overloaded method without volume argument (default volume)
	public static void playSound(String soundFileName) {
		playSound(soundFileName, 1.0f); // Default volume is 1.0 (full volume)
	}
}
