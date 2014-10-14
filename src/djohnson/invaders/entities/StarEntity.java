package djohnson.invaders.entities;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;


import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.sprites.GameSprite;



public class StarEntity implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
	
		star[0].colBox.drawContent(g2d, _observer);
		star[0].sprite.drawContent(g2d, _observer);
		
	}

	@Override
	public BoundingBox[] getBoundingBox() {
		return star;
	}
	
	@Override
	public EntityName getEntityName() {
		return EntityName.Star;
	}

	@Override
	public void move() {

		if(moveR) {
			star[0].pos_x += dx;
		}
				
		else {
			star[0].pos_x -= dx;
		}
	}
	
	@Override
	public void accept(CollisionVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public void animate() {
		// Not Implemented
	}
	
	//used for BoundingBox purpose
	public void setMove(boolean value) {
		moveR = value;
	}
	
	public StarEntity() {
		moveR = true;
		dx = 10f;
	}
	
	private boolean moveR;
	private float dx;
	private BoundingBox[] star = { BoundingBoxMan.Instance().Get(BoundingBoxName.StarBox) };
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
	
}
