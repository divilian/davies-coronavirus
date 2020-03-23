
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Dad extends BouncingGameObject {
	
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

    public void draw(Graphics g) {
        super.draw(g);
    }
}
