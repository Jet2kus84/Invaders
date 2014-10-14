package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.AlienMissileEntity;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;

public class AlienMissileVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.AlienMissile) {
			this.missile = (AlienMissileEntity)EntityMan.Instance().get(EntityName.AlienMissile);
		}
		else
			return;
			
		if(AlienMissileEntity.canShoot) {
			if(this.missile.getBoundingBox()[0].pos_y > 
					BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMaxY()) {
			
				//notified ship entity that target has been hit
				AlienMissileEntity.canShoot = false;
				
				//remove this missile after target is hit
				EntityMan.Instance().remove(missile, EntityMan.Instance().REMOVE_RESOURCES, 0);
				EntityMan.Instance().remove(missile, EntityMan.Instance().REMOVE_ENTITY, 0);
				
				
				
			}
		}
	}

	@Override
	public CollisionType getType() {
		return CollisionType.MissileVsWall;
	}

	public AlienMissileVsWall() {
		missile = null;
	}
	
	private AlienMissileEntity missile;
}
