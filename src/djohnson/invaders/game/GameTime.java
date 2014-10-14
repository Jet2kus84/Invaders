package djohnson.invaders.game;

public class GameTime {

	public static long currentGameTime() {
		
		gameTime = System.currentTimeMillis();
		return gameTime;
	}
	
	private GameTime() { }
	
	private static long gameTime;

}
