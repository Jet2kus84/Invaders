package djohnson.invaders.entities;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.ImageObserver;



import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.drawContent.DrawMan;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.sprites.GameSprite;
import djohnson.invaders.sprites.SpriteMan;
import djohnson.invaders.sprites.SpriteName;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.TextureMan;



public class MissileEntity implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
		
		if(ShipEntity.loaded) { 
			this.missile[0].colBox.drawContent(g2d, _observer);
			
			g2d.drawImage(this.missile[0].sprite.getMyImage().getImage(),
					(int)missile[0].pos_x, 
					(int)missile[0].pos_y, _observer);
			
		}
		else 
			return;
	}

	@Override
	public void move() {
		
		if(ShipEntity.loaded) {
			
			this.missile[0].pos_y += dy;
			this.missile[0].colBox.getRec().y += dy;
		}
	}
	
	@Override
	public BoundingBox[] getBoundingBox() {
		return missile;
	}	
	
	@Override
	public EntityName getEntityName() { 
		return EntityName.Missile;	
	}
		
	@Override
	public void accept(CollisionVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public void animate() {
		// Not Implemented
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
	
	public MissileEntity(int _x, int _y) {
			
		this.missile = new BoundingBox[NUMBER_OF_MISSILES_ALLOWED_PER_SHOT];
		
		GameSprite mG = SpriteMan.Instance().AddSquidSprite(SpriteName.NonAnimation_Missiles, 
				MyImageMan.Instance().AddImages(TextureMan.Instance().Get(TextName.Missiles), _x, _y, 5, 10));
		
		//load missile BoundingBox
		this.missile[0] = BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.MissileBox, 
						(GameSprite)mG, 
						new Rectangle(
						mG.getMyImage().getSource().x+2,
						mG.getMyImage().getSource().y+1,
						mG.getMyImage().getWidth(),
						mG.getMyImage().getHeight()), Color.GREEN);
		
		//missile speed
		dy = -10;
		
		DrawMan.Instance().addDrawComponent(this);
	}
		
	private float dy;
	private final int NUMBER_OF_MISSILES_ALLOWED_PER_SHOT = 1;
	private BoundingBox[] missile;
	
}
