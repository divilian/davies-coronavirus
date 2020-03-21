
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Coin extends GameObject {
	
	public Coin(int x, int y) {
		super(x, y, "coin.png");
	}
	
    /**
     * Returns the age of this Coin.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 1;
    }

    /**
     * Coins are stationary.
     */
    public void move() {
    }

    public void draw(Graphics g) {
        super.draw(g);
    }
}
