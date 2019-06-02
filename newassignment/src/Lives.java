import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * class keep a track of lives and updates them and displays them accordingly
 * @author shivam
 *
 */
public class Lives {
	
	public static String LIFE = "assets/lives.png";
	public static int INITIAL_LIVES = 3;
	public static float INITIAL_X = 24f;
	public static float INITIAL_Y = 744f;
	public static float DIFFERENCE = 32f;
	
	private Image object;
	
	// hold the current lives
	private static int curLives;
	
	public Lives() throws SlickException {
		curLives = INITIAL_LIVES;
		object = new Image(LIFE);
	}
	
	public static void addLife() {
		curLives++;
	}
	
	public static void reduceLife() {
		curLives--;
	}
	
	public int getLives() {
		return curLives;
	}
	
	public void render() {
		float x = INITIAL_X;
		float y = INITIAL_Y;
		for (int i = 0; i < getLives(); i++) {
			object.drawCentered (x,y);
			x += DIFFERENCE;
		}
		// if life is less than or equal to zero then quit the game
		if (getLives() <= 0)
			System.exit(0);
	}
}
