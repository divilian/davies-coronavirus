
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * A game engine is the basic control mechanism for the Davies game. It is a
 * singleton object only <i>one</i> of which will be instantiated. The game
 * engine maintains a collection of {@link GameObject}s that it uses to
 * represent individual creatures in the game.
 */
public class GameEngine extends JFrame {

    // These dimension-related variables are set in the static initializer.
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int LEFT_SIDE;
    public static int RIGHT_SIDE;
    public static int TOP_SIDE;
    public static int BOTTOM_SIDE;

    public static final int SCOREBOARD_HEIGHT=100;
    
    private int score;
    private boolean win = false;
    
    private static GameEngine theInstance;

    private Image buffer = null;
    private Timer timer = null;
//    private JButton close;

    public static Random rng = new Random();

    private static List<GameObject> objects =
         Collections.synchronizedList(new ArrayList<GameObject>());


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
        objects.add(x);
    }

    public void removeObject(GameObject x)
    {
        objects.remove(x);
    }

    static {
        // Find out the screen size and set dimensions accordingly.
        Dimension b = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_WIDTH = (int)b.width;
        SCREEN_HEIGHT = (int)b.height;
        LEFT_SIDE=0;
        RIGHT_SIDE=SCREEN_WIDTH;
        TOP_SIDE=SCOREBOARD_HEIGHT;
        BOTTOM_SIDE=SCREEN_HEIGHT;
    }

    private GameEngine() {

        setLayout(null);
 /*       close = new JButton("losers click here");
        getContentPane().add(close);
        Insets insets = getInsets();
        close.setBounds(insets.left + SCREEN_WIDTH - 200,
            insets.top + 50, 150, 50);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(100);
            }
        });
*/
        Level level = null;
        try {
            level = Level.getNextLevel();
        } catch (Level.MaxLevelException e) {
            System.err.println("No levels!");
            System.exit(99);
        }

        System.out.println("Playing " + level);

        setUndecorated(true);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        buffer = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        timer = new Timer();
        class ClockTickTask extends TimerTask {
            public void run() {
            	if(!win) {
            		tick();
            		repaint();
            	}
            }
        }
        timer.scheduleAtFixedRate(new ClockTickTask(), 50, 50);

        level.populate(this);

        Warrior w = Warrior.instance();
        addObject(w);
        addKeyListener(w);

        score = 0;
    }
    

    public void addToScore(int pts) {
        score += pts;
    }

    private void tick() {
    	
        for (int i=0; i<objects.size(); i++) {
            objects.get(i).move();
        }
        int size = objects.size();
        for (int i=0; i<size; i++) {
	        for(int x=0;x<size; x++){
            	if(objects.get(i).collidingWith(objects.get(x))) {
                 	objects.get(i).touch(objects.get(x));
                    if(objects.size()<size){
                       size--;
                       i--; 
                       x--;
                    }
		        }
            }
        }
    }


    /**
     * Paints the screen. This method will display all objects at their
     *  appropriate positions.
     */

    public void paint(Graphics g) {

//        close.repaint();

    	if(buffer == null) {
    		return;
    	}

   		Graphics bufferGraphics = buffer.getGraphics();
       	bufferGraphics.setColor(Color.BLACK);
       	bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Sort objects by z-order (they know how to do this) so that things
        // are drawn in the right sequence bottom-to-top.
        Collections.sort(objects);
       	for (GameObject o: objects) {
           	o.draw(bufferGraphics);
       	}
       	// FIX: All these numbers were just determined by trial-and-error.
       	// FIX: Making a JLabel and using a layout manager is probably the
       	// better way to do this.
       	bufferGraphics.setColor(Color.GRAY);
       	bufferGraphics.fillRoundRect(30,40,getWidth()/2,
       		SCOREBOARD_HEIGHT/2,50,50);
       	bufferGraphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
       	bufferGraphics.setColor(Color.YELLOW);
       	bufferGraphics.drawString("Score: " + score,50,70);

       	g.drawImage(buffer, 0, 0, this);
    }

}
