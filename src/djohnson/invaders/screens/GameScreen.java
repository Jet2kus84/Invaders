package djohnson.invaders.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;



import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.collisions.CollisionMan;
import djohnson.invaders.contentManager.Content;
import djohnson.invaders.controls.RegisterKeyEvent;
import djohnson.invaders.controls.RegisterKeyListener;
import djohnson.invaders.drawContent.DrawMan;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.game.GameTime;


/*
 *  This class will hold the space invader game
 */
public class GameScreen extends Screen implements Runnable {

	
	@Override
	public void run() {
		
		//get current system time(game time)
		beforeTime = GameTime.currentGameTime();
		
		while(true) {
			
			//update animations
			update();
			
			//repaint animations to screen
			repaint();
			
			//get time difference between current & before current time
			timeDiff = GameTime.currentGameTime() - beforeTime;
			
			sleep = DELAY - timeDiff;
			
			if(sleep < 0) {
				sleep = 2;
			}
			
			try {
				
				Thread.sleep(sleep);
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}
			
			//reset beforeTime to current time in milliseconds
			beforeTime = GameTime.currentGameTime();
			
		}
	}

	public void start() {
		
		if(gameScreenThread == null)
			gameScreenThread = new Thread(this);
		gameScreenThread.start();
	}
	
	public void stop() {
		if(gameScreenThread != null)
			gameScreenThread = null;
	}
	
	/*@Override
	public void addNotify() {
		super.addNotify();
		
		//create a new thread and give this class
		//as an argument
		animator = new Thread(this);
		
		//start the thread
		animator.start();
	}*/
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw content to the screen
		draw(g);
	}
	
	@Override
	public void draw(Graphics g) {

		//create a  2d graphic
		Graphics2D g2d = (Graphics2D) g;
					
		//draw all game components
		DrawMan.Instance().DrawGameComponents(g2d, this);
					
		//ensure that all graphics are up to date
		Toolkit.getDefaultToolkit().sync();
				
		//dispose of any resources
		g2d.dispose();	
		
	}
	
	private void update() {
		
		//update all movable objects
		EntityMan.Instance().moveAll();
		
		//Update and continue to accept collision visitors
		EntityMan.Instance().acceptAll(CollisionMan.Instance().getVisitorList());
		
		//animate all entities
		EntityMan.Instance().animate();
		
		//update all collision checks
		BoundingBoxMan.Instance().UpdateBoundingBoxList();
	
	}
	
	@Override
	public ScreenType getScreenType() { return ScreenType.GameScreen; }
	
	//constructor
	public GameScreen() {
		
		//Constant variables
		SCREEN_SIZE_X = 700;
		SCREEN_SIZE_Y = 600;
		DELAY = 25;
		
		//Load game's contents
		LoadContent();
		
		//initialize any content
		init();
	}

	@Override
	public void init() {

		_event = new RegisterKeyEvent();
		beforeTime = 0;
		timeDiff = 0;
		sleep = 0;
	
		//set the background color of the panel
		//figure out how to set pic as background
		setBackground(Color.BLACK);
	
		//allow panel to focus on keyAdapter
		setFocusable(true);
		
		//preferred size for this particular panel
		setPreferredSize(new Dimension(SCREEN_SIZE_X+1, SCREEN_SIZE_Y+1));
		
		setDoubleBuffered(true);
		
		//add key listen for key presses
		addKeyListener(new RegisterKeyListener(_event));

	}
	
	private void LoadContent() {
		
		//load in a textures
		Content.Instance().loadTextures();
		
		//load in fonts
		Content.Instance().loadFonts();
		
		//load images
		Content.Instance().loadImage();
		
		//load game sprites
		Content.Instance().loadSprite();
		
		//load Font sprites
		Content.Instance().loadFontSprites();
		
		//load collisions
		Content.Instance().loadBoundingBox();
		
		//load Entities
		Content.Instance().loadEntities();
		
		//load Collision Reactions
		Content.Instance().loadCollisionReactions();
		
		//load draw components
		Content.Instance().loadDrawComponents();
		
		//load sound objects
		Content.Instance().loadSound();
		
		//load mediators
		Content.Instance().loadMediators();
		
	}
	
	private Thread gameScreenThread;
	private long beforeTime;
	private long timeDiff;
	private long sleep;
	private final int DELAY;
	private final int SCREEN_SIZE_X;
	private final int SCREEN_SIZE_Y;
	private RegisterKeyEvent _event;
	private static final long serialVersionUID = 1L;
	
}
