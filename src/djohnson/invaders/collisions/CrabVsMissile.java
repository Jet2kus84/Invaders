package djohnson.invaders.collisions;

import java.util.Arrays;
import java.util.LinkedList;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.entities.CrabEntity;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.scoringSystem.ScoreMan;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.SoundName;
import djohnson.invaders.sprites.GameSprite;




public class CrabVsMissile implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Crab) {
			this.crab = (CrabEntity)_entity;
		}
		else
			return;
		
		if(ShipEntity.loaded && this.crab != null) { 
			for(int i = 0; i < crab.getBoundingBox().length; i++) {
				
				if(crab.getBoundingBox()[i].colBox.getRec().intersects(
						EntityMan.Instance().get(EntityName.Missile).getBoundingBox()[0].colBox.getRec().getBounds())) {
			
						//play hit sound
						SoundMan.Instance().get(SoundName.AlienKilled).play();
						
						//notified ship entity that target has been hit
						ShipEntity.loaded = false;
					
						//remove this missile after target is hit
						EntityMan.Instance().remove(EntityMan.Instance().get(EntityName.Missile), EntityMan.Instance().REMOVE_RESOURCES, 0);
						
						//remove crab resources
						EntityMan.Instance().remove(crab, EntityMan.Instance().REMOVE_RESOURCES, i);//EntityMan.Instance().removeResource(crab, i);
						
						//change array into a list
						LinkedList<BoundingBox> crabBoxList = new LinkedList<BoundingBox>(Arrays.asList(crab.getBoundingBox()));
						
						//change animation sprite array into a list
						LinkedList<GameSprite> crabSpriteList = new LinkedList<GameSprite>(Arrays.asList(crab.getSprite()));
						
						//remove first animation type, first half of animations are first images for animation
						crabSpriteList.removeFirst();
						
						//remove second animation type, second half of animations are second images for animation
						crabSpriteList.removeLast();
						
						//remove Crab hit from the list(array)
						crabBoxList.remove(i);
						
						//decrement the # of Crab
						crab.decrementNumberOfEntities();
						
						//return list as array back to the entity class
						crab.setBoundingBox(crabBoxList.toArray(new BoundingBox[0]));
						
						//return list2 as array back to the entity class
						crab.setSprite(crabSpriteList.toArray(new GameSprite[0]));
						
						//reset current animation frame, this will 
						//prevent random frame count when removing animation sprites
						crab.setCurrentFrame(0);
							
						//set score
						ScoreMan.getInstance().determinePoints(crab);
						
						//set get font sprite and set the score
						djohnson.invaders.sprites.FontSprite score = (djohnson.invaders.sprites.FontSprite)djohnson.invaders.sprites.SpriteMan.Instance().Get(
								djohnson.invaders.sprites.SpriteName.ActualScore, djohnson.invaders.sprites.SpriteType.FontSprite);
						
						//get the score
						score.setDataString(Integer.toString(ScoreMan.getInstance().getScore()));
						
						if(crab.numberOfEntities() == 0) {
						
							//now remove the entity the missile collided with
							EntityMan.Instance().remove(this.crab, EntityMan.Instance().REMOVE_ENTITY, 0);
							
							//remove this collision check because their are no more crab
							CollisionMan.Instance().dettach(this);
						}
						break;
				}
			} 
		}
	} 

	@Override
	public CollisionType getType() {
		return CollisionType.CrabVsMissile;
	}

	private CrabEntity crab;
}
