
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class TJ extends GameObject {
	
    private static int numTJs = 0;
    private static final int MAX_TJS = 50;

	public TJ(int x, int y) {
		super(x, y, "TJ.png");
        dx = GameEngine.rng.nextInt(10) - 5;
        dy = GameEngine.rng.nextInt(10) - 5;
        numTJs++;
	}
	
    /**
     * Returns the age of this TJ.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 13;
    }
    

    /**
     * Move this Dad, in an ordinary bouncing pattern. This method overrides
     * the {@link GameObject#move} method.
     */
    public void move() {

        x += dx;
        if(x < 0) {
            x = 0;
            dx = -dx;
            if (numTJs < MAX_TJS) {
                GameEngine.instance().addObject(new TJ(500,500));
            }
        } else if(x > GameEngine.SCREEN_WIDTH - 80) {
            x = GameEngine.SCREEN_WIDTH - 80;
            dx = -dx;
            if (numTJs < MAX_TJS) {
                GameEngine.instance().addObject(new TJ(500,500));
            }
        }
        y += dy;
        if(y < 0) {
            y = 0;
            dy = -dy;
            if (numTJs < MAX_TJS) {
                GameEngine.instance().addObject(new TJ(500,500));
            }
        } else if(y > GameEngine.SCREEN_HEIGHT - 80) {
            y = GameEngine.SCREEN_HEIGHT - 80;
            dy = -dy;
            if (numTJs < MAX_TJS) {
                GameEngine.instance().addObject(new TJ(500,500));
            }
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
    }
}
