package djohnson.invaders.entities;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.drawContent.Draw;
import djohnson.invaders.sprites.GameSprite;

public interface Entity extends Draw {

	public EntityName getEntityName();
	
	public BoundingBox[] getBoundingBox();
	
	public void move();
	
	public void accept(CollisionVisitor visitor);
	
	public void animate();
	
	public void decrementNumberOfEntities();
	
	public void setBoundingBox(BoundingBox[] value);
	
	public int numberOfEntities();
	
	public void setSprite(GameSprite[] _sprite);
	
	public GameSprite[] getSprite();
	
	public void setCurrentFrame(int _currentFrame);
	
	public int getCurrentFrame();
}
