package djohnson.invaders.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterKeyListener extends KeyAdapter {

public RegisterKeyListener(RegisterKeyEvent _event) {
		
		this.event = _event;
	}
	
	public void keyReleased(KeyEvent e) {
		event.keyReleased(e);
	}
	
	public void keyPressed(KeyEvent e) {
		event.keyPressed(e);
	}
	
	RegisterKeyEvent event;
}
