
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

abstract class BouncingGameObject extends GameObject {
	
    public BouncingGameObject(int startX, int startY, int width, int height, String imageName) {
        super(startX, startY, width, height, imageName);
    }

    /**
     * Move this object in an ordinary bouncing pattern. This method overrides
     * the {@link GameObject#move} method.
     */
    public void move() {

        x += dx;
        if(x < GameEngine.LEFT_SIDE) {
            x = GameEngine.LEFT_SIDE;
            dx = -dx;
            hitSide();
        } else if(x > GameEngine.RIGHT_SIDE - 80) {
            x = GameEngine.RIGHT_SIDE - 80;
            dx = -dx;
            hitSide();
        }
        y += dy;
        if(y < GameEngine.TOP_SIDE) {
            y = GameEngine.TOP_SIDE;
            dy = -dy;
            hitSide();
        } else if(y > GameEngine.BOTTOM_SIDE - 80) {
            y = GameEngine.BOTTOM_SIDE - 80;
            dy = -dy;
            hitSide();
        }
        updateHitbox();
    }

    /**
     * Subclasses can override this method if they want to be notified when
     * this bouncing object hits the side of the screen.
     */
    protected void hitSide() {

    }
}
