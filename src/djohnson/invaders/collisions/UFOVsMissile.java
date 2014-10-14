package djohnson.invaders.collisions;

import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.entities.UFOEntity;
import djohnson.invaders.game.GameFields;
import djohnson.invaders.scoringSystem.ScoreMan;
import djohnson.invaders.sound.SoundMan;
import djohnson.invaders.sound.SoundName;



public class UFOVsMissile implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.UFO) {
			ufo = (UFOEntity)_entity;
		}
		else
			return;
		
		if(ShipEntity.loaded && ufo != null) { 
			if(ufo.getBoundingBox()[0].colBox.getRec().intersects(
					EntityMan.Instance().get(EntityName.Missile).getBoundingBox()[0].colBox.getRec())) {
			
				//play hit sound
				SoundMan.Instance().get(SoundName.UFOHit).play();
				
				//notified ship entity that target has been hit
				ShipEntity.loaded = false;
			
				//notify ufo that it is no longer alive
				UFOEntity.alive = false;
				
				//restart or add to timer
				
				//set score
				ScoreMan.getInstance().determinePoints(ufo);
				
				//set get font sprite and set the score
				djohnson.invaders.sprites.FontSprite score = (djohnson.invaders.sprites.FontSprite)djohnson.invaders.sprites.SpriteMan.Instance().Get(
						djohnson.invaders.sprites.SpriteName.ActualScore, djohnson.invaders.sprites.SpriteType.FontSprite);
				
				//get the score
				score.setDataString(Integer.toString(ScoreMan.getInstance().getScore()));
				
				//remove this missile after target is hit
				EntityMan.Instance().remove(EntityMan.Instance().get(
						EntityName.Missile), EntityMan.Instance().REMOVE_RESOURCES, 0);
			
				if(GameFields.numberOfInvaders == 0) {
					//now remove the entity the missile collided with
					EntityMan.Instance().remove(ufo, EntityMan.Instance().REMOVE_RESOURCES, 0);
					EntityMan.Instance().remove(ufo, EntityMan.Instance().REMOVE_ENTITY, 0);
				}
			}
		}
		
	}

	@Override
	public CollisionType getType() { 
		return CollisionType.UFOVsMissile; 
	}
	
	private UFOEntity ufo;
}
