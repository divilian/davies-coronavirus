
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * A Warrior represents the human player in the game.
 */
public class Warrior extends GameObject implements KeyListener {

    private String currentDirection;
    private static Warrior theOneTrueWarrior;

    /**
     * Instantiate (or return if already exists) the singleton Warrior object
     * representing the user's presence in the game.
     * @return the one Warrior object that will exist.
     */
    static public Warrior instance() {
        if (theOneTrueWarrior == null) {
            theOneTrueWarrior = new Warrior(GameEngine.SCREEN_HEIGHT/2,
                GameEngine.SCREEN_WIDTH/2);
        }
        return theOneTrueWarrior;
    }
    
    public void touch(GameObject o)
    {
    	if(o.getName().equals("TJ"))
    	{
    		o.kill();   		
    	}
    	if(o.getName().equals("Coin"))
    	{
    		GameEngine.instance().addToScore(100);
    		o.kill();
    		TJ.speedUp();
    	}
    }
    
    public String getName()
    {
    	return "Warrior";
    }
    public boolean isFgObject() {
        return true;
    }

    private Warrior(int x, int y) {
        super(x, y, 80, 80, "warrior.png");
        currentDirection = "stopped";
    }

    public int getX() { return x; }
    public int getY() { return y; }

    /**
     * Returns the age of this Warrior.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 18;
    }

    public void move() {

        if (currentDirection.equals("Right")) {
            dx = 5;
            dy = 0;
        }
        else if (currentDirection.equals("Left")) {
            dx = -5;
            dy = 0;
        }
        else if (currentDirection.equals("Up")) {
            dx = 0;
            dy = -5;
        }
        else if (currentDirection.equals("Down")) {
            dx = 0;
            dy = 5;
        }
        else {
            dx = 0;
            dy = 0;
        }
        super.move();
      
    }


    public void keyPressed(KeyEvent e) {

        String name = KeyEvent.getKeyText(e.getKeyCode());

        currentDirection = name;
    }

    public void keyReleased(KeyEvent e) {

        String name = KeyEvent.getKeyText(e.getKeyCode());

        if (name == currentDirection) {
            currentDirection = "stopped";
        }
    }

    public void keyTyped(KeyEvent e) {

    }


}
