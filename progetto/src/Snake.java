import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake {

	/**
	 * A Snake 
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */
	
	
	protected Panel panel;
	private GameScene gameScene;
	private GameObject[][] scene;
	protected Color color;
	protected BufferedImage image;
	protected ArrayList<SnakeObject> snake = new ArrayList<SnakeObject>(10);
	private SnakeHead head;
	private SnakeTail tail;
	private SnakeBody body; 
	private SnakeBody body1;
	//private SnakeBody body2;
    private SnakeBody body3;
    SnakeBody blank; //used to ensure free first move.

	
	private boolean firstMove;
	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isAlive;
	private boolean isGrowing;
	boolean playMusicScore = false; //it is true when eating food
	boolean playMusicUnderscore = false; //it is true when eating spoiled food
	private int cost = 0;  // how big it become when the snake eat
	private int grow = 0; //counter for how many time is size increased
	private int score = 0;
	private int growDirection;
	private int howBig= 5; //initial size

	/**
	 * Constructs a Snake and put it into the GameScene.
	 * 
	 * @param panel
	 *            A generic Panel
	 * @param image
	 *            A Buffered Image used to print.
	 * @param giocoScena
	 *            A GameScene
	 * @param Color
	 *            Snake Body color if there isn't the image
	 * @param grandezza
	 *           grow factor when eating
	 */
	
	
	
	public Snake(Panel pannello, BufferedImage Picture, GameScene giocoScene, Color colore, int grandezza) {
		image = Picture;
		cost = grandezza;
		isAlive = true;
		firstMove = true;
		growDirection = (int) (Math.random() * 4);
		color = colore;
		panel = pannello;
		gameScene = giocoScene;
		scene = gameScene.scene;
		head = new SnakeHead(panel, gameScene, 0, 0);
		tail = new SnakeTail(panel, image, gameScene, 0, 0, color);
		body = new SnakeBody(panel, image, gameScene, 0, 0, color);
		body1 = new SnakeBody(panel, image, gameScene, 0, 0, color);
		//body2 = new SnakeBody(panel, image, gameScene, 0, 0, color);
		body3 = new SnakeBody(panel, image, gameScene, 0, 0, color);
		blank = new SnakeBody(panel, image, gameScene, 0, 0, color);
		randomSnake();
		printSnake();
	}

	// the following are clear  
	
	public boolean getIsAlive() {
		return isAlive;
	}

	public void reprintSnake() {
		randomSnake();
		printSnake();
	}

	public int getScore() {
		return score;
	}

	public void add(SnakeObject object) {
		snake.add(object);
	}

	public void printSnake() {
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).print();
		}
	}

	
	/**
	 * this is the method called when 
	 * the head snake hit something dangerous
	 */
	
	public void die() {
		if (!isAlive) {
			if (gameScene.players == 1) {
				if (snake.size() > 0) {
					snake.get(snake.size() - 1).remove();
					snake.remove(snake.size() - 1);
				}
			} else {
				for (int i = 0; i < snake.size(); i++) {
					snake.get(i).remove();
					snake.remove(i);
				}
			}
		}
	}

	/**
	 * Constructs a Snake and put it into the GameScene.
	 * 
	 * @param a 
	 *           an integer, -1,  0 or 1
	 * @param b
	 *           an integer, -1,  0 or 1
	 *  
	 *           (-1, 0)
	 *           ( 1, 0)
	 *           ( 0,-1)
	 *           ( 0, 1)
	 *           move the snake up, left, down, right.
	 *  
	 */
	
	
	public void normalMove(int a, int b) {
		snake.remove(head);
		head.remove();
		body = new SnakeBody(panel, image, gameScene, head.getX(), head.getY(), color);
		head = new SnakeHead(panel, gameScene, head.getX() + a, head.getY() + b);
		snake.add(body);
		head.print();
		snake.add(head);
		body.print();
		if (!isGrowing) {
			snake.remove(0);
			tail.remove();
			tail = new SnakeTail(panel, image, gameScene, snake.get(0).getX(), snake.get(0).getY(), color);
			snake.remove(0);
			snake.add(0, tail);
			tail.print();
		}
		if (isGrowing) {
			grow--;
			if (grow == 0) {
				isGrowing = false;
			}
		}
		notMoveOnMySelf(a, b);
		firstMove = false;
	}

	
	/**
	 * method for not moving on the opposite direction 
	 * 
	 * @param a 
	 *           an integer, -1,  0 or 1
	 * @param b
	 *           an integer, -1,  0 or 1
	 *  
	 *           (-1, 0)
	 *           ( 1, 0)
	 *           ( 0,-1)
	 *           ( 0, 1)
	 *           move the snake up, left, down, right.
	 *  
	 */
	
	
	public void notMoveOnMySelf(int a, int b) {
		if (a == -1) {
			isMovingRight = false;
			isMovingLeft = false;
			isMovingUp = true;
		}
		if (a == 1) {
			isMovingRight = false;
			isMovingLeft = false;
			isMovingDown = true;
		}
		if (b == -1) {
			isMovingUp = false;
			isMovingDown = false;
			isMovingLeft = true;
		}
		if (b == 1) {
			isMovingUp = false;
			isMovingDown = false;
			isMovingRight = true;
		}
	}

	
	/**
	 * The method recall in the world.
	 * It controls everything concerning the snake.
	 * if is growing
	 * if the snake hit something dangerous and have to die
	 * if it has to do a normal move
	 * ...
	 * 
	 * @param a 
	 *           an integer, -1,  0 or 1
	 * @param b
	 *           an integer, -1,  0 or 1
	 *  
	 *           (-1, 0)
	 *           ( 1, 0)
	 *           ( 0,-1)
	 *           ( 0, 1)
	 *           move the snake up, left, down, right.
	 *  
	 */
	
	public void move(int a, int b) {
		if (isAlive) {
			if ((isMovingDown == false && a == -1) || (isMovingRight == false && b == -1)
					|| (isMovingUp == false && a == 1) || (isMovingLeft == false && b == 1)) {
				if (scene[head.getX() + a][head.getY() + b] == null) {
					normalMove(a, b);
				} else if (scene[head.getX() + a][head.getY() + b].getIsDangerous())
					isAlive = false;
				else if (scene[head.getX() + a][head.getY() + b].getIsFood()) {
					if (!scene[head.getX() + a][head.getY() + b].getIsSpoiled()) {
						playMusicScore = true;
						isGrowing = true;
						scene[head.getX() + a][head.getY() + b].rePrint();
						scene[head.getX() + a][head.getY() + b] = null;
						score = score + cost;
						grow = grow + cost;
						normalMove(a, b);
					} else {
						if (score > 0) {
							score = score - cost;
						}
						playMusicUnderscore = true;
						scene[head.getX() + a][head.getY() + b].rePrint();
						scene[head.getX() + a][head.getY() + b] = null;
						normalMove(a, b);
					}
				}
			} else if (!firstMove)
				move(-a, -b);
		} else
			die();
	}

	
	/**
	 * 
	 * @param body1 
	 *           a Snake Object
	 * 
	 * @return true if exist a free cell on the gameScene matrix
	 *false else.
	 * 
	 */
	
	public boolean isAddable(SnakeObject body1) {
		if (((scene[body1.getX() - 1][body1.getY()] == null) || (scene[body1.getX()][body1.getY() - 1] == null)
				|| (scene[body1.getX() + 1][body1.getY()] == null) || (scene[body1.getX()][body1.getY() + 1] == null)))

			return true;
		else
			return false;
	}

	
	/**
	 * 
	 * Create a randomSnake and print it on the scene.
	 * 
	 * it is semi-Random : there are two parameters, growDirection that is 
	 * randomly created in initialization of the class, and cameFrom 
	 * that controls the direction of the previous piece of body
	 * 
	 * 
	 * that was made to have a much possible linear creation that follows the
	 * wall configuration  
	 * 
	 * 
	 * the initial grow of the snake depends from the attribute howBig.
	 */
	
	public void randomSnake() {
		boolean ok = false;
		boolean bigOk = false;
		while (!bigOk) {

			etichetta:

			while (ok == false) {
				blank = new SnakeBody(panel, image, gameScene, color);
				blank.setColor(Color.PINK);
				blank.print();

				if (!isAddable(blank)) {
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();
					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					head.setRandomNeighbour(blank, growDirection);
					snake.add(0, head);
					head.print();
					bigOk = true;
					ok = true;
				}
				if (!isAddable(head)) {
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();

					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					body1.setRandomNeighbour(head, growDirection, color);
					snake.add(0, body1);
					body1.print();
					bigOk = true;
					ok = true;
				}

	

				SnakeBody oldBody = new SnakeBody(panel, image, gameScene, body1.getX(), body1.getY(), color);
				oldBody.cameFrom=body1.cameFrom;
				for (int j = 0; j < howBig-3 ; j++) {

					if (!isAddable(oldBody)) {
						bigOk = false;
						ok = false;
						for (int i = 0; i < snake.size(); i++) {
							snake.get(i).remove();
						}
						snake.clear();
						blank.remove();
						break etichetta;
					} else {
						SnakeBody body=new SnakeBody(panel, image, gameScene, oldBody.getX(), oldBody.getY(), color);
						body.cameFrom=oldBody.cameFrom;
						body.setRandomNeighbour(oldBody, growDirection, color);
						snake.add(0, body);
						body.print();
						bigOk = true;
						ok = true;
						
						oldBody.setX(body.getX());
						oldBody.setY(body.getY());
						oldBody.cameFrom=body.cameFrom;
						
						body3.setX(body.getX());
						body3.setY(body.getY());
						body3.cameFrom=body.cameFrom;
					}	

				}

				
				if (!isAddable(body3)) {
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();
					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					tail.setRandomNeighbour(body3, growDirection);
					snake.add(0, tail);
					tail.print();
					bigOk = true;
					ok = true;
				}
			}
		}
		setViolatedDirection(body1);
	}

	
	/**
	 * set initial violated direction by controlling the head and
	 * first piece of body.
	 * 
	 */
	
	public void setViolatedDirection(SnakeBody body) {
		if (body.cameFrom == 2)
			isMovingUp = true;
		if (body.cameFrom == 3)
			isMovingLeft = true;
		if (body.cameFrom == 0)
			isMovingDown = true;
		if (body.cameFrom == 1)
			isMovingRight = true;
	}
	
	
	/*
	 * 
	 * 
	 * Old tested method for create snake not general in size.
	public void randomSnake2() {
		boolean ok = false;
		boolean bigOk = false;
		while (!bigOk) {

			etichetta:

			while (ok == false) {
				// blank = new SnakeBody(1, 4, color);
				blank = new SnakeBody(panel, image, gameScene, color);
				blank.setColor(Color.PINK);
				blank.print();
				System.out.println("blank " + blank.cameFrom + " " + blank.getX() + " " + blank.getY());

				if (!isAddable(blank)) {
					System.out.println("notAddable");
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();
					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					head.setRandomNeighbour(blank, growDirection);
					System.out.println("testa " + head.cameFrom + " " + head.getX() + " " + head.getY());
					snake.add(0, head);
					head.print();
					bigOk = true;
					ok = true;
				}
				if (!isAddable(head)) {
					System.out.println("notAddable");
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();

					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {

					body1.setRandomNeighbour(head, growDirection, color);
					System.out.println("body1 " + body1.cameFrom + " " + body1.getX() + " " + body1.getY());
					snake.add(0, body1);
					body1.print();
					bigOk = true;
					ok = true;
				}
				if (!isAddable(body1)) {
					System.out.println("notAddable");
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();

					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					body2.setRandomNeighbour(body1, growDirection, color);
					System.out.println("body2 " + body2.cameFrom + " " + body2.getX() + " " + body2.getY());
					snake.add(0, body2);
					body2.print();
					bigOk = true;
					ok = true;
				}

				if (!isAddable(body2)) {
					System.out.println("notAddable");
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();
					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					body3.setRandomNeighbour(body2, growDirection, color);
					System.out.println("body3 " + body3.cameFrom + " " + body3.getX() + " " + body3.getY());
					snake.add(0, body3);
					body3.print();
					bigOk = true;
					ok = true;
				}

				if (!isAddable(body3)) {
					System.out.println("notAddable");
					bigOk = false;
					ok = false;
					for (int i = 0; i < snake.size(); i++) {
						snake.get(i).remove();
					}
					snake.clear();
					blank.remove();
					break etichetta;
				} else {
					tail.setRandomNeighbour(body3, growDirection);
					System.out.println("tail " + tail.cameFrom + " " + tail.getX() + " " + tail.getY());
					snake.add(0, tail);
					body3.print();
					bigOk = true;
					ok = true;
				}
				// blank.remove(game);

			}

		}
		setViolatedDirection(body1);
	}
	
	
	*/
	
	
	
	
	
	
	
	
	
}



