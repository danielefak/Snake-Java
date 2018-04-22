import java.awt.Color;
import java.awt.image.BufferedImage;

public class SnakeBody extends SnakeObject{

private BufferedImage picture;

/**
 * A Snake Head
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */

/**
 * Constructs a Snake Body using the Snake Object constructor.
 * Put it into the GameScene.
 * 
 * @param panel
 *            A generic Panel
 * @param image
 *            A Buffered Image used to print.
 * @param giocoScena
 *            A GameScene
 * @param x
 *            Coordinate X of the snake tail in the game
 * 
 * @param y
 *            Coordinate y of the snake tail in the game
 */
	public SnakeBody(Panel pannello, BufferedImage image, GameScene giocoScena, int x, int y, Color color) {
		super(pannello, giocoScena, x, y);
	    setColor(color);
	    picture=image;
	}
	

	/**
	 * Constructs a Snake Body using the snake Object random coordinate constructor.
	 * Put it into the GameScene.
	 * 
	 * @param panel
	 *            A generic Panel
	 * @param image
	 *            A Buffered Image used to print.
	 * @param giocoScena
	 *            A GameScene
*/
	
	public SnakeBody(Panel pannello, BufferedImage image, GameScene giocoScena, Color color) {
		super(pannello, giocoScena);
	    setColor(color);
	    picture=image;
	}
	
	/**
	 * Print the Snake Body into the scene and on the panel
	 */
	public void print() {
		panel.drawOval(getX(), getY(), getColor());
		panel.drawImage(getX(), getY(), picture);
		scene[getX()][getY()]=this;	
	}

	/**
	 * Does nothing
	 */
	@Override
	public void rePrint() {
		
	}

	
	/**
	 * Set the Coordinate using  the Snake Object method setRandomNeighbour.
	 * Put it into the GameScene.
	 * 
	 * @param body
	 *          the previous body in the snake
	 * @param growDirection
	 *         the favourite direction  for the method setRandomNeighbour
	 * @param color
	 *           A color.
*/
	
	public void setRandomNeighbour(SnakeObject body, int growDirection, Color color) {
		setRandomNeighbour(body,growDirection);
		setColor(color);	
	}

		

}
