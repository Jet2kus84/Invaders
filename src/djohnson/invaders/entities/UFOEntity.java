package djohnson.invaders.entities;


import java.awt.Graphics2D;
import java.awt.image.ImageObserver;


import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.game.GameFields;
import djohnson.invaders.game.GameTime;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.SoundName;
import djohnson.invaders.sprites.GameSprite;



/*
 * 	develop a timer so that the ufo can come by at random intervals
 * 
 */
public class UFOEntity implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
	
		if(alive) {
			ufo[0].colBox.drawContent(g2d, _observer);
			ufo[0].sprite.drawContent(g2d, _observer);
		}
	}

	@Override
	public EntityName getEntityName() {
		return EntityName.UFO;
	}

	@Override
	public BoundingBox[] getBoundingBox() {
		return ufo;
	}

	@Override
	public void move() {
			
		if(alive && (GameFields.numberOfInvaders != 0)) {
			this.ufo[0].pos_x += dx;
			
			//play ufo sound if within view
			SoundMan.Instance().get(SoundName.UFO).loop();
			
			//once ufo leaves the screen
			if(ufo[0].pos_x > BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxX()) {

				//stop ufo sound from looping if out of view
				SoundMan.Instance().get(SoundName.UFO).stop();
				
				int limit = 15 * 1000;
				
				if((GameTime.currentGameTime() - timeLapse) > limit) {
					
					ufo[0].pos_x = 0;
					timeLapse = GameTime.currentGameTime();
				}
			}
		}
		else {
			if(GameFields.numberOfInvaders != 0)
				spawnUFO();
			
		}
		//if ufo passes wall delete from list and respawn from a location
	}

	@Override
	public void accept(CollisionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void animate() {
		// Not Yet Implemented
	}
	
	@Override
	public void setBoundingBox(BoundingBox[] value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int numberOfEntities() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSprite(GameSprite[] _sprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameSprite[] getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCurrentFrame(int _currentFrame) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void decrementNumberOfEntities() {
		// TODO Auto-generated method stub
		
	}
	
	public void spawnUFO() {
		ufo[0].pos_x = -100;
		ufo[0].pos_y = 10;
		alive = true;
	}
		
	public UFOEntity() {
	
		dx = 5;
		alive = true;
		timeLapse = GameTime.currentGameTime();
		
	}

	private BoundingBox[] ufo = { BoundingBoxMan.Instance().Get(BoundingBoxName.UFOBox) }; 
	private float dx;
	private long timeLapse;
	public static boolean alive;
	
}
