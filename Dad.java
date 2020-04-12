
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class Dad extends GameObject {
	
	public Dad(int x, int y) {
		super(x, y, 75, 90, "dad.png");
        dx = GameEngine.rng.nextInt(10) - 5;
        dy = GameEngine.rng.nextInt(10) - 5;
        z = 7;  // Near background.
	}
	
    /**
     * Returns the age of this Dad.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 36;
    }
    public String getName()
    {
    	return "Dad";
    }

    public void infect()
    {
    	if(!infected)
    	{
    		infected = true;
    		dx*=0.5;
    		dy*=0.5;
    		try {
    			image = ImageIO.read(new File("dad_infected.png"));
    		}
        	catch (Exception e) { e.printStackTrace(); System.exit(1); }
    	}
    }

    public void draw(Graphics g) {
        super.draw(g);
    }
}
