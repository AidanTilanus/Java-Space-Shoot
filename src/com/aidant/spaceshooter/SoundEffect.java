package com.aidant.spaceshooter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundEffect {

	private static final Map<String, Clip> loadedClips = new HashMap<>();

	// Preload sound files
	public static void preloadSound(String soundFilePath) {
		try {
			URL soundURL = SoundEffect.class.getResource(soundFilePath);
			if (soundURL == null) {
				throw new Exception("No sound file at " + soundFilePath);
			}

			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);

			loadedClips.put(soundFilePath, clip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Play a preloaded sound
	public static void playSound(String soundFilePath, float volume) {
		try {
			Clip clip = loadedClips.get(soundFilePath);

			if (clip == null) {
				System.out.println("Sound not preloaded, loading now: " + soundFilePath);
				preloadSound(soundFilePath);
				clip = loadedClips.get(soundFilePath);
			}

			// Reset the clip to the beginning
			clip.setFramePosition(0);

			// Adjust volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(20f * (float) Math.log10(volume));

			// Start playing the sound
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Overloaded method without volume argument (default volume)
	public static void playSound(String soundFilePath) {
		playSound(soundFilePath, 1.0f); // Default volume is 1.0 (full volume)
	}
}
