
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

    private Image buffer = null;
    private Timer timer = null;

    public static Random rng = new Random();

    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    public static void main(String args[]) {
        new GameEngine();
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
        objects.add(w);
        addKeyListener(w);

        for (int i=0; i<5; i++) {
            objects.add(new Coin((i+1)*100,(i+1)*100));
        }
        objects.add(new Dad(500,500));
    }


    private void tick() {

        for(int i=0; i<objects.size(); i++) {
            objects.get(i).move();
        }
    }


    /**
     * Paints the screen. This method will display all objects at their
     *  appropriate positions.
     */
    public void paint(Graphics g) {

        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        for(int i=0; i<objects.size(); i++) {
            objects.get(i).draw(bufferGraphics);
        }

        g.drawImage(buffer, 0, 0, this);
    }


    void addGameObject(GameObject go) {
        objects.add(go);
    }

}
