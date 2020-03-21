
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Dad extends GameObject {
	
	public Dad(int x, int y) {
		super(x, y, "dad.png");
        dx = GameEngine.rng.nextInt(10) - 5;
        dy = GameEngine.rng.nextInt(10) - 5;
	}
	
    /**
     * Returns the age of this Dad.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 36;
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
        } else if(x > GameEngine.SCREEN_WIDTH - 80) {
            x = GameEngine.SCREEN_WIDTH - 80;
            dx = -dx;
        }
        y += dy;
        if(y < 0) {
            y = 0;
            dy = -dy;
        } else if(y > GameEngine.SCREEN_HEIGHT - 80) {
            y = GameEngine.SCREEN_HEIGHT - 80;
            dy = -dy;
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
    }
}
