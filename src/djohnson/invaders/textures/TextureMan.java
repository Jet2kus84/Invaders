package djohnson.invaders.textures;

public class TextureMan {
	
    //remove all texture resources
    public void Dispose() { 
    	
    	numberOfTextures--;
    }

    //Load textures
    public Texture Load(TextType _type, TextName textName, GameTextures gText)
    {
    	Texture text = null;
    	
        text = new Texture(_type, textName, gText);
        AddTextures(text);
        
        numberOfTextures++;

        return text;
    }

    //get a texture given the name
    public Texture Get(TextName _textName)
    {
        Texture current = first;
        while (current != null)
        {
            if (current.getTextureName() == _textName)
                break;
            current = (Texture)current.GetNext();
        }
        return current;
    }

    //allows access to this class
    public static TextureMan Instance()
    {
            if (instance == null)
                instance = new TextureMan();
            return instance;
    }
    
    //return the number of items in the list
    public int size() { return numberOfTextures; }
    
    //this class can not be instantiated
    private TextureMan()
    {
        instance = null;
        numberOfTextures = 0;
        first = null;
        last = null;
    }

    //add textures to the list
    private void AddTextures(Texture node)
    {
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
    
    private Boolean isEmpty() { return first == null; }
    private Texture first;
    private Texture last;
    private int numberOfTextures;
    private static TextureMan instance;
    
}
