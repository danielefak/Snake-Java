import java.awt.Color;
import java.util.ArrayList;

public class Menu {
private MyPanel panel;
private BoardPanel panel2;
private GameBoard game;
private int costX;
//private int costY;

	private ArrayList<Coordinate> nibbleCoord = CoordinateIO.read("nibbles.txt");
	private boolean nibbleMatrix[][];
	private ArrayList<Coordinate> theEnd = CoordinateIO.read("final.txt");
	private boolean endGame[][];
	private ArrayList<Coordinate> startCoord = CoordinateIO.read("start.txt");
	private boolean startMatrix[][];
	private ArrayList<Coordinate> blackSnakeCoord = CoordinateIO.read("snakeBlack.txt");
	private boolean blackSnakeMatrix[][];
	private ArrayList<Coordinate> redSnakeCoord = CoordinateIO.read("snakeRed.txt");
	private boolean redSnakeMatrix[][];
	private ArrayList<Coordinate> tongueSnakeCoord = CoordinateIO.read("snakeTongue.txt");
	private boolean tongueSnakeMatrix[][];
	private ArrayList<Coordinate> menuCoord = CoordinateIO.read("menu.txt");
	private boolean menuMatrix[][];
	private ArrayList<Coordinate> onCoord = CoordinateIO.read("on.txt");
	private boolean onMatrix[][];
	private ArrayList<Coordinate> offCoord = CoordinateIO.read("off.txt");
	private boolean offMatrix[][];
	private ArrayList<Coordinate> menuRecordCoord = CoordinateIO.read("recordMenu.txt");
	private boolean menuRecordMatrix[][];
	private ArrayList<Coordinate> scoreCoord = CoordinateIO.read("score.txt");
	private boolean scoreMatrix[][];
	private ArrayList<Coordinate> numbersCoord = CoordinateIO.read("numbers.txt");
	private boolean numberMatrix[][];

	public Menu(GameBoard gioco){
		panel2=gioco.panel2;
		panel=gioco.panel;
		game=gioco;	
		costX=game.costX;
		//costY=game.costY;
		
		
		nibbleMatrix = new boolean[panel.rows][panel.cols];
		startMatrix = new boolean[panel2.rows][panel2.cols];
		onMatrix = new boolean[panel2.rows][panel2.cols];
		offMatrix = new boolean[panel2.rows][panel2.cols];
		endGame = new boolean[panel.rows][panel.cols];
		menuMatrix = new boolean[80][panel2.cols];
		blackSnakeMatrix = new boolean[panel2.rows][panel2.cols];
		redSnakeMatrix = new boolean[panel2.rows][panel2.cols];
		tongueSnakeMatrix = new boolean[panel2.rows][panel2.cols];
		menuRecordMatrix = new boolean[panel2.rows][panel2.cols];
		numberMatrix = new boolean[5][30];
		scoreMatrix = new boolean[panel2.rows][panel2.cols];

		nibbleMatrix();
		startMatrix();
		onMatrix();
		offMatrix();
		endMatrix();
		menuMatrix();
		blackSnakeMatrix();
		redSnakeMatrix();
		tongueSnakeMatrix();
		menuRecordMatrix();
		numberMatrix();
		scoreMatrix();
	}
	
	public void nibbleMatrix() {
		int r = (int) ((panel.cols - 26) / 2) - 1;
		int s = (int) ((panel.rows - 5) / 2) - 1;
		for (int i = 0; i < nibbleCoord.size(); i++)
			nibbleMatrix[nibbleCoord.get(i).getX() + s][nibbleCoord.get(i).getY() + r] = true;
	}

	public void printNibble(int colore) {
		nibbleMatrix();
		for (int i = 0; i < panel.rows; i++)
			for (int j = 0; j < panel.cols; j++)
				if (nibbleMatrix[i][j] == false) {
					panel.drawRectangle(i, j, Color.black);
				} else {
					panel.drawRectangle(i, j, new Color(colore, colore, colore));
				}

	}

