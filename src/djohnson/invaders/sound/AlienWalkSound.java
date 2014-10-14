package djohnson.invaders.sound;

public class AlienWalkSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.AlienMovement_One;
	}

	public AlienWalkSound() {
		super("/fastinvader1.wav");
	}
}
