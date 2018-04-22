import java.awt.Color;

/**
 * A Snake Head
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */


public class SnakeHead extends SnakeObject {
	


	/**
	 * Constructs a Snake Head using the Snake Object constructor.
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
	public SnakeHead(Panel pannello, GameScene giocoScena,int x, int y) {
		super(pannello, giocoScena,x, y);
		setColor(Color.black);
	}

	/**
	 * Print the Snake Head into the scene and on the panel
	 */
		
	public void print() {
		panel.drawRectangle(getX(), getY(), getColor());
		scene[getX()][getY()]=this;	
	}


	/**
	 * Does nothing
	 */
	@Override
	public void rePrint( ) {
		// TODO Auto-generated method stub
		
	}



	
}
