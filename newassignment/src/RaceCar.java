import org.newdawn.slick.SlickException;

public class RaceCar extends MovableObjects {
	
	public static final float VELOCITY = 0.5f;
	
	public RaceCar (String imageSrc, float x, float y, boolean moveRight) throws SlickException {
		super(imageSrc, x, y, moveRight);
		super.setVelocity(VELOCITY);
	}
}

