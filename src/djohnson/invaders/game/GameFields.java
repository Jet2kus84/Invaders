package djohnson.invaders.game;

/*
 * this class holds fields that are
 * shared among entities 
 */
public class GameFields {
	
	//this class doesn't need to be instantiated
	private GameFields() { }
	
	//non-changeable fields
	public final static int distanceX = 50; //distance between alien entities on the x-axis
	public final static int distanceY = 40; //distance between alien entities on the y-axis
	
	//changeable fields
	public static boolean moveR = true;
	public static float dy = 1.5f;
	public static float dx = 1.5f; //speed of invaders
	public static int numberOfSquids = 11; //20% of total invaders(.20)
	public static int numberOfCrabs = 22; //40% of total invaders(.40)
	public static int numberOfOctopus = 22; //40% of total invaders(.40)
	public static int numberOfInvaders = (numberOfSquids + numberOfCrabs + numberOfOctopus);
	public static final int startPosition_X = 10;
	public static final int startPosition_Y = 30;
	public static float invadersFrameSpeed = 250;
	public static float speedIncrease = .10f;
	public static float numberOfPlayerLives = 3;
	public static boolean showBoundingBoxes = true;
	public static void speedIncrease() {
		
	}
}
