package djohnson.invaders.images;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import djohnson.invaders.game.GameNode;
import djohnson.invaders.textures.Texture;

public class MyImage extends GameNode {
	
     public MyImage(Texture _text, int x, int y, int _width, int _height)
     {
    	 //initialize with texture resource
         text = _text;
         
    	 //get image from file and place into the image object
    	 _image = Toolkit.getDefaultToolkit().getImage((String) text.getGameTexture().gText());
         
         //create a source rectangle
         source = new Rectangle(x, y, _width, _height);
       
     }
     
     public Image getImage() 			  { return _image; 			}
     public Rectangle getSource()   	  { return source; 		    }
     public int getWidth() 		   	      { return source.width;    }
     public int getHeight()		    	  { return source.height; 	}
     public Texture getText() 			  { return text;   			}
     
     public void setImage(Image newImage) { this._image = newImage; } 
     
     private Texture text;
     private Rectangle source;
     private Image _image;
}
