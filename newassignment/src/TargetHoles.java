import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

/** 
 * controls the status of each target hole
 * @author shivam
 *
 */
public class TargetHoles {
	private BoundingBox bounds;
	public static final float HEIGHT = 48;
	public static final float WIDTH = 96;
	private boolean isIntersected = false;
	
	public TargetHoles(float x, float y) throws SlickException{
		bounds = new BoundingBox (x, y, WIDTH, HEIGHT);
	}
	
	public BoundingBox getBounds(float[] coordinates) {
		return bounds;
	}
	// update the hole status if intersected
	public void updateIntersected() {
		isIntersected = !isIntersected;
	}
	public BoundingBox getBounds() {
		return bounds;
	}
	public boolean status() {
		return isIntersected;
	}
} 
