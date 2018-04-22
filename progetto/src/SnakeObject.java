abstract public class SnakeObject extends GameObject {
	/**
	 * A Snake Tail
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */
	
	
	protected int cameFrom= -1; // it s a number between 0 and 3 and indicates 
	// where the direction of the PRECEDENTE snake Object in the snake.
	
	/**
	 * Constructs a Snake Object using the GameObject constructor.
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
	
	public SnakeObject(Panel pannello, GameScene giocoScena, int x, int y) {
		super(pannello, giocoScena,x, y);
		setIsDangerous(true);
		setIsSnake(true);
	}

	
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
	
	public SnakeObject(Panel pannello, GameScene giocoScena) {
		super( pannello, giocoScena);
		setIsDangerous(true);
		setIsSnake(true);
	}

	
	//Semi random per favorire una certa configurazione lineare

	
	/**
	 * Set the object1 coordinate semi random (prefer the direction)
	 * direction if is possible. set it random else
	 * 
	 * @param object1
	 *            A generic Snake Object
	 * @param direction
	 *            A Buffered Image used to print.
	 */
	
	public void setRandomNeighbour(SnakeObject object1, int direction) {

		int x = object1.getX();
		int y = object1.getY();
		int s = 0;
		int t = 0;
		int newCameFrom;
		
			do {
				int a = (int) (Math.random() * 4);
				if (a == 0)
					s = x - 1;
				if (a == 2)
					s = x + 1;
				if (a == 1)
					t = y - 1;
				if (a == 3)
					t = y + 1;
				if (scene[s][t] != null) {
					s = x;
					t = y;
				}
				newCameFrom=a;
			} while (scene[s][t] != null);
			
 
			if (direction==0 && scene[x-1][y] == null) {
				s = x-1;
				t = y;
				newCameFrom=direction;
			} 
			
			
			if (direction==1 && scene[x][y-1] == null) {
				s = x;
				t = y-1;
				newCameFrom=direction;
			} 
			if (direction==2 && scene[x+1][y] == null) {
				s = x+1;
				t = y;
				newCameFrom=direction;

			} 
			if (direction==3 && scene[x][y+1] == null) {
				s = x;
				t = y+1;
				newCameFrom=direction;
			} 
		   
			
			if (object1.cameFrom ==0 && scene[x-1][y] == null) {
				s = x-1;
				t = y;
				newCameFrom=object1.cameFrom;
			} 
			
			
			if (object1.cameFrom ==1 && scene[x][y-1] == null) {
				s = x;
				t = y-1;
				newCameFrom=object1.cameFrom;
			} 
			
			if (object1.cameFrom==2 && scene[x+1][y] == null) {
				s = x+1;
				t = y;
				newCameFrom=object1.cameFrom;

			} 
			if (object1.cameFrom==3 && scene[x][y+1] == null) {
				s = x;
				t = y+1;
				newCameFrom=object1.cameFrom;
			} 
			
		coordinate = new Coordinate(s, t);
		cameFrom= newCameFrom;
	}

}