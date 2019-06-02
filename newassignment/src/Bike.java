import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * controls the working of bike
 * @author shivam
 *
 */
public class Bike extends MovableObjects {
	
	// constant declarations
	public static final float VELOCITY = 0.2f;
	public static final int LEFT_EXTREME = 24;
	public static final int RIGHT_EXTREME = 1000;
	
	public Bike (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
	}
	
	public void update(Input input, int delta) {
		super.update(input, delta);
		// to make the bike reverse the direction
		if (super.getXcord() <= LEFT_EXTREME) {
			super.setVelocity((-1) * VELOCITY);
		}
		if (super.getXcord() >= RIGHT_EXTREME) {
			super.setVelocity((1) * VELOCITY);
		}
	}
}
