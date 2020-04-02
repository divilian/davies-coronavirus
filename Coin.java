import java.awt.Graphics;

import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

public class Coin extends GameObject {
	
	public Coin(int x, int y) {
       super(x,y,72,74,"Coin.java");
        z = 5;  // Draw mostly in the background.
        int picNum=GameEngine.rng.nextInt(3);
        String picName;
        if(picNum==0)
            picName="ToiletPaper.png";
        else
            if(picNum==1)
                picName="Groceries.png";
            else
                picName="HandSanitizer.png";
        try{
            image = ImageIO.read(new File(picName));
        }
        catch(Exception e) {e.printStackTrace(); System.exit(1);}
        
}	
    /**
     * Returns the age of this Coin.
     * @return The age in Middle Earth years (which is 1.4 of an Earth year.)
     */
    public int getAge() {
        return 1;
    }

    public String getName()
    {
    	return "Coin";
    }
    /**
     * Coins are stationary.
     */
    public void move() {
    }

    public void draw(Graphics g) {
        super.draw(g);
    }
}
