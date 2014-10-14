package djohnson.invaders.textures;

import djohnson.invaders.game.GameNode;

public class Texture extends GameNode {

    public Texture(TextType type, TextName textName, GameTextures _gText)
    {
    	
    	//this will hold the texture file or font type
        this.gText = _gText;
        
        //name of the image
        this.textName = textName;
        
        //type of image
        this.type = type;
        
        //ensure next link is available
        this.SetNext(null);
        
        //ensure previous link is available
        this.SetPrev(null);
    }
    
    public TextName getTextureName() 		 { return textName; }
    public TextType getTextureType() 		 { return type; 	}
    public GameTextures getGameTexture() 	 { return gText; 	}
    
    private TextName textName;
    private TextType type;
    private GameTextures gText;
}
