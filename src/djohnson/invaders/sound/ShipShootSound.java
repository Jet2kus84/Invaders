package djohnson.invaders.sound;

public class ShipShootSound extends Sound {

	@Override
	public SoundName getSoundName() {
		return SoundName.ShipShot;
	}
	
	public ShipShootSound() {
		super("/shoot.wav");
	}
}
