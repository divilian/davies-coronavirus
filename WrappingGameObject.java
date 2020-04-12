
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

/**
 * A WrappingGameObject is a wrapper around an GameObject that will cause it to
 * wrap around the edges of the screen to the other side, like a torus.
 * Decorator pattern.
 */
class WrappingGameObject extends GameObject {

    private GameObject underlyingGameObject;
	
    public WrappingGameObject(GameObject underlying) {
        super(underlying.x, underlying.y, underlying.width,
            underlying.height, "none");
        this.underlyingGameObject = underlying;
    }

    /**
     * Wrap this object back from the edges of the screen, Pac-man style. This
     * method overrides the {@link GameObject#move} method.
     */
    public void move() {

        underlyingGameObject.x += underlyingGameObject.dx;
        if(underlyingGameObject.x < GameEngine.LEFT_SIDE) {
            underlyingGameObject.x = GameEngine.RIGHT_SIDE - 80;
        } else if(underlyingGameObject.x > GameEngine.RIGHT_SIDE - 80) {
            underlyingGameObject.x = GameEngine.LEFT_SIDE;
        }
        underlyingGameObject.y += underlyingGameObject.dy;
        if(underlyingGameObject.y < GameEngine.TOP_SIDE) {
            underlyingGameObject.y = GameEngine.BOTTOM_SIDE - 80;
        } else if(underlyingGameObject.y > GameEngine.BOTTOM_SIDE - 80) {
            underlyingGameObject.y = GameEngine.TOP_SIDE;
        }
        underlyingGameObject.updateHitbox();
    }

    /**
     * Returns the age of the underlying GameObject.
     */
    public int getAge() {
        return underlyingGameObject.getAge();
    }

    public String getName() {
        return underlyingGameObject.getName();
    }

    /**
     * Draw the underlying object at its coordinate on the screen.
     * @param g The Java Swing Graphics object that will actually do the
     * painting.
     */
    public void draw(Graphics g) {
        underlyingGameObject.draw(g);
    }

}
