package djohnson.invaders.entities;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;


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




public class OctopusEntity extends AlienUser implements Entity {

	/*
	 * 
	 * 	how does the octopus tell the crab that it can shoot? by position
	 * 	who tells the octopus to shoot?
	 */
	@Override
	public void drawContent(Graphics2D g2d, ImageObserver _observer) {
		for(BoundingBox b : newOctopus) {
			b.colBox.drawContent(g2d, _observer);
			b.sprite.drawContent(g2d, _observer);
		}
	}

	@Override
	public EntityName getEntityName() {
		return EntityName.Octopus;
	}

	@Override
	public BoundingBox[] getBoundingBox() {
		return newOctopus;
	}
	
	@Override
	public void move() {
		
		for(BoundingBox d : newOctopus) {
			if(GameFields.moveR) {
				d.pos_x += GameFields.dx;
			}	
			else {
				d.pos_x -= GameFields.dx;
			}
			
			/*
			 * 
			 *  needed for alien shooting
			 *  bootomRow = rand.nextInt((GameFields.numberOfOctopus-1) + (GameFields.numberOfOctopus/2))];
			 *  topRow = rand.nextInt((GameFields.numberOfOctopus/11)-1));
			 */
			//TRYING OUT SHOOT WHILE MOVING
			if(!AlienMissileEntity.canShoot && (GameFields.numberOfOctopus >= 0)) {
				if(GameTime.currentGameTime() - timeLapse > GameFields.invadersFrameSpeed) {
					
					//create a new random # generator every time the game updates
					rand = new Random();
					
					//get the current # of octopus entities
					int numberOfOctopusLeft = GameFields.numberOfOctopus - 1;
					
					//at one more count to account for array start at zero
					if(numberOfOctopusLeft == 0) numberOfOctopusLeft++;
					
					//initialize value with a random # from 0-11
					int value = rand.nextInt(numberOfOctopusLeft) + (GameFields.numberOfOctopus >> 1);
					
					//ensure that value is not less than the current # of octopus entities
					if(value < GameFields.numberOfOctopus)
						fire(newOctopus[value]); //initialize array with random value
						
					//restart the timer
					timeLapse = GameTime.currentGameTime();
				}
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

			for(int i = 0; i < GameFields.numberOfOctopus; i++) {
				
				//input first second of images
				this.newOctopus[i].sprite = this.sprite[currentFrame];
				
				// if all Octopus are load with first image, start over & load second image
				if(i > GameFields.numberOfOctopus-1) i = 0; 
				
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
		for(BoundingBox b : this.newOctopus) {
			b.pos_y += value;
		}
	}
	
	@Override
	public void decrementNumberOfEntities() {
		GameFields.numberOfOctopus--;
		GameFields.numberOfInvaders --;
		GameFields.dx += GameFields.speedIncrease;
	}
	
	@Override
	public void setBoundingBox(BoundingBox[] value) {
		this.newOctopus = value;
	}
	
	@Override
	public int numberOfEntities() {
		return GameFields.numberOfOctopus;
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
	
	public void fire(BoundingBox d) {
		EntityMan.Instance().addEntity(new AlienMissileEntity(
				(int)d.pos_x+30, (int)d.pos_y+40));
	}
	
	public OctopusEntity() {
		
		//initialize the mediator
		super(AlienMediator.getInstance(), EntityName.Octopus);
		
		timeLapse = GameTime.currentGameTime();
		
		//initial bounding box Octopus array
		newOctopus = new BoundingBox[GameFields.numberOfOctopus];
		
		//initialize first Octopu sprite array
		octopusSprite = new GameSprite[GameFields.numberOfOctopus];
			
		//initialize Octopu animation array
		sprite = new GameSprite[GameFields.numberOfOctopus << 1];

		int j = 0; 
		for(int i = 0; i < sprite.length; i++) {
		
			if(i < GameFields.numberOfOctopus) {
				//create and load new bounding boxes with new sprite & sprites with new images
				newOctopus[i] = BoundingBoxMan.Instance().AddBoundingBox(BoundingBoxName.OctopusBox, 
						octopusSprite[i] = //initialize sprite variable as well
						SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Octopus, MyImageMan.Instance().AddImages(
						TextureMan.Instance().Get(TextName.Octopus), 
						GameFields.startPosition_X, (GameFields.startPosition_Y + 120), 40, 30)), 
						new Rectangle(
						octopusSprite[i].getMyImage().getSource().x+12,
						octopusSprite[i].getMyImage().getSource().y+17,
						octopusSprite[i].getMyImage().getWidth(),
						octopusSprite[i].getMyImage().getHeight()), Color.WHITE); 
		
				//if the first row of Octopuss are positioned, start on the next row and reset the position
				if(i == GameFields.numberOfOctopus*.50f) j = 0;
				
				//if first row finished draw second row
				if(i > ((GameFields.numberOfOctopus*.50f) -1 )) {
					newOctopus[i].pos_x += j;
					newOctopus[i].pos_y += GameFields.distanceY; //increment distance between Octopus y-axis
					//System.out.println("(" + newOctopus[i].pos_x + ", " + newOctopus[i].pos_y + ")");
				}
				else {
					//position first row
					newOctopus[i].pos_x += j;
					//System.out.println("(" + newOctopus[i].pos_x + ", " + newOctopus[i].pos_y + ")");
				}
				j += GameFields.distanceX; //increment distance between Octopus x-axis
			}
	
			//load in the two different sprites needed for animation
			if(i < GameFields.numberOfOctopus) {
				//load first set of Octopus sprites
				sprite[i] = octopusSprite[i];
				
			} else {
				//load second set of Octopus sprites
				sprite[i] = SpriteMan.Instance().AddSquidSprite(
						SpriteName.Animation_Octopus, MyImageMan.Instance().AddImages(
								TextureMan.Instance().Get(TextName.Octopus2), 10, 100, 40, 30));
			}
		}
	}
	
	private static class Octopus {
		static BoundingBox entity;
		static boolean killed = false;
	}
	
	private Octopus[] _octopus;
	////////////////////////////////
	private GameSprite[] sprite;
	private int currentFrame = 0;
	private BoundingBox[] newOctopus;
	private final GameSprite[] octopusSprite;
	private long timeLapse;
	private Random rand;
}
