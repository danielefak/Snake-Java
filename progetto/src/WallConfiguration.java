import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * ArrayList of walls
 * 
 * @author Daniele Fakhoury 
 * @author NMCGJ, AY 2015-2016
 */

public class WallConfiguration {
protected ArrayList<Wall> wallList= new ArrayList<Wall>(10);
	

/**
 * Constructs an an ArrayList of Walls. Put it into the GameScene and print it on Panel.
 * 
 * @param panel
 *           A generic Panel
 * @param image
 *           A buffered Image used for printing
 * @param giocoScena
 *          A GameScene 
 * @param ArrayList<Coordinate> of the walls to create          
 *          
 */

WallConfiguration(Panel pannello, BufferedImage image, GameScene giocoScena, ArrayList<Coordinate> coord) {
    	 for(int i=0; i<coord.size();i++){
    		 Wall wall = new Wall(pannello, image, giocoScena,coord.get(i).getX(), coord.get(i).getY());
    		 wallList.add(wall);
    		 print();
    	 }
		}




    /**
	 * Print the wallList in the scene and on the panel   
	 */
    
	public void print( ) {
		for (int i = 0; i < wallList.size(); i++) {
			wallList.get(i).print();
		}
	}
}
