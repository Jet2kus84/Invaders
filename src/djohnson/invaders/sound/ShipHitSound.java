package djohnson.invaders.sound;

public class ShipHitSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.ShipExplosion;
	}

	public ShipHitSound() {
		super("/explosion.wav");
	}
}
