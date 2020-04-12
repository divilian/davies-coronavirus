
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class TJ extends GameObject {
	
    private static int numTJs = 0;
    private static final int MAX_TJS = 25;
    private static int speedMultiplier = 1;
	public TJ(int x, int y) {
		super(x, y, 75, 90, "TJ.png");
        numTJs++;
        dx = GameEngine.rng.nextInt(51) - 25;
        dy = GameEngine.rng.nextInt(51) - 25;
        dx*=speedMultiplier;
        dy*=speedMultiplier;
        z = 8;  // Near background.
	}
	
    /**
     * Returns the age of this TJ.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 13;
    }
    public String getName()
    {
    	return "TJ";
    }

    public void infect()
    {
    	if(!infected)
    	{
    		infected = true;
    		dx*=0.5;
    		dy*=0.5;
    		try {
    			image = ImageIO.read(new File("TJ_Infected.png"));
    		}
        	catch (Exception e) { e.printStackTrace(); System.exit(1); }
    	}
    }
    
    public void kill()
    {
    	super.kill();
    	numTJs--;
    }
    /**
     * When this TJ hits a wall, clone him (unless we're already at the maximum
     * amount of TJ-ness the universe can handle).
     */
    protected void hitSide() {
        if (numTJs < MAX_TJS) {
            GameEngine.instance().addObject(new TJ(x,y));
        }
        updateHitbox();
    }
    
    public void draw(Graphics g) {
    	super.draw(g);
    }
}
