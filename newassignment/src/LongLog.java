import org.newdawn.slick.SlickException;

/**
 * class controls the long logs
 * @author shivam
 *
 */
public class LongLog extends Rideable {
	
	public static final float VELOCITY = 0.07f;
	
	public LongLog (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
		
	}
}