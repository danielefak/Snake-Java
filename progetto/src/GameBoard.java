
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import javax.sound.sampled.Clip;

public class GameBoard extends MusicKeyAnimationFrame {

	/**
	 * GameBoard is created to take care of what concerning key event and
	 * animation.
	 * 
	 * It takes care of a menu written one by one coordinate on external *txt
	 * files
	 * 
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */

	private static final long serialVersionUID = 1L;
	// int factor = 2;


	GameScene gameScene;
Menu menu;
	// some menu variables
	private int cursor = 0;
	private int startMenuitems = 4;
	private int menuItems = startMenuitems;
	private int menuRecordItems = 3;
	private int optionsItems = 10;
	private int color = 1;
	private int colore2 = 250;
	// private int time = 0;

	// the movement int modified with the key event
	private int a = 0;
	private int b = 0;

	// controlling some animation int
	private int x = 0;
	private int y = 0;

	// some flag that controls where we are in the game
	private boolean appare = true;
	private boolean startOptions;
	private boolean inMenu = false;
	private boolean inMenuRecord = false;
	private boolean oneTime = true;
	private boolean firstTime = true;
	private boolean inSetting = false;
	private boolean canStart = false;
	private boolean initial = true;
	private boolean canPause = false;
	
	protected boolean isMute = false;

	ArrayList<Coordinate> options = CoordinateIO.read("options.txt");

	/**
	 * Constructs a new frame using a super constructor. Initialize matrices
	 * with all MENU written came from *txt files.
	 * 
	 * Initialize all images used form files.
	 * 
	 * 
	 * @param title
	 *            the frame title
	 * @param rows
	 *            the frame number of rows
	 * @param columns
	 *            the frame number of columns
	 * @param size
	 *            the size of the cell
	 * 
	 */

	public GameBoard(String title, int rows, int columns, int size) {
		super(title, rows, columns, size);

		menu=new Menu(this);
		BufferedImage wallPic = readImage(new File("background22.jpg"));
		// BufferedImage wallPicAlternative = readImage(new File("wall.png"));
		BufferedImage snake2Pic = readImage(new File("snake4.png"));
		BufferedImage snake1Pic = readImage(new File("snake3.png"));
		BufferedImage foodPic = readImage(new File("food0.png"));
		BufferedImage backgroundPic = readImage(new File("background23333.jpg"));

		wallImage = wallPic;
		wallImageAlternative = wallPic; // wallPicAlternative;
		snake1Image = snake1Pic;
		snake2Image = snake2Pic;
		foodImage = foodPic;
		backgroundImage = backgroundPic;

		setSavedOptions();

		panel2.setBackground(Color.black);

		

		heart.loop(0);
		playAnimation();
	}

	/**
	 * Override to not accepting key event when in pause
	 * 
	 * 
	 */

	@Override
	public void pauseAnimation() {
		isAnimPaused = true;
		canStart = false;
	}

