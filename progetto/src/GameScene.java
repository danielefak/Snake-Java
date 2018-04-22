import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameScene {

	/**
	 * The scene of the game. Collect all object of the game and the permitted
	 * the interactions
	 * 
	 * Collect all options that concerns the game: *players(number of snake)
	 * *number of food *number of spoiled Food *wallConfiguration *.....
	 * 
	 * Collect all Images used to print gameObjects.
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */

	private Panel panel;

	private BufferedImage wallPic;
	private BufferedImage snake1Pic;
	private BufferedImage snake2Pic;
	private BufferedImage foodPic;
	private BufferedImage backgroundPic;

	protected ArrayList<Coordinate> border = new ArrayList<Coordinate>(10);
	protected GameObject[][] scene; //all gameObject are there when initialized
	protected WallConfiguration borderWall;
	protected Food food;
	protected SpoiledFood spoiledFood;
	protected Snake snake;
	protected Snake snake2;

	protected int players;
	private double percentWall;
	private int background;

	protected ArrayList<Coordinate> livelloList = new ArrayList<Coordinate>(10);
	protected WallConfiguration livello;
	protected ArrayList<Coordinate> randomList = new ArrayList<Coordinate>(10);
	protected WallConfiguration random;

	/**
	 * Constructs a GameScene initialized all game variables
	 * 
	 * @param isRandom
	 *            is a boolean that indicate if the wall configuration is random
	 *            or loaded from a *txt file.
	 * @param numFood
	 *            the number of food in the game
	 * @param numSpoiledFood
	 *            the number of Spoiled food in the game
	 * @param sfondo
	 *            a number from 0 to 3 that indicates what background to use
	 * @param livelloLista
	 *           is the arrayList of coordinate of walls loaded from a file
	 * 
	 * @param giocatori 
	 *           is the number of players
	 *           
	 *           
	 * then there are all images used
	 * 
	 */

	public GameScene(boolean isRandom, int numFood, int numSpoiledFood, int sfondo, ArrayList<Coordinate> livelloLista,
			Panel pannel, int wallpercent, int grandezza, BufferedImage backgroundImage, BufferedImage foodImage,
			BufferedImage snake1Image, BufferedImage snake2Image, BufferedImage wallImage,
			BufferedImage wallImageAlternative, int giocatori) {

		background = sfondo;
		percentWall = wallpercent;

		if (sfondo == 1)
			wallPic = wallImage;
		else
			wallPic = wallImageAlternative;

		snake1Pic = snake1Image;
		snake2Pic = snake2Image;
		foodPic = foodImage;
		backgroundPic = backgroundImage;

		panel = pannel;
		players = giocatori;
		scene = new GameObject[panel.getRows()][panel.getColumns()];
		borderInList();
		borderWall = new WallConfiguration(panel, wallPic, this, border);

		if (!isRandom) {
			livelloList = livelloLista;
			livello = new WallConfiguration(panel, wallPic, this, livelloList);
		}

		if (isRandom) {
			randomList();
			random = new WallConfiguration(panel, wallPic, this, randomList);
		}

		snake = new Snake(panel, snake1Pic, this, Color.blue, grandezza);

		players = giocatori;

		if (players == 2) {
			snake2 = new Snake(panel, snake2Pic, this, Color.ORANGE, grandezza);
			snake2.blank.remove();
		}
		snake.blank.remove();

		for (int i = 0; i < numFood; i++) {
			food = new Food(panel, foodPic, this);
		}
		for (int i = 0; i < numSpoiledFood; i++) {
			spoiledFood = new SpoiledFood(panel, this);
		}
		panel.repaint();
	}

	/**
	 * Fill the arrayList border with the panel boorder coordinate
	 * 
	 */
	
	public void borderInList() {
		for (int i = 0; i < panel.getColumns(); i++) {
			Coordinate c = new Coordinate(0, i);
			border.add(c);
		}
		for (int i = 0; i < panel.getColumns(); i++) {
			Coordinate c = new Coordinate(panel.getRows() - 1, i);
			border.add(c);
		}
		for (int i = 0; i < panel.getRows(); i++) {
			Coordinate c = new Coordinate(i, 0);
			border.add(c);
		}
		for (int i = 0; i < panel.getRows(); i++) {
			Coordinate c = new Coordinate(i, panel.getColumns() - 1);
			border.add(c);
		}

	}

	/**
	 * Print background choosing from 4 possible opportunity
	 * 
	 */
	
	public void printBackground() {
		if (background == 1)
			printBackgroundMuro();
		else if (background == 2)
			printBackgroundColor();
		else if (background == 3)
			printBackgroundBlue();
		else if (background == 4)
			// boardPanel.setBackground(Color.white);
			printBackgroundGray();
	}

	/**
	 * Initialize the wall configuration random, with a random wall configuration
	 * 
	 */
	
	public void randomList() {
		int numberWalls = (int) (panel.getRows() * panel.getColumns() * percentWall / 100);
		int randSize = 0;
		int x;
		int y;
		while (numberWalls > 0) {
			int randDirection = (int) (Math.random() * 4);
			if (randDirection == 0 || randDirection == 2)
				randSize = (int) (Math.random() * panel.getRows());
			if (randDirection == 1 || randDirection == 3)
				randSize = (int) (Math.random() * panel.getColumns());
			do {
				x = (int) (Math.random() * (panel.getRows() - 2) + 2);
				y = (int) (Math.random() * (panel.getColumns() - 2) + 2);
			} while (scene[x - 1][y] != null || scene[x - 1][y - 1] != null || scene[x - 1][y + 1] != null

					|| scene[x][y] != null || scene[x][y - 1] != null || scene[x][y + 1] != null

					|| scene[x + 1][y] != null || scene[x + 1][y - 1] != null || scene[x + 1][y + 1] != null);
			Coordinate d;

			for (int i = 0; i < randSize; i++) {

				if (randDirection == 0) {
					x = x - 1;
				}

				if (randDirection == 1) {
					y = y - 1;
				}

				if (randDirection == 2) {
					x = x + 1;
				}

				if (randDirection == 3) {
					y = y + 1;
				}
				d = new Coordinate(x, y);
				if (x > 1 && x < panel.getRows() - 2 && y > 1 && y < panel.getColumns() - 2) {
					if (randDirection == 0 && scene[x - 1][y] == null && scene[x - 1][y - 1] == null
							&& scene[x - 1][y + 1] == null

							&& scene[x][y - 1] == null && scene[x][y + 1] == null

							&& scene[x + 1][y - 1] == null && scene[x + 1][y + 1] == null) {
						randomList.add(d);
						numberWalls--;
					} else if (randDirection == 0)
						x = x - 3;

					if (randDirection == 1 && scene[x - 1][y] == null && scene[x - 1][y - 1] == null
							&& scene[x - 1][y + 1] == null && scene[x][y - 1] == null && scene[x + 1][y] == null
							&& scene[x + 1][y - 1] == null && scene[x + 1][y + 1] == null) {
						randomList.add(d);
						numberWalls--;
					} else if (randDirection == 1)
						y = y - 3;

					if (randDirection == 2 && scene[x - 1][y - 1] == null && scene[x - 1][y + 1] == null
							&& scene[x][y - 1] == null && scene[x][y + 1] == null && scene[x + 1][y] == null
							&& scene[x + 1][y - 1] == null && scene[x + 1][y + 1] == null) {
						randomList.add(d);
						numberWalls--;
					} else if (randDirection == 2)
						x = x + 3;

					if (randDirection == 3 && scene[x - 1][y] == null && scene[x - 1][y - 1] == null
							&& scene[x - 1][y + 1] == null && scene[x][y + 1] == null && scene[x + 1][y] == null
							&& scene[x + 1][y - 1] == null && scene[x + 1][y + 1] == null) {
						randomList.add(d);
						numberWalls--;
					} else if (randDirection == 3)
						y = y + 3;
				}

				random = new WallConfiguration(panel, wallPic, this, randomList);
			}

		}

	}

	
	/*Some methods for Background.
	Fill the panel with a color or an image
	if the scene matrix cell is free
	(so we have to recall those methods every iteration).
	Maybe a little expensive but I think is really more fancy
	than a simple monocromatic background
	*/
	
	

	
	public void printBackgroundColor() {
		for (int i = 0; i < panel.getRows(); i++)
			for (int j = 0; j < panel.getColumns(); j++)
				if (scene[i][j] == null) {
					if (10 * (i + 2) < 255 && 10 * (j + 2) < 255)
						panel.drawRectangle(i, j, new Color(10 * (j + 2), 10 * (i + 2), 10 * (j + 2)));
					else if (10 * (i + 2) > 255 && 10 * (j + 2) < 255)
						panel.drawRectangle(i, j, new Color(10 * (j + 2), 500 - 10 * (i + 2), 10 * (j + 2)));

					else if (10 * (i + 2) < 255 && 10 * (j + 2) > 255 && 10 * (j + 2) < 500)
						panel.drawRectangle(i, j, new Color(500 - 10 * (j + 2), 10 * (i + 2), 500 - 10 * (j + 2)));

					else if (10 * (i + 2) >= 255 && 10 * (j + 2) >= 255 && 10 * (j + 2) < 500)
						panel.drawRectangle(i, j,
								new Color(500 - 10 * (j + 2), 500 - 10 * (i + 2), 500 - 10 * (j + 2)));
					else if (10 * (j + 2) >= 500 && 10 * (i + 2) >= 255)
						panel.drawRectangle(i, j,
								new Color(10 * (j + 2) - 500, 500 - 10 * (i + 2), 10 * (j + 2) - 500));
					else if (10 * (j + 2) >= 500 && 10 * (i + 2) < 255)
						panel.drawRectangle(i, j, new Color(10 * (j + 2) - 500, 10 * (i + 2), 10 * (j + 2) - 500));

				}
	}

	public void printBackgroundGray() {
		for (int i = 0; i < panel.getRows(); i++)
			for (int j = 0; j < panel.getColumns(); j++)
				if (scene[i][j] == null) {
					if (8 * (i + 2) < 255)
						panel.drawRectangle(i, j, new Color(8 * i + 20, 8 * i + 20, 8 * i + 20));
				}
	}

	public void printBackgroundBlue() {
		for (int i = 0; i < panel.getRows(); i++)
			for (int j = 0; j < panel.getColumns(); j++)
				if (scene[i][j] == null) {
					if (7 * (i + 20) < 255)
						panel.drawRectangle(i, j, new Color(0, 5 * (i + 20), 7 * (i + 20)));
					else if (7 * (i + 20) < 500 && 5 * (i + 20) < 500 && 5 * (i + 20) > 250)
						panel.drawRectangle(i, j, new Color(0, 500 - 5 * (i + 20), 500 - 7 * (i + 20)));
					else if (7 * (i + 20) < 500 && 5 * (i + 20) < 250)
						panel.drawRectangle(i, j, new Color(0, 5 * (i + 20), 500 - 7 * (i + 20)));

				}
	}

	public void printBackgroundMuro() {
		for (int i = 0; i < panel.getRows(); i++)
			for (int j = 0; j < panel.getColumns(); j++)
				if (scene[i][j] == null) {
					panel.drawImage(i, j, backgroundPic);
				}
	}

}
