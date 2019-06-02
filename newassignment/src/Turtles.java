import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * implements the functionality of turtles of resurfacing and being invisible
 * @author shivam
 *
 */
public class Turtles extends Rideable {
	
	public static final float VELOCITY = 0.085f;
	public static final int VISIBLE_TIME = 7000;
	public static final int INVISIBLE_TIME = 9000;
	
	private float time;
	private float previousX; // to hold previous move
	private float previousY;
	
	public Turtles (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
		time = 0;
	}
	public void update(Input input, int delta) {
		time = time + (delta); // to track the actual time
		if(time <= VISIBLE_TIME) {
			super.update(input, delta);
			previousX = super.getXcord();// keep track of previous location before disappearance
			previousY = super.getYcord();
		}
		if(time >= INVISIBLE_TIME) {
			time = 0;
			super.setXcord(previousX); // re-position the turtles to the last positions
			super.setYcord(previousY);
		}
	}
	public void render() {
		if (time <= VISIBLE_TIME)
			super.render(); // only render the turtles in visible time
	}
	public float getTime() {
		return time;
	}
}