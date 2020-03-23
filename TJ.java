
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class TJ extends BouncingGameObject {
	
    private static int numTJs = 0;
    private static final int MAX_TJS = 50;

	public TJ(int x, int y) {
		super(x, y, "TJ.png");
        numTJs++;
        dx = GameEngine.rng.nextInt(51) - 25;
        dy = GameEngine.rng.nextInt(51) - 25;
	}
	
    /**
     * Returns the age of this TJ.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 13;
    }
    

    /**
     * When this TJ hits a wall, clone him (unless we're already at the maximum
     * amount of TJ-ness the universe can handle).
     */
    protected void hitSide() {
        if (numTJs < MAX_TJS) {
            GameEngine.instance().addObject(new TJ(x,y));
        }
    }
    
    public void draw(Graphics g) {
        super.draw(g);
    }
}
