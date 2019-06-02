import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 * reads the csv and sends the data in the required classes
 * @author shivam
 *
 */
public class LevelLoader {
	/**
	 * loads the stage by reading the csv
	 * @param filename
	 * @return the array list filled with stage components
	 * @throws NumberFormatException
	 * @throws SlickException
	 */
	public ArrayList<Sprite> loadSprites(String filename) throws NumberFormatException, SlickException {
		ArrayList<Sprite> list = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
				String line;
				
				while ((line = reader.readLine()) != null) {
					
					// Splitting the lines
					String[] parts = line.split(",");
					
					// Checking the csv with the required data and adding to its respective class
					switch (parts[0]) {
						case "water":
							list.add(new Water("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2])));
							break;
						case "grass":
							list.add(new Tile("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2])));
							break;
						case "tree":
							list.add(new Tree("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2])));
							break;
						case "turtle":
							list.add(new Turtles("assets/" + parts[0] + "s.png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "bike":
							list.add(new Bike("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "bulldozer":
							list.add(new Bulldozer("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "bus":
							list.add(new Bus("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "log":
							list.add(new Log("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "longLog":
							list.add(new LongLog("assets/" + "longlog" + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
						case "racecar":
							list.add(new RaceCar("assets/" + parts[0] + ".png", Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Boolean.parseBoolean(parts[3])));
							break;
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
