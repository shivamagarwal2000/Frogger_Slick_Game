import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
public class ExtraLife extends MovableObjects {
	
	public static final String EXTRALIFE = "assets/extralife.png";
	public static final String LOG = "assets/log.png";
	public static final String LONGLOG = "assets/longlog.png";
	public static final int RENDERTIME = 14000;
	public static final int MOVEMENTTIME = 2000;
	public static final int TILESIZE = 48;
	public static final int INITIALTIME = 25;
	public static final int MILLISECONDS = 1000;
			
	
	// store the random time between 25 and 35
	private int randTime;
	
	// store the direction of movement of extra life
	private static int dirFlag; 
	
	// time counter
	private float time; 
	
	// temporary sprite to hold the position of extralife
	private static Sprite temp; 
	
	// generate a randomiser
	Random rand = new Random(); 
	
	// list holding only valid sprites
	private static ArrayList<Sprite> tempList;
	
	// generate a flag to indicate a new extra life  
	private static int flag = 0; 
	
	// to store whether to render or not
	private static boolean renderStatus = false; 
	
	// keeps track of the rendering time of extralife 
	private float tempTime;
	
	private int delta;
	
	/**
	 * initialise the list and generate a list of valid objects
	 * @param list of all the sprites
	 * @throws SlickException
	 */
	public ExtraLife(ArrayList<Sprite> list) throws SlickException {
		super(EXTRALIFE,0,0,true);
		tempList = new ArrayList<Sprite>();
		
		// create a list of all the valid objects to ride for extralife
		for (Sprite var : list) {
			if (var.type.equals(LOG) || var.type.equals(LONGLOG)) {
				tempList.add(var);
			}
		}
		// generate an initial random time
		setRandomTime();
		
		// making default movement to the right and setting the time and direction as well
		dirFlag = 1;
		tempTime = 0f;
		time = 0f;
		super.setDirection(0);
	}
	
	// updates the movement of extra life
	public void update(Input input, int delta) {
		
		// measure the elapsed time
		time = time + delta; 
		if ((flag == 0) && (time >= randTime)) {
			
			// generate a random log or longlog
			temp = tempList.get(rand.nextInt(tempList.size()));
			this.delta = delta;
			
			// update all the coordinates and set the velocity of the extralife object 
			updateSpriteVariables();
			
			//update the render conditions and flag variables
			updateLocalVariables(true, 1);
		}
		
		// set the extralife if it goes out of screen
		if (super.getBounds().getLeft() >= SCREEN_WIDTH && (super.getDirection() == 1)) {
			super.setXcord(temp.getXcord());
		
		} else if (super.getBounds().getRight() <= 0 && (super.getDirection() == (-1))) {
			super.setXcord(temp.getXcord());
		}
		
		// make the frog move along the log or long log
		super.setXcord(super.getXcord() + super.getVelocity() * delta * super.getDirection());
		
	}
		
	public void render() {
		if (renderStatus) {
			
			// temporary timer to track sub-movements
			tempTime = tempTime + delta;
			
			//if its more than 2s then move in a particular direction
			if (tempTime >= MOVEMENTTIME) {
				
				// deals right or left movement
				if (dirFlag == 1) {
					
					super.setXcord(super.getXcord() + TILESIZE);
				}
				
				else if (dirFlag == (-1)) {
					super.setXcord(super.getXcord() - TILESIZE);
				}
				// if moves to water then stop and change direction
				if (!super.getBounds().intersects(temp.getBounds())) {
					
					dirFlag *= -1;
					if (dirFlag == (-1))
						super.setXcord(super.getXcord() - (TILESIZE * 2));
					else
						super.setXcord(super.getXcord() + (TILESIZE * 2));
				}
				tempTime = 0f;	
			}
			// if it crosses a 14s mark then reset the random time and update the rendering conditions and flag
			if (time >= RENDERTIME) {
				setRandomTime();
				updateLocalVariables(false, 0);
			}

			if (Player.getfrg().intersects(getBounds())) {
				updateLocalVariables(false, 0);
				setRandomTime();
				Lives.addLife();
			}
			super.render();
		}
	}
	
	// update the coordinates and the speed and direction
	public void updateSpriteVariables() {
		super.setXcord(temp.getXcord());
		super.setYcord(temp.getYcord());
		super.setVelocity(((MovableObjects)temp).getVelocity());
		super.setDirection(((MovableObjects)temp).getDirection());
	}
		
	public void updateLocalVariables(boolean renderTemp, int flagTemp) {
		time = 0;
		tempTime = 0;
		renderStatus = renderTemp;
		flag = flagTemp;
		dirFlag = 1;
	}
	
	// generate a random time
	public void setRandomTime() {
		// generate a random time between 25 and 35s
		randTime = MILLISECONDS * (INITIALTIME + (int)(11.0 * Math.random()));
	}
}
