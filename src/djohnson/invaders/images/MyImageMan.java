package djohnson.invaders.images;

import djohnson.invaders.textures.TextName;
import djohnson.invaders.textures.Texture;

final public class MyImageMan {

    public MyImage AddImages(Texture text, int x, int y, int width, int height)
    {
        MyImage image = new MyImage(text, x, y, width, height);

        if (isEmpty())
        {
            image.SetNext(null);
            image.SetPrev(null);

            first = image;
            last = image;
        }
        else
        {
            image.SetNext(null);
            image.SetPrev(last);

            last.SetNext(image);
            last = image;
        }

        numberOfImages++;
        
        return image;
    }

    public void Remove(MyImage node)
    {
        MyImage current = (MyImage)first.GetNext();

        //Check first item in list
        if (first == node)
            first = (MyImage)first.GetNext();

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
            current = (MyImage)current.GetNext();
        }
        numberOfImages--;
    }
    
    public MyImage Get(TextName name)
    {
        MyImage current = first;

        while (current != null)
        {
            if (current.getText().getTextureName() == name)
                break;
            current = (MyImage)current.GetNext();
        }

        return current;
    }
    
    public int size() { return numberOfImages; }
    
    public static MyImageMan Instance()
    {
            if (instance == null)
                instance = new MyImageMan();
            return instance;
    }
    
    private MyImageMan()
    {
        first = null;
        last = null;
        numberOfImages = 0;
        instance = null;
    }

    private MyImage first;
    private MyImage last;
    private int numberOfImages;
    private static MyImageMan instance;
    private Boolean isEmpty() { return first == null; }
    
}
