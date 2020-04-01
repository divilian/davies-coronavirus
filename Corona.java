
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Corona extends BouncingGameObject {
	
	public Corona(int x, int y) {
	    super(x, y, 75, 75, "corona.png");
	    infected=true;
        dx = GameEngine.rng.nextInt(10) - 5;
        dy = GameEngine.rng.nextInt(10) - 5;
        z = 19;   // This is in the near foreground.
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
   
    public void infect(){}

    public void draw(Graphics g) {
        super.draw(g);
    }
}
