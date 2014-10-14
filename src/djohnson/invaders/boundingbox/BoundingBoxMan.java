package djohnson.invaders.boundingbox;

import java.awt.Color;
import java.awt.Rectangle;

import djohnson.invaders.sprites.GameSprite;


public class BoundingBoxMan {
	
    public BoundingBox AddBoundingBox(BoundingBoxName name, GameSprite gSprite, Rectangle colRec, Color colColor)
    {
    	BoundingBox col = new BoundingBox();
        col.Set(name, gSprite, colRec, colColor);

        if (isEmpty())
        {
            col.SetNext(null);
            col.SetPrev(null);

            first = col;
            last = col;
        }
        else
        {
            col.SetNext(null);
            col.SetPrev(last);

            last.SetNext(col);
            last = col;
        }
        numberOfBoundingBoxObj++;
        
        return col;
    }

    public void RemoveBoundingBoxObj(BoundingBox node)
    {
    	BoundingBox current = (BoundingBox)first.GetNext();

        //Check first item in list
        if (first == node)
            first = (BoundingBox)first.GetNext();

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
            current = (BoundingBox)current.GetNext();
        }
        
        numberOfBoundingBoxObj--;
    }

    public BoundingBox Get(BoundingBoxName _name)
    {
    	BoundingBox current = first;

        while (current != null)
        {
            if ((current.name == _name))
                break;
            current = (BoundingBox)current.GetNext();
        }
        return current;
    }

    public void UpdateBoundingBoxList()
    {
    	BoundingBox current = first;

        while (current != null)
        {
            current.Update();

            current = (BoundingBox)current.GetNext();
        }

    }
    
    public int size() { return numberOfBoundingBoxObj; }
    
    public static BoundingBoxMan Instance()
    {
    	if(instance == null)
    		instance = new BoundingBoxMan();
    	return instance;
    }

    private BoundingBoxMan()
    {
        numberOfBoundingBoxObj = 0;
        first = null;
        last = null;
        instance = null;
    }
    
    private int numberOfBoundingBoxObj;
    private BoundingBox first;
    private BoundingBox last;
    private static BoundingBoxMan instance;
    private Boolean isEmpty() { return first == null; }
    
}
