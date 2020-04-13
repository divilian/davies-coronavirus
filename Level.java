
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;
import java.lang.reflect.Constructor;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.ImageObserver;

/**
 * A level object represents a single level of the game: its initial screen
 * layout, number and type of objects, winning condition, etc.
 *
 * Clients will obtain Levels only through the static {@link getNextLevel()}
 * method, which will hydrate the next Level in sequence from a file named
 * <tt>Level</tt><i>n</i></tt>.lvl in the <tt>levels</tt> directory.
 */
public class Level {

    static class MaxLevelException extends Exception {}

    static class ObjectSpec {
        String name;
        int x, y;
        String[] extraAttributes;

        ObjectSpec(String n, double x, double y, String[] e) {
            name = n;
            double gameHeight = GameEngine.BOTTOM_SIDE - GameEngine.TOP_SIDE;
            double gameWidth = GameEngine.RIGHT_SIDE - GameEngine.LEFT_SIDE;
            this.x = (int) Math.round(gameWidth * x);
            this.y = (int) Math.round(gameHeight * y);
            extraAttributes = e;
        }
    }

    static final String LEVEL_DIR = "levels";

    private static int currentLevelNum;
    private static int maxLevelNum;

    private int levelNum;
    private Image bgImage;
    private String bgImageFilename;
    private String name;
    private ArrayList<ObjectSpec> objectSpecs = new ArrayList<ObjectSpec>();

    static {
        File levelsDir = new File(LEVEL_DIR);
        if (!levelsDir.exists()) {
            System.err.println("No " + LEVEL_DIR + " directory!");
            System.exit(1);
        }
        String[] levelFiles = levelsDir.list(new FilenameFilter() {
            public boolean accept(File d, String f) {
                return f.endsWith(".lvl");
            }
        });
        String levelFileRegex = "level(\\d+).lvl";
        Pattern levelFilePattern = Pattern.compile(levelFileRegex);
        ArrayList<Integer> levelNums = new ArrayList<Integer>();
        for (String f : levelFiles) {
            Matcher m = levelFilePattern.matcher(f);
            if (!m.find()) {
                System.err.println("Illegal level file " + f + ".");
                System.exit(2);
            }
            levelNums.add(Integer.valueOf(m.group(1)));
        }

        maxLevelNum = Collections.max(levelNums);
    }

    public static Level getNextLevel() throws Level.MaxLevelException {
        if (currentLevelNum == maxLevelNum) {
            throw new MaxLevelException();
        }
        currentLevelNum++;
        return new Level(currentLevelNum);
    }

    private Level(int num) {
        levelNum = num;
        hydrate(LEVEL_DIR + File.separator + "level" + num + ".lvl");
        try {
            bgImage = ImageIO.read(new File(bgImageFilename));
        }
        catch (Exception e) { e.printStackTrace(); System.exit(1); }
    }

    public Image getBgImage() {
        return bgImage;
    }

    private double getCoord(String s) {
        if (s.equals("r")) {
            return GameEngine.rng.nextDouble();
        } else {
            return Double.valueOf(s);
        }
    }

    private void hydrate(String filename) {
        Scanner s = null;
        try {
            s = new Scanner(new File(filename));
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Missing level file " + filename + "!");
            System.exit(3);
        }
        name = s.nextLine();
        bgImageFilename = s.nextLine();
    
        while (s.hasNextLine()) {
            String objLine = s.nextLine();
            String[] parts = objLine.split(",");
            String objName = parts[0];
            int quantity = Integer.valueOf(parts[1]);
            for (int i=0; i<quantity; i++) {
                double x = getCoord(parts[2]);
                double y = getCoord(parts[3]);
                String[] extras = new String[parts.length-4];
                for (int j=0; j<parts.length-4; j++) {
                    extras[j] = parts[4+j];
                }
                objectSpecs.add(new ObjectSpec(objName, x, y, extras));
            }
        }
    }

    /**
     * Populate the {@link GameEngine} passed with items from this Level.
     */
    public void populate(GameEngine ge) {

        try {
            for (ObjectSpec o : objectSpecs) {
                Class[] args = new Class[2];
                args[0] = Integer.TYPE;
                args[1] = Integer.TYPE;
                Constructor c = Class.forName(o.name).getConstructor(args);
                GameObject obj = (GameObject) c.newInstance(o.x, o.y);
                GameObject wrapped = obj;
                for (String extra: o.extraAttributes) {
                    Constructor wc = Class.forName(extra+"GameObject").
                        getConstructor(new Class[]{ GameObject.class });
                    wrapped = (GameObject) wc.newInstance(wrapped);
                }
                ge.addObject(wrapped);
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(4);
        }
    }

    public String toString() {
        return "Level " + levelNum + " (" + "\"" + name + "\")";
    }
}
