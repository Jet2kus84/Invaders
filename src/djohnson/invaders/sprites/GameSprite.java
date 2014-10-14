package djohnson.invaders.sprites;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import djohnson.invaders.images.MyImage;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.textures.TextName;


public class GameSprite extends Sprite {

     public GameSprite() {
      
         this.name = SpriteName.Not_Initialized;
         this.color = Color.BLACK;
         
         image = null;
      
     }
     
     //other options
     public GameSprite(SpriteName _name, MyImage _image) {
    	 this.color = Color.BLACK;
    	 this.name = _name;
    	 this.image = _image;
     }
     
     public void Set(SpriteName _name, TextName imageName, MyImageMan imageMan) {
        
    	 this.name = _name;
         this.image = imageMan.Get(imageName);
     }

     @Override
 	public SpriteType GetSpriteType() {
 		return SpriteType.GameSprite;
 	}

 	@Override //subject to change to accommodate animations
 	public void drawContent(Graphics2D _sprite, ImageObserver io) {
 	
 		_sprite.drawImage(image.getImage(), 
 					      image.getSource().x,
 						  image.getSource().y, io);
 		
 	}
 	
 	
 	
 	public void setImage(MyImage _image) {
 		this.image = _image;
 	}
 	
 	public MyImage getMyImage() {
 		return image;
 	}
 	
    public Color color;
    private MyImage image;
}
