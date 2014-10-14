package djohnson.invaders.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public abstract class Sound {

	public void play() {
		
		new Thread() {
			public void run() {
				if(!playing) {
					sndClip.play();
					playing = true;
				}
			}
		}.start();
		
		playing = false;
	}
	
	public void loop() {
		
		if(!playing) {
			sndClip.loop();
			playing = true;
		}
	}
	
	public void stop() {
		
		if(playing) {
			sndClip.stop();
			playing = false;
		}
	}
	
	public abstract SoundName getSoundName();
	
	public Sound(String _fileName) {
		
		this.fileName = _fileName;
		sndClip = Applet.newAudioClip(Sound.class.getResource(fileName));
		playing = false;
	}
	
	private AudioClip sndClip;
	private String fileName;
	private boolean playing;
	
}
