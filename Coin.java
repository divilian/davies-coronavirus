
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Coin extends GameObject {
	
	public Coin(int x, int y) {
		super(x, y, 72, 74, "coin.png");
        z = 5;  // Draw mostly in the background.
	}
	
    /**
     * Returns the age of this Coin.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 1;
    }

    public String getName()
    {
    	return "Coin";
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
