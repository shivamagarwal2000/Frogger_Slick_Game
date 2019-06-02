import org.newdawn.slick.SlickException;

/**
 * class controls the log objects
 * @author shivam
 *
 */
public class Log extends Rideable {
	
	public static final float VELOCITY = 0.1f;
	
	public Log (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
		
	}
}