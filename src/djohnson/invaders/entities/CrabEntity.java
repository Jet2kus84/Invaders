package djohnson.invaders.entities;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.collisions.CollisionVisitor;
import djohnson.invaders.game.GameFields;
import djohnson.invaders.game.GameTime;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.mediators.AlienMediator;
import djohnson.invaders.mediators.AlienUser;
import djohnson.invaders.sprites.GameSprite;
import djohnson.invaders.sprites.SpriteMan;
import djohnson.invaders.sprites.SpriteName;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.TextureMan;




public class CrabEntity extends AlienUser implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
		
		for(BoundingBox b : newCrab) {
			b.colBox.drawContent(g2d, _observer);
			b.sprite.drawContent(g2d, _observer);
		}
	}

	@Override
	public EntityName getEntityName() {
		return EntityName.Crab;
	}

	@Override
	public BoundingBox[] getBoundingBox() {
		return newCrab;
	}
	
	@Override
	public void move() {
		
		for(BoundingBox d : newCrab) {
			if(GameFields.moveR) {
				d.pos_x += GameFields.dx;
			}	
			else {
				d.pos_x -= GameFields.dx;
			}
		}	
	}

	@Override
	public void accept(CollisionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void animate() {
		
		if(GameTime.currentGameTime() - timeLapse > GameFields.invadersFrameSpeed) {

			for(int i = 0; i < GameFields.numberOfCrabs; i++) {
				
				//input first second of images
				this.newCrab[i].sprite = this.sprite[currentFrame];
				
				// if all Crabs are load with first image, start over & load second image
				if(i > GameFields.numberOfCrabs-1) i = 0; 
				
				currentFrame++;
				
				//animation is complete, start the animation over
				if (currentFrame > this.sprite.length - 1) 
					currentFrame = 0;
						
				//restart the timer
				timeLapse = GameTime.currentGameTime();
			}
		}
	}
	
	@Override
	public void send(boolean signal) {
		this.mediator.sendMessage(signal, this);
	}

	@Override
	public void send(float value) {
		this.mediator.sendMessage(value, this);
	}

	@Override
	public void receive(boolean signal) {
		GameFields.moveR = signal;
	}

	@Override
	public void receive(float value) {
		for(BoundingBox b : this.newCrab) {
			b.pos_y += value;
		}
	}
	
	@Override
	public void decrementNumberOfEntities() {
		GameFields.numberOfCrabs--;
		GameFields.numberOfInvaders --;
		GameFields.dx += GameFields.speedIncrease;
	}
	
	@Override
	public void setBoundingBox(BoundingBox[] value) {
		this.newCrab = value;
	}
	
	@Override
	public int numberOfEntities() {
		return GameFields.numberOfCrabs;
	}
	
	@Override
	public void setSprite(GameSprite[] _sprite) {
		this.sprite = _sprite;
	}
	
	@Override
	public GameSprite[] getSprite() {
		return this.sprite;
	}
	
	@Override
	public void setCurrentFrame(int _currentFrame) {
		this.currentFrame = _currentFrame;
	}
	
	@Override
	public int getCurrentFrame() {
		return this.currentFrame;
	}
	
	public CrabEntity() {
		
		//initialize the mediator
		super(AlienMediator.getInstance(), EntityName.Crab);
		
		timeLapse = GameTime.currentGameTime();
		
		//initial bounding box Crab array
		newCrab = new BoundingBox[GameFields.numberOfCrabs];
		
		//initialize first Crab sprite array
		crabSprite = new GameSprite[GameFields.numberOfCrabs];
			
		//initialize Crab animation array
		sprite = new GameSprite[GameFields.numberOfCrabs*2];

		int j = 0; 
		for(int i = 0; i < sprite.length; i++) {
		
			if(i < GameFields.numberOfCrabs) {
				//create and load new bounding boxes with new sprite & sprites with new images
				newCrab[i] = BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.CrabBox, 
						crabSprite[i] = //initialize sprite variable as well
						SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Crab, MyImageMan.Instance().AddImages(
						TextureMan.Instance().Get(TextName.Crab), 
						GameFields.startPosition_X, (GameFields.startPosition_Y + 40), 40, 30)), 
						new Rectangle(
						crabSprite[i].getMyImage().getSource().x+12,
						crabSprite[i].getMyImage().getSource().y+17,
						crabSprite[i].getMyImage().getWidth(),
						crabSprite[i].getMyImage().getHeight()), Color.WHITE); 
		
				//if the first row of crabs are positioned, start on the next row and reset the position
				if(i == GameFields.numberOfCrabs*.50f) j = 0;
				
				//if first row finished draw second row
				if(i > ((GameFields.numberOfCrabs*.50f) -1 )) {
					newCrab[i].pos_x += j;
					newCrab[i].pos_y += GameFields.distanceY; //drop second row to this distance
				}
				else {
					//position first row
					newCrab[i].pos_x += j;
				}
				j += GameFields.distanceX; //increment distance between crabs
			}
	
			//load in the two different sprites needed for animation
			if(i < GameFields.numberOfCrabs) {
				//load first set of Crab sprites
				sprite[i] = crabSprite[i];
				
			} else {
				//load second set of crab sprites
				sprite[i] = SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Crab, MyImageMan.Instance().AddImages(
								TextureMan.Instance().Get(TextName.Crab2), 10, 100, 40, 30));
				
			}
		}
	}
	
	private GameSprite[] sprite;
	private int currentFrame = 0;
	private BoundingBox[] newCrab;
	private GameSprite[] crabSprite;
	private long timeLapse;
	
}
