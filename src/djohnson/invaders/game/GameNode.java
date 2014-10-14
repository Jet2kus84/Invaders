package djohnson.invaders.game;

public class GameNode {

	private GameNode next;
    private GameNode prev;

    public void SetNext(GameNode node) { next = node; }

    public GameNode GetNext() { return next; }

    public void SetPrev(GameNode node) { prev = node; }

    public GameNode GetPrev() { return prev; }
    
}
