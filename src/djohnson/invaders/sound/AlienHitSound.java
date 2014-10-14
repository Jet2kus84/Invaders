package djohnson.invaders.sound;

public class AlienHitSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.AlienKilled;
	}

	public AlienHitSound() {
		super("/invaderkilled.wav");
	}
}
