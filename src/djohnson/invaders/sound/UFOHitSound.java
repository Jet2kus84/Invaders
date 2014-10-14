package djohnson.invaders.sound;

public class UFOHitSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.UFOHit;
	}

	public UFOHitSound() {
		super("/ufo_highpitch.wav");
	}
}
