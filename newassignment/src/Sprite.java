import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

import org.newdawn.slick.Input;

/**
 * holds the functionality of sprites
 * @author shivam
 *
 */

public class Sprite {
	private float xcord;
	private float ycord;
    private Image sprite;
    public String type;
    private BoundingBox bounds;
	
    public Sprite(String imageSrc, float x, float y) throws SlickException {
		
    	// initialise all the sprites and bounding box as well
    	type = imageSrc;
		sprite = new Image(imageSrc);
		this.xcord = x;
		this.ycord = y;
		bounds = new BoundingBox(sprite, (int)x, (int)y);
	}
	
	public void render() {
		
		sprite.drawCentered(xcord,ycord);
	}
	// getters and setters
	public float getXcord() {
		
		return xcord;
	}

	public void setXcord(float xcord) {
		
		this.xcord = xcord;
		bounds.setX((int)xcord);
	}

	public void update (Input input, int delta) {
	}
	
	public float getYcord() {
		
		return ycord;
	}

	public void setYcord(float ycord) {
		
		this.ycord = ycord;
		bounds.setY((int)ycord);
	}
	
	public BoundingBox getBounds() { // return the bounding box
    	return bounds;
    }
}
