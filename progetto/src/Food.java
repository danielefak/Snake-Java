import java.awt.Color;
import java.awt.image.BufferedImage;

public class Food extends GameObject {

	BufferedImage picture;
	
	/**
	 * A Food
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */

	/**
	 * Constructs a Wall using the GameObject random coordinate constructor.
	 * Put it into the GameScene and print it on Panel.
	 * 
	 * @param panel
	 *            A generic Panel
	 * @param image
	 *            A Buffered Image used to print.
	 * @param giocoScena
	 *            A GameScene
	 */

	public Food(Panel pannello, BufferedImage image, GameScene giocoScena) {
		super(pannello, giocoScena);
		setIsFood(true);
		setColor(Color.yellow);
		picture = image;
		print();
	}

	/**
	 * Print the Food into the scene and on the panel
	 */
	@Override
	public void print() {
		panel.drawOval(getX(), getY(), getColor());
		panel.drawImage(getX(), getY(), picture);
		scene[getX()][getY()] = this;
	}

	/**
	 * RePrint the Food into the scene and on the panel
	 */
	@Override
	public void rePrint() {
		setRandomCoordinate();
		print();
	}

}
