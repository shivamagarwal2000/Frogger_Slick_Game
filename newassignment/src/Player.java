import utilities.BoundingBox;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * class that handles the intersection of frog with all the different objects
 * renders the frog to its position as well
 * @author shivam
 *
 */
public class Player extends Sprite{

	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 768;
	public static final int TURTLE_LOCN = 2;
	public static final int VISIBLE_TIME = 7000;
	public static final int TILE_SIZE = 48;
	public static final int WATER_Y = 336;
	public static final int INITIAL_PLAYER_Y = 720;
	public static final int INITIAL_PLAYER_X = 512;
	public static final String[] HAZARDS = {"assets/bike.png","assets/bus.png","assets/racecar.png"};
	public static final String[] RIDEABLE = {"assets/log.png","assets/longlog.png","assets/turtles.png"};
	public static final String BULLDOZER = "assets/bulldozer.png";
	public static final String WATER = "assets/water.png";
	public static final String TREE = "assets/tree.png";
	
	
	// holds the bounding box of frog
	private static BoundingBox frg;
	
	private ArrayList<Sprite> list;
	private static Image frog;
	
	// array list of all target holes
	private ArrayList<TargetHoles> holes;
	private float velocity;
	private int direction;
	// store the previous move
	private float previousX;
	private float previousY;
	private ExtraLife eLife;
    
	/**
	 * initialise the list of all sprites and holes
	 * also initialise the local variables
	 * @param imageSrc holds the image string
	 * @param xcord 
	 * @param ycord
	 * @param list Sprite list
	 * @param holes holes List
	 * @throws SlickException
	 */
	
	public Player(String imageSrc, float xcord, float ycord, ArrayList<Sprite> list, ArrayList<TargetHoles> holes) throws SlickException {
		super(imageSrc, xcord, ycord); // call the parent constructor
		frog = new Image(imageSrc);
		velocity = 0.0f;
		direction = 1;
		this.list = new ArrayList <Sprite>();
		this.list = list;
		this.holes = holes;
		//new Lives();
		eLife = new ExtraLife(list);
	}

	public void update(Input input, int delta) {
	
		// apply the logic of movement of frog and hold the previous moves
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			previousY = getYcord();
			previousX = getXcord();
			setYcord(getYcord() - TILE_SIZE);
			velocity = 0;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			previousY = getYcord();
			previousX = getXcord();
			setYcord(getYcord() + TILE_SIZE);
			velocity = 0;
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			previousX = getXcord();
			previousY = getYcord();
			setXcord(getXcord() - TILE_SIZE);
			velocity = 0;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			previousX = getXcord();
			previousY = getYcord();
			setXcord(getXcord() + TILE_SIZE);
			velocity = 0;
		}
		if (getYcord() < 0) {
			setYcord(0);
		}
		if (getXcord() <= 0) {
			setXcord(TILE_SIZE/2);
		}
		if (getYcord() >= SCREEN_HEIGHT) {
			setYcord(SCREEN_HEIGHT-TILE_SIZE);
		}
		if (getXcord() >= SCREEN_WIDTH) {
			setXcord(SCREEN_WIDTH-TILE_SIZE);
		}
		setXcord(getXcord() + velocity * delta * direction); // set the x coordinate
		frg = new BoundingBox(frog, getXcord(), getYcord()); // new bounding box to check intersection
		eLife.update(input, delta);
        
	}

	public void render() {
		frog.drawCentered(getXcord(),getYcord());
		eLife.render();
	}
	
    public static BoundingBox getfrg() { // return the bounding box
    	return frg;
    }
    
    /**
     * function checks all the intersection with the various objects
     * performs appropriate actions accordingly
     * @param delta
     * @param input
     */
    public void checkIntersection() {
    	boolean rideableStatus = false; // keep track of whether the frog in on any rideable object or water
		boolean waterStatus = false;
		boolean treeStatus = false;
		// traverse the list full of objects
		
    	for (Sprite var : list) {
    		
    		for (int i = 0; i < 3; i++) {
    			// check the hazards and make the frog reset and reduce the life
    			if (var.type.equals(HAZARDS[i])) {
    				if(frg.intersects(var.getBounds())) {
    					Lives.reduceLife();
						resetPlayer();
    				}
    			}	
    		}
    		if (var.type.equals(BULLDOZER) && frg.intersects(var.getBounds())) {
    			
    			// make the bulldozer push the player
    			velocity = ((MovableObjects)var).getVelocity();
    			setXcord(var.getXcord() + (TILE_SIZE));
    			
    			// check if frog goes out of screen and dies
    			if(getXcord() >= SCREEN_WIDTH - (TILE_SIZE)) {
    				Lives.reduceLife();
    				resetPlayer();
   				}		
    		}
 
    		if (var.type.equals(WATER) && frg.intersects(var.getBounds())) {
    			waterStatus = true; // update the status if the turtle is in water
    		}
    		for (int i = 0; i < 3; i++) {
    			if (var.type.equals(RIDEABLE[i]) && frg.intersects(var.getBounds())) {
    				
    				// for the turtles make it only rideable for visible time
    				if ((i == TURTLE_LOCN) && (((Turtles)var).getTime() <= VISIBLE_TIME)) {
    					velocity = ((MovableObjects)var).getVelocity();
        				direction = ((MovableObjects)var).getDirection();
        				rideableStatus = true;
    				}
    				// for every case except turtle
    				else if (i != TURTLE_LOCN) {
    					velocity = ((MovableObjects)var).getVelocity();
    					direction = ((MovableObjects)var).getDirection();
    					rideableStatus = true;
    				}
    			}
    		}
    		
    		if (var.type.equals(TREE) && frg.intersects(var.getBounds())) {
    			// implement solid tiles and make the frog return to its last move
    			setXcord(previousX); 
    			setYcord(previousY);
    			treeStatus = true;
    		}
    	}
    	// make the player lose a life if its in water and not on a rideable object
    	if(waterStatus) {
    		if(!rideableStatus) {
    			Lives.reduceLife();
				resetPlayer();
    		}
    	}
    	// check whether the player enters the holes or not
    	// implement the logic to alter the level as well
    	for (TargetHoles var : holes) {
    		if (frg.intersects(var.getBounds())) {
    			if (!var.status() && !treeStatus) {
    				var.updateIntersected();
    				resetPlayer();
    			}
    			else if (!treeStatus) {
    				Lives.reduceLife();
    				resetPlayer();
    			}
    		}
    	}
    		
	}
    
    // puts the player back to the start
    public void resetPlayer() {
    	setXcord(INITIAL_PLAYER_X);
    	setYcord(INITIAL_PLAYER_Y);
    	velocity = 0;
    }
    
    public void setVelocity(float velocity) {
    	this.velocity = velocity;
    }
    
    public void setDirection(int direction) {
    	this.direction = direction;
    }
}
