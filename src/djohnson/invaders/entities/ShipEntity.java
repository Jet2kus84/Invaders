package djohnson.invaders.entities;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;


import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.SoundName;
import djohnson.invaders.sprites.GameSprite;



public class ShipEntity implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
		
		ship[0].colBox.drawContent(g2d, _observer);
		
		g2d.drawImage(ship[0].sprite.getMyImage().getImage(), 
				 (int)ship[0].pos_x, 
				 (int)ship[0].pos_y, _observer);
		
	}
	
	@Override
	public void move() {
		
		ship[0].pos_x += dx;
		ship[0].colBox.getRec().x += dx;		
	}
	
	@Override
	public EntityName getEntityName()  { 
		return EntityName.Ship; 
	}
	
	@Override
	public void accept(CollisionVisitor visitor) {
		visitor.visit(this);
	}
	
	public void fire() {
		
		if(!loaded) {
			EntityMan.Instance().addEntity(new MissileEntity( ((int)ship[0].pos_x + offset),
					(int)ship[0].pos_y - (offset/5))); 
			loaded = true;
			SoundMan.Instance().get(SoundName.ShipShot).play();
		}
	}
	
	//return this entity's BoundingBox
	@Override
	public BoundingBox[] getBoundingBox() { 
		return ship;
	}
	
	@Override
	public void animate() {
		//implement for when ship is hit
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
	
	public void setDirectionX(float xAxis) { 
		this.dx = xAxis; 
	}
	
	public ShipEntity() {
		
		offset = 20;
		dx = 0;
		loaded = false;
	}
	
	private float dx;
	private int offset;
	public static boolean loaded;
	private BoundingBox[] ship = { 
			BoundingBoxMan.Instance().Get(BoundingBoxName.ShipBox) 
	};
	
}