	/**
	 * the method that take care of what happens when clicking on keyboard in
	 * the game and in the menu.
	 * 
	 */
	@Override
	protected void processKey(KeyEvent e) {
		if (inSetting) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (!isMute)
					if (!isMute) {

						bip.setFramePosition(0);
						bip.loop(0);
						bip.start();
					}

				cursor = ((cursor - 1 + menuItems) % menuItems);
				color = 0;

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (!isMute) {
					bip.setFramePosition(0);
					bip.loop(0);
					bip.start();
				}

				cursor = ((cursor + 1 + menuItems) % menuItems);
				color = 0;

			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (inMenu) {
					if (!isMute) {
						bip2.setFramePosition(0);
						bip2.loop(0);
						bip2.start();
					}
					cursor = 0;
					startOptions = true;
					inMenu = false;
					menuItems = startMenuitems;
				}

				else if (inMenuRecord) {
					if (!isMute) {
						bip2.setFramePosition(0);
						bip2.loop(0);
						bip2.start();
					}
					cursor = 0;
					startOptions = true;
					inMenuRecord = false;
					menuItems = startMenuitems;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (inMenu) {
					if (!isMute) {
						bip2.setFramePosition(0);
						bip2.loop(0);
						bip2.start();
					}
					if (cursor % menuItems == 0) {
						cursor = 0;
						startOptions = true;
						inMenu = false;
						menuItems = startMenuitems;
					}
					if (cursor % menuItems == 1)
						askMultyplayer();
					if (cursor % menuItems == 2)
						openFile();
					if (cursor % menuItems == 3)
						if (isShowing())
							this.showMessageDialog("Welcome in NIBBLES Game.\n\n"
									+ "FIRST player moves with ARROWS Keys and is the BLUE snake\n"
									+ "SECOND player moves with A-S-D-W  Keys and is the  RED snake\n"
									+ "\nPress P to puse/resume the game " + "\n\nHAVE FUN!!", "Wecome");
					if (cursor % menuItems == 4)
						askWall();
					if (cursor % menuItems == 5)
						askVelocity();
					if (cursor % menuItems == 6)
						askGrow();
					if (cursor % menuItems == 7)
						askNumberFood();
					if (cursor % menuItems == 8)
						askBackground();
					if (cursor % menuItems == 9) {
						save();
					}

				} else if (startOptions) {
					if (!isMute) {
						bip2.setFramePosition(0);
						bip2.loop(0);
						bip2.start();
					}
					if (cursor % startMenuitems == 0)
						optionSetted = true;

					if (cursor % startMenuitems == 1) {
						inMenu = true;
						startOptions = false;
						menuItems = optionsItems;
						cursor = 0;
					}
					if (cursor % startMenuitems == 2) {
						inMenuRecord = true;
						startOptions = false;
						menuItems = menuRecordItems;
						cursor = 0;
					}
					if (cursor % startMenuitems == 3) {
						isMute = !isMute;
						if (isMute) {
							menuMusic.stop();
						} else {
							menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
							bip2.setFramePosition(0);
							bip2.loop(0);
							bip2.start();
						}

					}
				} else if (inMenuRecord) {
					if (!isMute) {
						bip2.setFramePosition(0);
						bip2.loop(0);
						bip2.start();
					}
					if (cursor % menuItems == 0) {
						cursor = 0;
						startOptions = true;
						inMenuRecord = false;
						menuItems = startMenuitems;
					}
					if (cursor % menuItems == 1) {
						readRecord();
					}
					if (cursor % menuItems == 2) {
						clearRecord();
					}

				}

			}
		}
		if (canPause) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (this.isAnimPaused)
					playAnimation();
				else
					pauseAnimation();
			}
		}

		if (canStart) {
			if (e.getKeyCode() == KeyEvent.VK_UP)
				a = 1;
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				a = 2;
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				a = 3;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				a = 4;
			if (players == 2) {
				if (e.getKeyCode() == KeyEvent.VK_W)
					b = 1;
				if (e.getKeyCode() == KeyEvent.VK_A)
					b = 2;
				if (e.getKeyCode() == KeyEvent.VK_S)
					b = 3;
				if (e.getKeyCode() == KeyEvent.VK_D)
					b = 4;
			}
		}
	}

	/**
	 * loadgame options from a file
	 * 
	 */

	public void setSavedOptions() {
		players = options.get(0).getX();
		percentWall = options.get(1).getX();
		gameDelay = options.get(2).getX();
		grown = options.get(3).getX();
		numFood = options.get(4).getX();
		background = options.get(5).getX();
		numSpoiledFood = options.get(6).getX();
	}

	/**
	 * Allow to save options in a file that is loaded when the game start. (to
	 * don't change setting every time open a new frame)
	 * 
	 */

	public void save() {
		int sure = this.showInputDialogInt("Press 1 if are you sure to save \n your current options for next times", 1);
		if (sure == 1) {
			options.clear();
			CoordinateIO.write(optionsFile, options);
			options.add(0, new Coordinate(players, 0));
			options.add(1, new Coordinate(percentWall, 0));
			options.add(2, new Coordinate(gameDelay, 0));
			options.add(3, new Coordinate(grown, 0));
			options.add(4, new Coordinate(numFood, 0));
			options.add(5, new Coordinate(background, 0));
			options.add(6, new Coordinate(numSpoiledFood, 0));
			CoordinateIO.write(optionsFile, options);
		}
	}

	/**
	 * The animations
	 * 
	 */

	@Override
	protected void animateInit() {

		if (play == true) {
			if (!optionSetted) {

				if (firstTime) {
					if (isShowing())
						this.showMessageDialog(
								"Welcome in NIBBLES Game.\n\n " + "1) EAT Food (yellow) to increase your score \n "
										+ "2) AVOID Spoiled Food (red) or your score will decrease !! \n \n "
										+ "FIRST player moves with ARROWS Keys and is the BLUE snake\n"
										+ "SECOND player moves with A-S-D-W  Keys and is the  RED snake\n"
										+ "\nPress P to puse/resume the game " + "\n\nHAVE FUN!!",
								"Wecome");
					firstTime = false;
				}

				x = 0;
				y = 0;
				a = 0;
				b = 0;

				canStart = false;
				oneTime = true;
				inSetting = true;
				startOptions = true;
				menuOptions.setEnabled(true);
				menuRecord.setEnabled(true);
				start.setEnabled(true);

				if (!isMute) {
					if (isShowing())
						menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}

			if (optionSetted) {
				menuOptions.setEnabled(false);
				menuRecord.setEnabled(false);
				start.setEnabled(false);
				panel2.clear();
				gameScene = new GameScene(casuale, numFood, numSpoiledFood, background, level, panel, percentWall,
						grown, backgroundImage, foodImage, snake1Image, snake2Image, wallImage, wallImageAlternative,
						players);
				inSetting = false;

				if (!isMute) {
					menuMusic.stop();
					theme.setFramePosition(0);
					if (isShowing())
						theme.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}

			this.repaint();

		}
	}

	@Override
	protected void animateNext() {

		if (initial) {
			play = false;
			setAnimationDelay(6);

			if (appare) {
				menu.printNibble(color);
				repaint();
				color++;
				if (color == 250)
					appare = false;
			}
			if (!appare) {
				menu.printNibble(color);
				repaint();
				color--;
			}
			if (color == 0) {
				initial = false;
				appare = true;
				play = true;
				animateInit();
			}
		}

		if (inSetting) {
			heart.close();

			if (startOptions) {
				setAnimationDelay(2);
				if (appare) {
					menu.printStart(color, cursor);
					repaint();
					color++;
					if (color == 250)
						appare = false;
				}
				if (!appare) {
					menu.printStart(color, cursor);
					repaint();
					if (color > 0)
						color--;
				}
				if (color == 0) {
					appare = true;
				}
				if (optionSetted) {
					inSetting = false;
					animateInit();
				}
			}

			if (inMenu) {
				setAnimationDelay(2);
				if (appare) {
					menu.printMenu(color, cursor);
					repaint();
					color++;
					if (color == 250)
						appare = false;
				}
				if (!appare) {
					menu.printMenu(color, cursor);
					repaint();
					if (color > 0)
						color--;
				}

				if (color == 0) {
					appare = true;
				}
				if (optionSetted) {
					inMenu = false;
					cursor = 0;
					animateInit();
				}
			}

			if (inMenuRecord) {
				setAnimationDelay(2);
				if (appare) {
					menu.printMenuRecord(color, cursor);
					repaint();
					color++;
					if (color == 250)
						appare = false;
				}
				if (!appare) {
					menu.printMenuRecord(color, cursor);
					repaint();
					if (color > 0)
						color--;
				}

				if (color == 0) {
					appare = true;
				}
				if (optionSetted) {
					inMenu = false;
					cursor = 0;
					animateInit();
				}
			}

		}

		if (play && optionSetted) {
			if (a == 0 && b == 0)
				menu.printScore();

			canPause = true;
			setAnimationDelay(gameDelay);
			canStart = true;
			repaint();
			switch (a) {
			case 1:
				gameScene.snake.move(-1, 0);
				break;
			case 2:
				gameScene.snake.move(0, -1);
				break;
			case 3:
				gameScene.snake.move(1, 0);
				break;
			case 4:
				gameScene.snake.move(0, 1);
				break;
			}
			if (players == 2) {
				switch (b) {
				case 1:
					gameScene.snake2.move(-1, 0);
					break;
				case 2:
					gameScene.snake2.move(0, -1);
					break;
				case 3:
					gameScene.snake2.move(1, 0);
					break;
				case 4:
					gameScene.snake2.move(0, 1);
					break;
				}

			}

			if (gameScene.snake.playMusicScore) {
				menu.printScore();
				if (!isMute) {
					score.setFramePosition(0);
					if (isShowing())
						score.loop(0);
					score.start();
					gameScene.snake.playMusicScore = false;
				}
			}

			if (players > 1 && gameScene.snake2.playMusicScore) {
				menu.printScore();
				score.setFramePosition(0);
				if (isShowing())
					score.loop(0);
				score.start();
				gameScene.snake2.playMusicScore = false;
			}

			if (gameScene.snake.playMusicUnderscore) {
				menu.printScore();
				if (!isMute) {
					underscore.setFramePosition(0);
					if (isShowing())
						underscore.loop(0);
					underscore.start();
					gameScene.snake.playMusicUnderscore = false;
				}
			}

			if (players > 1 && gameScene.snake2.playMusicUnderscore) {
				menu.printScore();
				underscore.setFramePosition(0);
				if (isShowing())
					underscore.loop(0);
				underscore.start();
				gameScene.snake2.playMusicUnderscore = false;
			}

			if (x == 0 && y == 0)
				gameScene.printBackground();

			if (players == 1) {
				if (!gameScene.snake.getIsAlive()) {
					setAnimationDelay(25);
				}

			}

			if ((players == 1 && gameScene.snake.snake.size() == 0)
					|| (players == 2 && gameScene.snake.snake.size() == 0 && gameScene.snake2.snake.size() == 0)) {
				canPause = false;

				if (x < panel.rows) {
					setAnimationDelay(5); /////////////////////////
					if (!isMute && oneTime) {
						theme.stop();
						gameOver.setFramePosition(0);
						if (isShowing())
							gameOver.loop(0);
						gameOver.start();
						oneTime = false;
					}
					menu.endGame(x, y);
					y++;
				}
				if (y == panel.cols) {
					y = 0;
					x++;
				}
				if (x == panel.rows) {
					setAnimationDelay(3); //////////////////
					menu.printEndMatrix(colore2);
					colore2--;
				}
				if (colore2 == 0) {
					animateFinal2();
				}
			}
			repaint();
		}
	}

	@Override
	protected void animateFinal() {
		playAnimation();
	}

	protected void animateFinal2() {
		optionSetted = false;
		casuale = true;
		level.clear();
		color = 1;
		colore2 = 250;

		x = 0;
		y = 0;
		a = 0;
		b = 0;

		if (players == 1) {
			this.showMessageDialog("Score: " + gameScene.snake.getScore() + " punti", "Game Over");
			if (gameScene.snake.getScore() > recordCoord.get(0).getX()) {
				SetRecord(gameScene.snake.getScore());
				this.showMessageDialog("Congratulations, " + gameScene.snake.getScore() + " is a new record!!",
						"New Record");
			}

		}

		if (players == 2) {
			int bestScore;
			this.showMessageDialog("Player 1 SCORE: " + gameScene.snake.getScore() + " \nPlayer 2 SCORE: "
					+ gameScene.snake2.getScore(), "Game Over");

			if (gameScene.snake.getScore() > gameScene.snake2.getScore()) {
				bestScore = gameScene.snake.getScore();
			} else {

				bestScore = gameScene.snake2.getScore();

			}
			if (bestScore > recordCoord.get(0).getX()) {
				SetRecord(bestScore);
				this.showMessageDialog("Congratulations, " + bestScore + "  is a new record!!", "New Record");
			}

		}
		if (!isMute) {
			gameOver.stop();
			if (isShowing())
				menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
		}
		animateInit();
	}

	// Some methods that initialize a matrix using coordinate written in
	// external files.

	// Some method that print on the panel what is on matrices
	// some of those methods depends from a parameter used for animation



	public static void main(String[] args) {
		int rows = 25 * 2; // >=20
		int cols = 45 * 2; // >=40
		// a causa delle animazione iniziale e finnale
		// nibbles e game over.
		int size = 13; // <= 15

		GameBoard frame = new GameBoard("Nibbles", rows, cols, size);

		frame.setLocation(10, 10);
		frame.start();
	}

}
