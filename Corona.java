
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Corona extends BouncingGameObject {
	
	public Corona(int x, int y) {
		super(x, y, 75, 75, "corona.png");
        dx = GameEngine.rng.nextInt(10) - 5;
        dy = GameEngine.rng.nextInt(10) - 5;
	}
	
    /**
     * The novel Coronavirus started in 2020.
     */
    public int getAge() {
        return 1;
    }
    public String getName()
    {
    	return "Corona";
    }
    public void touch(GameObject o)
    {
    	
    }
   

    public void draw(Graphics g) {
        super.draw(g);
    }
}
