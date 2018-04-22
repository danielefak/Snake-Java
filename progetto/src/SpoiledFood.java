import java.awt.Color;
public class SpoiledFood extends GameObject {
     /**
	 * A Spoiled Food
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */
	
	/**
	 * Constructs a Wall using the GameObject random coordinate constructor.
	 * Put it into the GameScene and print it on Panel. 
	 * @param panel
	 *            A generic Panel
	 * @param giocoScena
	 *            A GameScene
	 */
		public SpoiledFood(Panel pannello, GameScene giocoScena) {
		super(pannello, giocoScena);
		setIsFood(true);
		setIsSpoiled(true);
		setColor(Color.red);
		print();
	}
		
	/** 
	 * Print the Spoiled Food into the scene
	 * and on the panel 
	 */	
	@Override
	public void print() {
		panel.drawOval(getX(), getY(), getColor());
		// panel.drawImage(getX(), getY(), picture);
		scene[getX()][getY()] = this;
	}
	
	/** 
	 * RePrint the Spoiled Food into the scene 
	 * and on the panel
	 */
	@Override
	public void rePrint() {
		setRandomCoordinate();
		print();
	}}
