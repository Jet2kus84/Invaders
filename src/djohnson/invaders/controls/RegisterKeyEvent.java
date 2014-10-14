package djohnson.invaders.controls;

import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.game.GameFields;

import java.awt.event.KeyEvent;

public class RegisterKeyEvent {

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		ShipEntity s = 
				(ShipEntity)EntityMan.Instance().get(EntityName.Ship);
		
		if(key == KeyEvent.VK_SPACE) {
			
			//load a missile into ship & fire
			s.fire();				
		}
		
		if(key == KeyEvent.VK_LEFT) {
			s.setDirectionX(-velocity);
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			s.setDirectionX(velocity);
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if(key == KeyEvent.VK_S){
			if(GameFields.showBoundingBoxes) {
				GameFields.showBoundingBoxes = false;
			}
			else {
				GameFields.showBoundingBoxes = true;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
        int key = e.getKeyCode();
        ShipEntity s = 
        		(ShipEntity)EntityMan.Instance().get(EntityName.Ship);
        
        if (key == KeyEvent.VK_LEFT) {
        	s.setDirectionX(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	s.setDirectionX(0);
        }
    }

	public RegisterKeyEvent() {	
		velocity = 7;
	}
	
	private float velocity;  
}
