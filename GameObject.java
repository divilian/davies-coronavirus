import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.image.ImageObserver;

/**
 * The superclass of all visibly displayable game elements. Inidividual
 * subclasses will override methods for character-specific behavior.
 */
abstract public class GameObject implements ImageObserver {

    protected int x, y;
    protected int dx, dy;
    protected int height, width;
    protected int top, bottom, left, right;
    protected boolean infected;
    protected Image image;
    
    public GameObject(int startX, int startY, int w, int h, String imageName) {

        x = startX;
        y = startY;
        width = w;
        height = h;
        dx = 0;
        dy = 0;
        infected = false;
        updateHitbox();
        try {
            image = ImageIO.read(new File(imageName));
        }
        catch (Exception e) { e.printStackTrace(); System.exit(1); }
    }
    
    public boolean isFgObject() {
        return false;
    }
    
    public void infect() {
    	infected = true;
    }
    
    public void kill() {
    	GameEngine.instance().removeObject(this);
    }
    
    public void updateHitbox() {
        left = x-width/2;
        right = x+width/2;
        top = y-height/2;
        bottom = y+height/2;
    }
    
    public boolean collidingWith(GameObject o) {
    	return (left<=o.right)&&(o.left<right)&&(top<o.bottom)&&(bottom>o.top);
    }

    public abstract void touch(GameObject o);
    
    public abstract String getName();
    /**
     * Move this object one clock tick's worth of movement. Subclasses can
     * override this method to implement creature-type-specific move
     * algorithms.
     */
    public void move() {
        x += dx;
        if (x < GameEngine.LEFT_SIDE) {
            x = GameEngine.LEFT_SIDE;
        } else if(x > GameEngine.RIGHT_SIDE - 80) {
            x = GameEngine.RIGHT_SIDE - 80;
        }
        y += dy;
        if (y < GameEngine.TOP_SIDE) {
            y = GameEngine.TOP_SIDE;
        } else if (y > GameEngine.BOTTOM_SIDE - 80) {
            y = GameEngine.BOTTOM_SIDE - 80;
        }
        updateHitbox();
    }
    

    /**
     * Draw the creature at its coordinate on the screen.
     * @param g The Java Swing Graphics object that will actually do the
     * painting.
     */
    public void draw(Graphics g) {
    	
        g.drawImage(image, x, y, (ImageObserver)this);
    }

    /**
     * Returns the age of this GameObject.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    abstract public int getAge();


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) { return false; }

}
