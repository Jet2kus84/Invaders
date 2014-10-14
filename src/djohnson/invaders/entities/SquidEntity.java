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




public class SquidEntity extends AlienUser implements Entity {

	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
		
		for(BoundingBox b : newSquid) {
			b.colBox.drawContent(g2d, _observer);
			b.sprite.drawContent(g2d, _observer);
		}
	}

	@Override
	public EntityName getEntityName() {
		return EntityName.Squid;
	}

	@Override
	public BoundingBox[] getBoundingBox() {
		return newSquid;
	}
	
	@Override
	public void move() {
		
		for(BoundingBox d : newSquid) {
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

			for(int i = 0; i < GameFields.numberOfSquids; i++) {
				
				//input first second of images
				this.newSquid[i].sprite = this.sprite[currentFrame]; 
				
				// if all squids are load with first image, start over & load second image
				if(i > GameFields.numberOfSquids-1) i = 0; 
				
				currentFrame++;
				
				//animation is complete, start the animation over
				if (currentFrame > this.sprite.length-1)
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
		for(BoundingBox b : this.newSquid) {
			b.pos_y += value;
		}
	}
	
	@Override
	public void decrementNumberOfEntities() {
		GameFields.numberOfSquids--;
		GameFields.numberOfInvaders --;
		GameFields.dx += GameFields.speedIncrease;
	}
	
	@Override
	public void setBoundingBox(BoundingBox[] value) {
		this.newSquid = value;
	}
	
	@Override
	public int numberOfEntities() {
		return GameFields.numberOfSquids;
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
		
	public SquidEntity() {
		
		//initialize the mediator
		super(AlienMediator.getInstance(), EntityName.Squid);
		
		currentFrame = 0;
	
		timeLapse = GameTime.currentGameTime();
	
		//initial bounding box squid array
		newSquid = new BoundingBox[GameFields.numberOfSquids];
		
		//initialize first squid sprite array
		squidSprite = new GameSprite[GameFields.numberOfSquids];
			
		//initialize squid animation array
		sprite = new GameSprite[GameFields.numberOfSquids*2];

		int j = 0;
		for(int i = 0; i < sprite.length; i++) {
			
			if(i < GameFields.numberOfSquids) {
				
				//create and load new bounding boxes with new sprite & sprites with new images
				newSquid[i] = BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.SquidBox, 
						squidSprite[i] = //initialize sprite variable as well
						SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Squid, MyImageMan.Instance().AddImages(
						TextureMan.Instance().Get(TextName.Squid), 
						GameFields.startPosition_X, GameFields.startPosition_Y, 40, 30)), 
						new Rectangle(
						squidSprite[i].getMyImage().getSource().x+12,
						squidSprite[i].getMyImage().getSource().y+17,
						squidSprite[i].getMyImage().getWidth(),
						squidSprite[i].getMyImage().getHeight()), Color.WHITE); 
			
				newSquid[i].pos_x += j;
				j += GameFields.distanceX; //increment distance between squid
			}
			
			//load in the two different sprites needed for animation
			if(i < GameFields.numberOfSquids) {
				//load first set of squid sprites
				sprite[i] = squidSprite[i];
				
			} else {
				//load second set of squid sprites
				sprite[i] = SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Squid, MyImageMan.Instance().AddImages(
								TextureMan.Instance().Get(TextName.Squid2), 10, 60, 40, 30));
				
			}
		}
	}
	
	private GameSprite[] sprite;
	private BoundingBox[] newSquid;
	private GameSprite[] squidSprite;
	private int currentFrame;
	private long timeLapse;
	
}
