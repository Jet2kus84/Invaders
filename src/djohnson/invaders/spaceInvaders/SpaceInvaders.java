package djohnson.invaders.spaceInvaders;

import javax.swing.SwingUtilities;

import djohnson.invaders.game.GameProgram;

/**
*  @author Jet2kus84
*  
*/

public class SpaceInvaders {
	
	public static void main(String[] args) {
		
		//Queue the event
		SwingUtilities.invokeLater(new Runnable( ) {//EventQueue.invokeLater(new Runnable() {
					
			@Override
			public void run() {		
				//instantiate & run a new game Thread
				new GameProgram().runGame();
			}
		});	
	}	
}
