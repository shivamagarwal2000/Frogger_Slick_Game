import org.newdawn.slick.SlickException;

/**
 * controls the bus same as the car and bike
 * @author shivam
 *
 */
public class Bus extends MovableObjects {
	
	// constant declarations
	public static final float VELOCITY = 0.15f;
	
	public Bus (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		setVelocity(VELOCITY);
	}
}
