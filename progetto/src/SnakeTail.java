import java.awt.Color;
import java.awt.image.BufferedImage;

public class SnakeTail extends SnakeObject{

	private BufferedImage picture;
	
	
	/**
	 * A Snake Tail
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */

	/**
	 * Constructs a Snake Tail using the Snake Object constructor Put it into the
	 * GameScene.
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
	public SnakeTail(Panel pannello, BufferedImage image, GameScene giocoScena,int x, int y, Color color) {
		super(pannello, giocoScena,x, y);
		setColor(color);
		picture= image;
	}
	
	/**
	 * Print the Snake Tail into the scene and on the panel
	 */
	
	@Override
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
		// TODO Auto-generated method stub		
	}
}