	public void endMatrix() {

		int r = (int) ((panel.cols - 15) / 2) - 1;
		int s = (int) ((panel.rows - 11) / 2) - 1;
		for (int i = 0; i < theEnd.size(); i++)
			endGame[theEnd.get(i).getX() + s][theEnd.get(i).getY() + r] = true;
	}

	public void printEndMatrix(int colore) {

		for (int i = 0; i < panel.rows; i++)
			for (int j = 0; j < panel.cols; j++)
				if (endGame[i][j] == false) {
					panel.drawRectangle(i, j, Color.black);
				} else {
					panel.drawRectangle(i, j, new Color(colore, colore, colore));
				}
	}
	
	public void blackSnakeMatrix() {
		int r = (int) ((panel2.cols) - 40);
		int s = (int) ((panel2.rows - 32));
		for (int i = 0; i < blackSnakeCoord.size(); i++)
			blackSnakeMatrix[blackSnakeCoord.get(i).getX() + s][blackSnakeCoord.get(i).getY() + r] = true;
	}

	public void redSnakeMatrix() {
		int r = (int) ((panel2.cols) - 40);
		int s = (int) ((panel2.rows - 32));
		for (int i = 0; i < redSnakeCoord.size(); i++)
			redSnakeMatrix[redSnakeCoord.get(i).getX() + s][redSnakeCoord.get(i).getY() + r] = true;
	}

	public void tongueSnakeMatrix() {
		int r = (int) ((panel2.cols) - 40);
		int s = (int) ((panel2.rows - 32));
		for (int i = 0; i < tongueSnakeCoord.size(); i++)
			tongueSnakeMatrix[tongueSnakeCoord.get(i).getX() + s][tongueSnakeCoord.get(i).getY() + r] = true;
	}

	public void startMatrix() {
		// int r = (int) ((panel.cols - 26) / 2) - 1;
		int s = (int) ((panel2.rows - 30) / 2) - 1;
		for (int i = 0; i < startCoord.size(); i++)
			startMatrix[startCoord.get(i).getX() + s][startCoord.get(i).getY() + 2] = true;
	}

	public void onMatrix() {
		// int r = (int) ((panel.cols - 26) / 2) - 1;
		int s = (int) ((panel2.rows - 30) / 2) - 1;
		for (int i = 0; i < onCoord.size(); i++)
			onMatrix[onCoord.get(i).getX() + s][onCoord.get(i).getY() + 2] = true;
	}

	public void offMatrix() {
		// int r = (int) ((panel.cols - 26) / 2) - 1;
		int s = (int) ((panel2.rows - 30) / 2) - 1;
		for (int i = 0; i < offCoord.size(); i++)
			offMatrix[offCoord.get(i).getX() + s][offCoord.get(i).getY() + 2] = true;
	}

	public void printStart(int colore, int cursor) {
		int s = (int) ((panel2.rows - 30) / 2) - 1;
		for (int i = 0; i < panel2.rows; i++)
			for (int j = 0; j < panel2.cols; j++) {
				if (!game.isMute) {
					if (startMatrix[i][j] == true || blackSnakeMatrix[i][j] == true || onMatrix[i][j]) {
						panel2.drawRectangle(i, j, Color.white);
					} else if (redSnakeMatrix[i][j]) {
						panel2.drawRectangle(i, j, new Color(255, 215, 0));// new
																			// //////////////////////////
						// Color(230,230,
						// 230));
					} else if (tongueSnakeMatrix[i][j] == true
							|| i > s + 8 * cursor && i <= s + 7 + 8 * cursor && j < 40) {
						panel2.drawRectangle(i, j, new Color(colore, 0, 0));
					} else
						panel2.drawRectangle(i, j, Color.black);
				} else {
					if (startMatrix[i][j] == true || blackSnakeMatrix[i][j] == true || offMatrix[i][j]) {
						panel2.drawRectangle(i, j, Color.white);
					} else if (redSnakeMatrix[i][j]) {
						panel2.drawRectangle(i, j, Color.green);// new
																// Color(230,230,
																// 230));
					} else if (tongueSnakeMatrix[i][j] == true
							|| i > s + 8 * cursor && i <= s + 7 + 8 * cursor && j < 40) {
						panel2.drawRectangle(i, j, new Color(colore, 0, 0));
					} else
						panel2.drawRectangle(i, j, Color.black);
				}

			}
	}

