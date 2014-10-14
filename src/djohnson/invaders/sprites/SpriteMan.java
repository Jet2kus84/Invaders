package djohnson.invaders.sprites;


import java.awt.Color;

import djohnson.invaders.images.MyImage;
import djohnson.invaders.images.MyImageMan;
import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.Texture;

public class SpriteMan {
	
    public static SpriteMan Instance() {
    	
    	if (instance == null)
    		instance = new SpriteMan();
    	return instance;
    }

    public void Remove(Sprite node)
    {
        Sprite current = (Sprite)first.GetNext();

        //Check first item in list
        if (first == node)
            first = (Sprite)first.GetNext();

        //check all other values
        while (current != null)
        {
            if (current == node)
            {
                if (current.GetPrev() != null)
                    current.GetPrev().SetNext(current.GetNext());

                if (current.GetNext() != null)
                    current.GetNext().SetPrev(current.GetPrev());
                break;
            }
            current = (Sprite)current.GetNext();
        }
        numberOfSprites--;
    }

    public GameSprite AddGameSprite(SpriteName _name, TextName imageName, MyImageMan imageMan)
    {
        GameSprite gSprite = new GameSprite();
        
        gSprite.Set(_name, imageName, imageMan);
        
        Add(gSprite);
        
        numberOfSprites++;
        
        return gSprite;
    }

    public GameSprite AddSquidSprite(SpriteName _name, MyImage image) {
    	
    	GameSprite gSprite = new GameSprite(_name, image);
    	
    	Add(gSprite);
    	
    	numberOfSprites++;
    	
    	return gSprite;
    }
    public FontSprite AddFontSprite(SpriteName _name, Texture text, String message, float _xLoc, float _yLoc, Color _color) 
    {
        FontSprite fSprite = new FontSprite(_name, text, message, _xLoc, _yLoc, _color);

        Add(new FontSprite(_name, text, message, _xLoc, _yLoc, _color));

        numberOfSprites++;

        return fSprite;
    }

    public Sprite Get(SpriteName _name, SpriteType _type) {
    	
        Sprite current = first;

        while (current != null)
        {
            if ((current.GetSpriteType() == _type) && (current.name == _name))
                break;
            current = (Sprite)current.GetNext();
        }
        return current;
    }
    
    public int size() { return numberOfSprites; }
    
    private void Add(Sprite node) {
    	
        if (isEmpty())
        {
            node.SetNext(null);
            node.SetPrev(null);

            first = node;
            last = node;
        }
        else
        {
            node.SetNext(null);
            node.SetPrev(last);

            last.SetNext(node);
            last = node;
        }
    }
    
    private SpriteMan() {
    	
        first = null;
        last = null;
        instance = null;
        numberOfSprites = 0;
    }
    
    private Boolean isEmpty() { return first == null; }
    
    private int numberOfSprites;
    private Sprite first;
    private Sprite last;
    private static SpriteMan instance;
    
}
