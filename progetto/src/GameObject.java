import java.awt.*;

/**
 * A generic Game object
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */

abstract public class GameObject {
	
	public Coordinate coordinate;
	private Color color;
	private boolean isDangerous;
	private boolean isFood;
	private boolean isWall;
	private boolean isSnake;
	private boolean isSpoiled;
	GameScene gameScene;
	GameObject scene[][];
	//BoardPanel panel;
     Panel panel;
       
	public GameObject(Panel pannello, GameScene giocoScena, int a, int b) {
		coordinate = new Coordinate(a, b);
		panel=pannello;
		gameScene=giocoScena;
		scene=giocoScena.scene;
	}

	public GameObject(Panel pannello, GameScene giocoScena) {
		panel=pannello;
		gameScene=giocoScena;
		scene=giocoScena.scene;
		setRandomCoordinate();
	}

	public void setRandomCoordinate() {    
		int a = panel.getRows();
		int b = panel.getColumns();
		int x = (int) (Math.random() * a);
		int y = (int) (Math.random() * b);
		while (scene[x][y] != null) {
		x = (int) (Math.random() * a);
		y = (int) (Math.random() * b);
		}
		coordinate = new Coordinate(x, y);		
	}
	
	public Color getColor() {
		return color;
	}

	public boolean getIsDangerous() {
		return isDangerous;
	}

	public void setIsDangerous(boolean dangerosity) {
		isDangerous = dangerosity;
	}
	
	public boolean getIsWall() {
		return isWall;
	}

	public void setIsWall(boolean isAWall){
		isWall= isAWall;
	}

	public boolean getIsFood() {
		return isFood;
	}

	public void setIsFood(boolean eatable) {
		isFood = eatable;
	}

	public boolean getIsSnake() {
		return isSnake;
	}
	
	
	public void setIsSpoiled(boolean Spoiled) {
		isSpoiled = Spoiled;
	}

	public boolean getIsSpoiled() {
		return isSpoiled;
	}

	public void setIsSnake(boolean snakerosity) {
		isSnake = snakerosity;
	}
	public void setColor(Color colore) {
		color = colore;
	}

	public int getX() {
		return coordinate.getX();
	}

	public int getY() {
		return coordinate.getY();
	}

	public void setX(int x) {
		coordinate.setX(x);
	}

	public void setY(int y) {
		coordinate.setY(y);
	}

	abstract public void print();

	abstract public void rePrint( );

	public void remove( ) {
		panel.clear(getX(), getY());
		scene[getX()][getY()] = null;
	}

}