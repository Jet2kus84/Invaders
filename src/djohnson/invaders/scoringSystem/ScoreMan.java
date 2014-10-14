package djohnson.invaders.scoringSystem;

import java.util.Random;

import djohnson.invaders.entities.Entity;


public class ScoreMan {

	public void determinePoints(Entity _entity) {
		
		//score according to entity
		switch(_entity.getEntityName()) {
			case Squid:
				score += this.INVADER_SQUID_POINTS;
				break;
			case Crab:
				score += this.INVADER_CRAB_POINTS;
				break;
			case Octopus:
				score += this.INVADER_OCTOPUS_POINTS;
				break;
			case UFO:
				rand = new Random();
				score += rand.nextInt(UFO_MAX_POINTS) + UFO_MIN_POINTS;
				break;
			
			default:
				score += 0;
		}
	}
	
	//return the high score
	public int getHighScore() {
		return highScore;
	}
	
	//return the current score
	public int getScore() {
		return score;
	}
	
	public static ScoreMan getInstance() {
		if(instance == null) 
			instance = new ScoreMan();
		return instance;
	}
	
	private ScoreMan() {
		
		score = 0;
		highScore = 0;
		INVADER_CRAB_POINTS = 30;
		INVADER_OCTOPUS_POINTS = 10;
		INVADER_SQUID_POINTS = 40;
		UFO_MIN_POINTS = 100;
		UFO_MAX_POINTS = 500;
	}
	
	private static ScoreMan instance;
	private int score;
	private int highScore;
	private Random rand;
	private final int INVADER_SQUID_POINTS;
	private final int INVADER_CRAB_POINTS;
	private final int INVADER_OCTOPUS_POINTS;
	private final int UFO_MIN_POINTS;
	private final int UFO_MAX_POINTS;
		
}
