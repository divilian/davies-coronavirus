
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

/**
 * A BouncingGameObject is a wrapper around an GameObject that will cause it to
 * bounce off walls and other objects. Decorator pattern.
 */
class BouncingGameObject extends GameObject {

    private GameObject underlyingGameObject;
	
    public BouncingGameObject(GameObject underlying) {
        super(underlying.x, underlying.y, underlying.width,
            underlying.height, "none");
        this.underlyingGameObject = underlying;
    }

    /**
     * Move this object in an ordinary bouncing pattern. This method overrides
     * the {@link GameObject#move} method.
     */
    public void move() {

        underlyingGameObject.x += underlyingGameObject.dx;
        if(underlyingGameObject.x < GameEngine.LEFT_SIDE) {
            underlyingGameObject.x = GameEngine.LEFT_SIDE;
            underlyingGameObject.dx = -underlyingGameObject.dx;
            hitSide();
        } else if(underlyingGameObject.x > GameEngine.RIGHT_SIDE - 80) {
            underlyingGameObject.x = GameEngine.RIGHT_SIDE - 80;
            underlyingGameObject.dx = -underlyingGameObject.dx;
            hitSide();
        }
        underlyingGameObject.y += underlyingGameObject.dy;
        if(underlyingGameObject.y < GameEngine.TOP_SIDE) {
            underlyingGameObject.y = GameEngine.TOP_SIDE;
            underlyingGameObject.dy = -underlyingGameObject.dy;
            hitSide();
        } else if(underlyingGameObject.y > GameEngine.BOTTOM_SIDE - 80) {
            underlyingGameObject.y = GameEngine.BOTTOM_SIDE - 80;
            underlyingGameObject.dy = -underlyingGameObject.dy;
            hitSide();
        }
        underlyingGameObject.updateHitbox();
    }

    /**
     * Subclasses can override this method if they want to be notified when
     * this bouncing object hits the side of the screen.
     */
    protected void hitSide() {
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

    public boolean collidingWith(GameObject o) {
        return underlyingGameObject.collidingWith(o);
    }

    public void touch(GameObject o){
        underlyingGameObject.touch(o);
    }

    public boolean getInfected(){
        return underlyingGameObject.getInfected();
    }
    public int getTop() { return underlyingGameObject.getTop(); }
    public int getBottom() { return underlyingGameObject.getBottom(); }
    public int getLeft() { return underlyingGameObject.getLeft(); }
    public int getRight() { return underlyingGameObject.getRight(); }

}
