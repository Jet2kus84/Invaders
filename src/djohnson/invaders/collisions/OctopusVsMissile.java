package djohnson.invaders.collisions;

import java.util.Arrays;
import java.util.LinkedList;

import djohnson.invaders.boundingbox.BoundingBox;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.OctopusEntity;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.scoringSystem.ScoreMan;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.SoundName;
import djohnson.invaders.sprites.GameSprite;

public class OctopusVsMissile implements CollisionVisitor {
	
	@Override
	public void visit(Entity _entity) {
	
		if(_entity.getEntityName() == EntityName.Octopus) {
			this.octopus = (OctopusEntity)_entity;
		}
		else
			return;
		
		if(ShipEntity.loaded && this.octopus != null) { 
			for(int i = 0; i < octopus.getBoundingBox().length; i++) {
				
				if(octopus.getBoundingBox()[i].colBox.getRec().intersects(
						EntityMan.Instance().get(EntityName.Missile).getBoundingBox()[0].colBox.getRec().getBounds())) {
			
						//play hit sound
						SoundMan.Instance().get(SoundName.AlienKilled).play();
						
						//notified ship entity that target has been hit
						ShipEntity.loaded = false;
					
						//remove this missile after target is hit
						EntityMan.Instance().remove(EntityMan.Instance().get(EntityName.Missile), EntityMan.Instance().REMOVE_RESOURCES, 0);
						
						//remove this octopus' resources
						EntityMan.Instance().remove(octopus, EntityMan.Instance().REMOVE_RESOURCES, i);
						
						//change array into a list
						LinkedList<BoundingBox> octopusBoxList = new LinkedList<>(Arrays.asList(octopus.getBoundingBox()));
						
						//change animation sprite array into a list
						LinkedList<GameSprite> octopusSpriteList = new LinkedList<>(Arrays.asList(octopus.getSprite()));
						
						//remove first animation type, first half of animations are first images for animation
						octopusSpriteList.removeFirst();
						
						//remove second animation type, second half of animations are second images for animation
						octopusSpriteList.removeLast();
						
						//remove the octopus that was hit from the list(entity class array)
						octopusBoxList.remove(i);
						
						//decrement the # of octopus  
						octopus.decrementNumberOfEntities();
						
						//return list as array back to the entity class
						octopus.setBoundingBox(octopusBoxList.toArray(new BoundingBox[0]));
						
						//return list2 as array back to the entity class
						octopus.setSprite(octopusSpriteList.toArray(new GameSprite[0]));
						
						//reset current animation frame, this will 
						//prevent random frame count when removing animation sprites
						octopus.setCurrentFrame(0);
					
						//set score
						ScoreMan.getInstance().determinePoints(octopus);
						
						//set get font sprite and set the score
						djohnson.invaders.sprites.FontSprite score = (djohnson.invaders.sprites.FontSprite)djohnson.invaders.sprites.SpriteMan.Instance().Get(
								djohnson.invaders.sprites.SpriteName.ActualScore, djohnson.invaders.sprites.SpriteType.FontSprite);
						
						//get the score
						score.setDataString(Integer.toString(ScoreMan.getInstance().getScore()));
						
						if(octopus.numberOfEntities() == 0) {
						
							//now remove the entity the missile collided with
							EntityMan.Instance().remove(this.octopus, EntityMan.Instance().REMOVE_ENTITY, 0);
											
							//remove this collision check because their are no more octopus
							CollisionMan.Instance().dettach(this);
						}
						break;
				}
			} 
		}
	} 

	@Override
	public CollisionType getType() {
		return CollisionType.OctopusVsMissile;
	}
		
	private  OctopusEntity octopus;
	
}
