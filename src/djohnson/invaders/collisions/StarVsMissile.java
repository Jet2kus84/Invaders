package djohnson.invaders.collisions;

import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.ShipEntity;
import djohnson.invaders.entities.StarEntity;

public class StarVsMissile implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Star) {
			star = (StarEntity)_entity;
		}
		else
			return;
		
		if(ShipEntity.loaded && star != null) { 
			if(star.getBoundingBox()[0].colBox.getRec().intersects(
					EntityMan.Instance().get(EntityName.Missile).getBoundingBox()[0].colBox.getRec().getBounds())) {
			
				//notified ship entity that target has been hit
				ShipEntity.loaded = false;
			
				//remove this missile after target is hit
				EntityMan.Instance().remove(EntityMan.Instance().get(
						EntityName.Missile), EntityMan.Instance().REMOVE_RESOURCES, 0);
				
				//now remove the entity the missile collided with
				EntityMan.Instance().remove(star, EntityMan.Instance().REMOVE_RESOURCES, 0);
				EntityMan.Instance().remove(star, EntityMan.Instance().REMOVE_ENTITY, 0);
		
			}
		}
	}

	@Override
	public CollisionType getType() {
		return CollisionType.StarVsMissile;
	}

	private StarEntity star;
}
