package weco.fileManager;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	public static synchronized void playSound(final String titel){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("sfx\\" + titel));
					clip.open(inputStream);
					clip.start();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
}
