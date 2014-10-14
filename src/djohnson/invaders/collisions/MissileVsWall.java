package djohnson.invaders.collisions;

import djohnson.invaders.boundingbox.BoundingBoxMan;
import djohnson.invaders.boundingbox.BoundingBoxName;
import djohnson.invaders.entities.Entity;
import djohnson.invaders.entities.EntityMan;
import djohnson.invaders.entities.EntityName;
import djohnson.invaders.entities.MissileEntity;
import djohnson.invaders.entities.ShipEntity;

public class MissileVsWall implements CollisionVisitor {

	@Override
	public void visit(Entity _entity) {
		
		if(_entity.getEntityName() == EntityName.Missile) {
			this.missile = (MissileEntity)EntityMan.Instance().get(EntityName.Missile);
		}
		else
			return;
			
		if(ShipEntity.loaded) {
			if(this.missile.getBoundingBox()[0].pos_y < 
					BoundingBoxMan.Instance().Get(BoundingBoxName.WallBox).colBox.getRec().getMinY()) {
			
				//notified ship entity that target has been hit
				ShipEntity.loaded = false;
				
				//remove this missile after target is hit
				EntityMan.Instance().remove(EntityMan.Instance().get(
						EntityName.Missile), EntityMan.Instance().REMOVE_RESOURCES, 0);
			}
		}
	}

	@Override
	public CollisionType getType() {
		return CollisionType.MissileVsWall;
	}

	public MissileVsWall() {
		
		missile = null;
	}
	
	private MissileEntity missile;
}
