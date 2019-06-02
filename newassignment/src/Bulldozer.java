import org.newdawn.slick.SlickException;

/**
 * controls the bulldozer by calling the parent class
 * @author shivam
 *
 */
public class Bulldozer extends MovableObjects {
	
	// constant declarations
	public static final float VELOCITY = 0.05f;
	
	public Bulldozer (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
		super.getBounds().setX(x);
		super.getBounds().setX(y);
	}
	
}
