import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * This class controls the working of all the world objects and controls the player as well
 * It provides all the features available in the game by linking all the classes cohesively
 * @author Shivam Agarwal (951424)
 */

public class World {
	
	public static final float INITIAL_PLAYER_Y = 720;
	public static final float INITIAL_PLAYER_X = 512;
	public static final String FROG = "assets/frog.png";
	public static final String LVL_1 = "levels/0.lvl";
	public static final String LVL_2 = "levels/1.lvl";
	public static final int TOTAL_HOLES = 5;
	public static final int HOLE_Y = 24;
	public static final int MIDDLE_HOLES = 96;
	public static final int EMPTY_HOLES = 0;
	
	private ArrayList<Sprite> list;
	private Player frog;
	private Lives life;
	private ArrayList<TargetHoles> holes;
	private static int lvlCount = 0;
	private LevelLoader o;

	public World() throws NumberFormatException, SlickException {
		list = new ArrayList<Sprite>();
		o = new LevelLoader();
		list = (o.loadSprites (LVL_1));
		
		life = new Lives();
		holes = new ArrayList<TargetHoles>();

		// initialize the holes array
		for (int i = 1; i < TOTAL_HOLES * 2; i = i+2) {
			holes.add(new TargetHoles((MIDDLE_HOLES * i), HOLE_Y));
		}
		frog = new Player(FROG, INITIAL_PLAYER_X, INITIAL_PLAYER_Y, list, holes);
	}
	
	public void update(Input input, int delta) throws NumberFormatException, SlickException {
		// Update all of the sprites in the game
		frog.update(input, delta); 
		int filledHoles = 0;
		
		for (TargetHoles var : holes) {
			if (var.status())
				filledHoles++;
		}
		// update the level if all the holes are filled and exit the game if on level 2
		if (filledHoles == TOTAL_HOLES) {
			if (lvlCount == 1) {
				System.exit(0);
			}
			lvlCount ++;
			//create a new list
			list = new ArrayList <Sprite>();
			o = new LevelLoader();
			list = o.loadSprites (LVL_2);
			// Reinitialize the frog to initial position and reset update holes
			frog = new Player(FROG, INITIAL_PLAYER_X, INITIAL_PLAYER_Y, list, holes);
			filledHoles = 0;
			for (TargetHoles var : holes) {
				var.updateIntersected();
			}
		}
		for (Sprite var : list) {
			var.update(input, delta);
		}
		frog.checkIntersection();
	}
	// renders(draws) all the objects present in the game
	public void render(Graphics g) throws SlickException {
		for (Sprite var : list) {
			var.render();
		}
		frog.render();
		life.render();
		// render the frog in the holes if it had reached on them
		for (TargetHoles var : holes) {
			if (var.status()) {
				Image frog = new Image (FROG);
				frog.draw (var.getBounds().getX(), HOLE_Y);
			}
		}
	}

}
