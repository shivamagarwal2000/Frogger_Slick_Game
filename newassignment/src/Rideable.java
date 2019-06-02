import org.newdawn.slick.SlickException;
/**
 * controls the rideable
 * @author shivam
 *
 */
public class Rideable extends MovableObjects {
	
	public Rideable (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
	}
}
