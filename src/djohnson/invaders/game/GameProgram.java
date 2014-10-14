package djohnson.invaders.game;

import javax.swing.JFrame;

import djohnson.invaders.screens.GameScreen;
import djohnson.invaders.screens.MenuScreen;
import djohnson.invaders.screens.Screen;
import djohnson.invaders.screens.ScreenType;


/*
 * 
 *  This class will serve as the main frame that
 *  all panels will be added to
 *  
 */
public class GameProgram extends JFrame {

	public void runGame() {
		init();
	}
	
	private void init() {
		
		_screen = new GameScreen(); //new MenuScreen();
		
		if(_screen instanceof GameScreen) {
			setTitle("Space Invaders");
		}
		else if(_screen instanceof MenuScreen) {
			setTitle("Menu Screen");
		}
		else {
			setTitle("Test Frame");
		}
		
		//start the thread before placing component in frame
		if(_screen.getScreenType() == ScreenType.GameScreen)
			((GameScreen) _screen).start();
		
		//add a component to the frame
		add(_screen);
		
		//set the frame to the size of the panel added
		pack();
		
		//place frame in the center of the screen
		//setLocationRelativeTo(null);
		
		//window can not be resized
		setResizable(false);
		
		//allow window to be closed by clicking x
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//allow the frame to be shown on screen
		setVisible(true);
				
	}
	
	private Screen _screen;
	private static final long serialVersionUID = 1L;
}