	public void menuMatrix() {
		for (int i = 0; i < menuCoord.size(); i++)
			menuMatrix[menuCoord.get(i).getX()][menuCoord.get(i).getY() + 5] = true;
	}

	
	public void menuRecordMatrix() {
		int s = (int) ((panel2.rows - 24) / 2) - 1;
		for (int i = 0; i < menuRecordCoord.size(); i++)
			menuRecordMatrix[menuRecordCoord.get(i).getX() + s][menuRecordCoord.get(i).getY() + 5] = true;
	}

	
	public void printMenuRecord(int colore, int cursor) {
		int s = (int) ((panel2.rows - 24) / 2) - 1;
		for (int i = 0; i < panel2.rows; i++)
			for (int j = 0; j < panel2.cols; j++) {
				if (menuRecordMatrix[i][j] == true) {
					panel2.drawRectangle(i, j, Color.white);
				} else if (i > s + 8 * cursor && i <= s + 7 + 8 * cursor) {
					panel2.drawRectangle(i, j, new Color(colore, 0, 0));
				} else {
					panel2.drawRectangle(i, j, Color.black);
				}
			}
	}

	public void printMenu(int colore, int cursor) {
		for (int i = 0; i < panel2.rows; i++)
			for (int j = 0; j < panel2.cols; j++)
				if (menuMatrix[(i + 8 * cursor) % 80][j] == true) {
					panel2.drawRectangle(i, j, Color.white);
				} else if (i > 0 && i < 8) {
					panel2.drawRectangle(i, j, new Color(colore, 0, 0));
				} else
					panel2.drawRectangle(i, j, Color.black);
	}

	public void endGame(int a, int b) {
		if (endGame[a][b] == false) {
			panel.drawRectangle(a, b, Color.black);
		} else {
			panel.drawRectangle(a, b, Color.white);
		}
	}

	public void numberMatrix() {
		for (int i = 0; i < numbersCoord.size(); i++)
			numberMatrix[numbersCoord.get(i).getX()][numbersCoord.get(i).getY()] = true;
	}

	public void scoreMatrix() {
		int s = (int) ((panel2.rows - costX) + (costX - 5) / 2) - 2;
		for (int i = 0; i < scoreCoord.size(); i++)
			scoreMatrix[scoreCoord.get(i).getX() + s][scoreCoord.get(i).getY() + 3] = true;
	}

	public void printScore() {
		for (int i = panel2.rows - costX; i < panel2.rows; i++) {
			for (int j = 0; j < panel2.cols; j++)
				if (scoreMatrix[i][j] == true) {
					panel2.drawRectangle(i, j, new Color(192, 192, 192));
				} else
					panel2.drawRectangle(i, j, Color.black);
		}

		drawScore(game.gameScene.snake.getScore(), 0, Color.green);

		if (game.gameScene.players == 2)
			drawScore(game.gameScene.snake2.getScore(), (int) (panel2.cols / 2) - 10, Color.red);
	}

	/**
	 * 
	 * 
	 */

	public void drawScore(int score, int sposta, Color color) {
		if (score != 0) {
			int numDigit = (int) Math.log10(score);
			for (int i = 0; i <= numDigit; i++) {
				int bigDigit = (int) (score / Math.pow(10, numDigit - i));
				printDigit(bigDigit, i, sposta, color);
				score = (int) (score - bigDigit * Math.pow(10, numDigit - i));
			}

		} else
			printDigit(0, 0, sposta, color);
	}

	/**
	 * 
	 * 
	 */

	public void printDigit(int digit, int position, int sposta, Color color) {
		int s = (int) ((panel2.rows - costX) + (costX - 5) / 2) - 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				if (numberMatrix[i][digit * 3 + j] == true) {
					panel2.drawRectangle(i + s, 30 + 3 + 4 * position + j + sposta, color);
				}
			}
		}
	}
}
