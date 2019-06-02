import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * class handling the movement and all the related parameters of all the moving objects
 * updates the velocity and direction accordingly
 * @author shivam
 *
 */
public class MovableObjects extends Sprite{
	
	public static final float SCREEN_WIDTH = 1024;
	public static final float TILE_SIZE = 48;
	private float velocity;
	private int direction;

	public MovableObjects (String imageSrc, float xcord, float ycord, boolean moveRight) throws SlickException {
		super(imageSrc, xcord, ycord);
		
		if (moveRight)
			direction = 1; // the normal velocity
		else
			direction = (-1); // velocity in opposite direction
	}

	public void update(Input input, int delta) {
		
		// case to handle if object goes out of screen
		if (getBounds().getLeft() >= SCREEN_WIDTH && (direction == 1))
			super.setXcord((-1 * getBounds().getWidth()/2));
		
		else if (getBounds().getRight() <= 0 && (direction == (-1)))
			super.setXcord((SCREEN_WIDTH + getBounds().getWidth()/2));
		
		super.setXcord(super.getXcord() + velocity * delta * direction); // set the x coordinate
	
	}
	
	public void render() 
	{
		super.render();		
	}
	
	public float getVelocity() {
		
		return velocity;
	}

	public void setVelocity(float velocity) {
		
		this.velocity = velocity;
	}
	
	public int getDirection() {
		
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}       