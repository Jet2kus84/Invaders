package djohnson.invaders.sound;

public class UFOSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.UFO;
	}
	
	public UFOSound() {
		super("/ufo_lowpitch.wav");
	}

}
