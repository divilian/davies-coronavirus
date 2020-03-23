
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Random;


/**
 * A game engine is the basic control mechanism for the Davies game. It is a
 * singleton object only <i>one</i> of which will be instantiated. The game
 * engine maintains a collection of {@link GameObject}s that it uses to
 * represent individual creatures in the game.
 */
public class GameEngine extends JFrame {

    public static int SCREEN_WIDTH=900;
    public static int SCREEN_HEIGHT=600;

    private static GameEngine theInstance;

    private Image buffer = null;
    private Timer timer = null;

    public static Random rng = new Random();

    private static ArrayList<GameObject> fgObjects =
         new ArrayList<GameObject>();
    private static ArrayList<GameObject> bgObjects =
        new ArrayList<GameObject>();


    public static GameEngine instance() {
        if (theInstance == null) {
            theInstance = new GameEngine();
        }
        return theInstance;
    }

    public static void main(String args[]) {
        GameEngine.instance();
    }

    public void addObject(GameObject x)
    {
        if (x.isFgObject()) {
            fgObjects.add(x);
        } else {
            bgObjects.add(x);
        }
    }

    private GameEngine() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setVisible(true);
        buffer = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timer = new Timer();
        class ClockTickTask extends TimerTask {
            public void run() {
                tick();
                repaint();
            }
        }
        timer.scheduleAtFixedRate(new ClockTickTask(), 50, 50);

        Warrior w = Warrior.instance();
        addObject(w);
        addKeyListener(w);

        for (int i=0; i<5; i++) {
            addObject(new Coin((i+1)*100,(i+1)*100));
        }
        addObject(new Dad(500,500));
        addObject(new TJ(500,500));
    }
    

    private void tick() {
    	
        for (int i=0; i<fgObjects.size(); i++) {
            fgObjects.get(i).move();
        }
        for (int i=0; i<bgObjects.size(); i++) {
            bgObjects.get(i).move();
        }
    }


    /**
     * Paints the screen. This method will display all objects at their
     *  appropriate positions.
     */
    public void paint(Graphics g) {
    	if(buffer == null) {
    		return;
    	}
        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject o: bgObjects) {
            o.draw(bufferGraphics);
        }
        for (GameObject o: fgObjects) {
            o.draw(bufferGraphics);
        }

        g.drawImage(buffer, 0, 0, this);
    }

}
