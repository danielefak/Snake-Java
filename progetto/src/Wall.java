import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A wall brick
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */
public class Wall extends GameObject {
	private BufferedImage wallPic;

	/**
	 * Constructs a Wall using the GameObject constructor Put it into the
	 * GameScene and print it on Panel.
	 * 
	 * @param panel
	 *            A generic Panel
	 * @param picture
	 *            A buffered Image used for printing
	 * @param giocoScena
	 *            A GameScene
	 * @param x
	 *            Coordinate X of the wall in the game
	 * 
	 * @param y
	 *            Coordinate y of the wall in the game
	 */
	public Wall(Panel pannello, BufferedImage picture, GameScene giocoScena, int x, int y) {
		super(pannello, giocoScena, x, y);
		setColor(Color.black);
		setIsDangerous(true);
		setIsWall(true);
		wallPic = picture;
	}
	/**
	 * Print the wall into the scene and on the panel
	 */

	@Override
	public void print() {
		if (panel.isInside(getX(), getY())) {
			panel.drawRectangle(getX(), getY(), getColor());
			panel.drawImage(getX(), getY(), wallPic);
			scene[getX()][getY()] = this;
		}
	}

	/**
	 * Does nothing
	 */
	
	
	@Override
	public void rePrint() {
	}
}
