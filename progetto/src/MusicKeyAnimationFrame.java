import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * MusicKeyAnimationFrame is created just to divide roles. 
 * It take care of what concerning music and menu bar.
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */

abstract public class MusicKeyAnimationFrame extends KeyAnimationBoardFrame {

	private static final long serialVersionUID = 1L;

	protected JMenu menuRecord;
	protected JMenuItem menuReadRecord;
	protected JMenuItem menuClearRecord;

	protected JMenu menuOptions;
	protected JMenuItem menuMultyplayer;
	protected JMenuItem menuBackground;
	protected JMenuItem menuWall;
	protected JMenuItem menuVelocity;
	protected JMenuItem menuFood;
	protected JMenuItem menuGrow;
	protected JMenuItem showOptions;
	protected JMenuItem defaultOptions;
	protected JMenuItem setAllOptions;
	protected JMenuItem loadFile;
	protected JMenuItem start;

	private FileNameExtensionFilter txtFilter;

	
	protected boolean play = false;
	protected boolean casuale = true;
	protected boolean optionSetted = false;


	public File record = new File("Record.txt");
	protected File optionsFile = new File("options.txt");
	
	ArrayList<Coordinate> recordCoord = CoordinateIO.read("Record.txt");
	
	protected int costX=7;
	protected int costY=0;
	

	protected int players = 1;
	protected int grown = 5;
	protected int percentWall = 8;
	protected int HowPlay = 1;
	protected int wallDiff = 2;
	protected int gameDelay = 140;
	protected int background = 1;
	protected int numFood = 1;
	protected int numSpoiledFood=0;
	protected String colorBackground = "Vintage";


	protected LittleBoardPanel littlePanel;
	protected BoardPanel panel2 = getBoardPanel();
	protected MyPanel panel;
	protected int factor = 2;
	protected BufferedImage wallImage;
	protected BufferedImage wallImageAlternative;
	protected BufferedImage foodImage;
	protected BufferedImage snake1Image;
	protected BufferedImage snake2Image;
	protected BufferedImage backgroundImage;

	ArrayList<Coordinate> level = CoordinateIO.read("nibbles.txt");

	
	protected Clip theme = null;
	protected Clip heart = null;
	protected Clip bip = null;
	protected Clip bip2 = null;
	protected Clip gameOver = null;
	protected Clip menuMusic = null;
	protected Clip score = null;
	protected Clip underscore=null;
	
	/**
	 * Constructs a new frame using a super constructor.
	 * Initialize the music
	 * 
	 * @param title
	 *            the frame title
	 * @param rows
	 *            the frame number of rows
	 * @param columns
	 *           the frame number of columns
	 * @param size
	 *            the size of the cell
	 * 
	 */
	
	public MusicKeyAnimationFrame(String title, int rows, int columns, int size) {
		super(title, rows, columns, size);
		littlePanel= new LittleBoardPanel(boardPanel, costX, costY);
		panel = new MyPanel(littlePanel, factor);
		
		
		AudioInputStream audio = null;
		try {
			audio = AudioSystem.getAudioInputStream(new File("tromba.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			heart = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			heart.open(audio);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio0 = null;
		try {
			audio0 = AudioSystem.getAudioInputStream(new File("vivaldi2.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			menuMusic = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			menuMusic.open(audio0);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio1 = null;
		try {
			audio1 = AudioSystem.getAudioInputStream(new File("music2.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			theme = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			theme.open(audio1);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio2 = null;
		try {
			audio2 = AudioSystem.getAudioInputStream(new File("bip2.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			bip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bip.open(audio2);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio3 = null;
		try {
			audio3 = AudioSystem.getAudioInputStream(new File("bip.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			bip2 = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bip2.open(audio3);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio4 = null;
		try {
			audio4 = AudioSystem.getAudioInputStream(new File("score.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			score = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			score.open(audio4);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AudioInputStream audio5 = null;
		try {
			audio5 = AudioSystem.getAudioInputStream(new File("gameOver.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			gameOver = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gameOver.open(audio5);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AudioInputStream audio6 = null;
		try {
			audio6 = AudioSystem.getAudioInputStream(new File("underscore.wav"));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			underscore = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			underscore.open(audio6);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
   
	
	
	@Override
	/**
	 * is a overRight of the method addMenuBar
	 * add new menuitems and cleare some old.
	 * 
	 */
	protected void addMenuBar() {
		ActionListener menuFileListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JMenuItem actionMenu = (JMenuItem) e.getSource();
				if (actionMenu == menuFileNew) {
					newFile();
				} else if (actionMenu == menuFileOpen) {
					openFile();
				} else if (actionMenu == menuFileSave) {
					saveFile();
				} else if (actionMenu == menuFileExit) {
					close();
				} else if (actionMenu == menuReadRecord) {
					readRecord();
				} else if (actionMenu == menuClearRecord) {
					clearRecord();
				} else if (actionMenu == menuWall) {
					askWall();
				} else if (actionMenu == menuGrow) {
					askGrow();
				} else if (actionMenu == menuMultyplayer) {
					askMultyplayer();
				} else if (actionMenu == menuVelocity) {
					askVelocity();
				} else if (actionMenu == menuFood) {
					askNumberFood();
				} else if (actionMenu == menuBackground) {
					askBackground();
				} else if (actionMenu == showOptions) {
					showOptions();
				} else if (actionMenu == defaultOptions) {
					setDefaultOptions();
				} else if (actionMenu == setAllOptions) {
					someQuestions();
				} else if (actionMenu == loadFile) {
					openFile();
				} else if (actionMenu == start) {
					if (play) {
						optionSetted = true;
					}

				}

			}

		};

		fileChooser = new JFileChooser();
		txtFilter = new FileNameExtensionFilter("TXT files (*txt)", "txt");
		fileChooser.addChoosableFileFilter(txtFilter);
		fileChooser.removeChoosableFileFilter(imageFilter);
		fileChooser.removeChoosableFileFilter(jpgFilter);
		fileChooser.removeChoosableFileFilter(pngFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		file = new File("");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuRecord = new JMenu("Record");
		menuBar.add(menuRecord);

		menuReadRecord = new JMenuItem("Read Record");

		menuReadRecord.addActionListener(menuFileListener);
		menuRecord.add(menuReadRecord);

		menuClearRecord = new JMenuItem("Clear Record");
		menuClearRecord.addActionListener(menuFileListener);
		menuRecord.add(menuClearRecord);
		menuRecord.setEnabled(false);

		menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		menuOptions.setEnabled(false);

		menuMultyplayer = new JMenuItem("Multyplayer");
		menuMultyplayer.addActionListener(menuFileListener);
		menuOptions.add(menuMultyplayer);

		menuBackground = new JMenuItem("Background");
		menuBackground.addActionListener(menuFileListener);
		menuOptions.add(menuBackground);

		menuWall = new JMenuItem("Walls");
		menuWall.addActionListener(menuFileListener);
		menuOptions.add(menuWall);

		menuFood = new JMenuItem("Food");
		menuFood.addActionListener(menuFileListener);
		menuOptions.add(menuFood);

		menuVelocity = new JMenuItem("Velocity");
		menuVelocity.addActionListener(menuFileListener);
		menuOptions.add(menuVelocity);

		menuGrow = new JMenuItem("Grow Factor");
		menuGrow.addActionListener(menuFileListener);
		menuOptions.add(menuGrow);

		showOptions = new JMenuItem("Current Options");
		showOptions.addActionListener(menuFileListener);
		menuOptions.add(showOptions);

		defaultOptions = new JMenuItem("Default Options");
		defaultOptions.addActionListener(menuFileListener);
		menuOptions.add(defaultOptions);

		setAllOptions = new JMenuItem("Set All Options");
		setAllOptions.addActionListener(menuFileListener);
		menuOptions.add(setAllOptions);

		loadFile = new JMenuItem("Load Wall Configuration");
		loadFile.addActionListener(menuFileListener);
		menuOptions.add(loadFile);

		start = new JMenuItem("Start!");
		loadFile.setMnemonic(KeyEvent.VK_T);
		start.addActionListener(menuFileListener);
		menuBar.add(start);
		start.setEnabled(false);

	}

	
	//Some Clear methods recall in the game
	
	protected void readRecord() {
		this.showMessageDialog("Record:    " + recordCoord.get(0).getX(), "Record");
	}

	
	public void clearRecord() {
		int sure = this.showInputDialogInt("Press 1 if are you sure to reset your old record " + recordCoord.get(0).getX() + " ", 1);
		if (sure == 1) {

			SetRecord(0);

			this.showMessageDialog("Record is reset:    " + recordCoord.get(0).getX(), "Record");
		}

	}

	public void SetRecord(int newrecord) {
		recordCoord.remove(0);
		recordCoord.add( new Coordinate(newrecord, 0));
		CoordinateIO.write(record, recordCoord);
		CoordinateIO.read("current.txt");	
	}
	
	
	protected void openFile() {
		fileChooser.setDialogTitle("Open File");
		fileChooser.setSelectedFile(file);
		int rVal = fileChooser.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			loadTextFile(file);
		}
	}

	public void loadTextFile(File file) {	
		level = CoordinateIO.read(file.getPath());
		casuale = false;
	}

	public void askNumberFood() {
		numFood = this.showInputDialogInt("How many Food? \n(1  - 10) ", 1);
		if (numFood < 1)
			numFood = 1;
		if (numFood > 10)
			numFood = 10;
		numSpoiledFood=this.showInputDialogInt("How many Spoiled Food? \n(0  - 10) ", 0);
		if (numSpoiledFood < 0)
			numSpoiledFood = 0;
		if (numSpoiledFood > 10)
			numSpoiledFood = 10;
	}

	public void askWall() {

		wallDiff = this.showInputDialogInt("How many wall? \n(1 few - 4 many) ", 2);

		if (wallDiff <= 1)
			percentWall = 2;
		else if (wallDiff == 2)
			percentWall = 5;
		else if (wallDiff == 3)
			percentWall = 8;
		else if (wallDiff >= 4)
			percentWall = 11;
		else
			percentWall = 16;

	}

	public void askBackground() {

		background = this.showInputDialogInt("Chose Background:\n " + "1 for VINTAGE background \n"
				+ "2 for COLORED background \n" + "3 for BLUE background \n" + "4 for GRAY background \n" + "(1 - 4) ",
				1);

		if (background != 1 && background != 2 && background != 3 && background != 4)
			background = 1;

		if (background == 1)
			colorBackground = "Vintage";
		if (background == 2)
			colorBackground = "Colored";
		if (background == 3)
			colorBackground = "Blue";
		if (background == 4){
			colorBackground = "Gray";
			
		}
	}

	public void askMultyplayer() {

		players = this.showInputDialogInt("How many Players? \n(1-2)", 1);

		if (players != 1)
			players = 2;
	}

	public void askVelocity() {
		int fast = this.showInputDialogInt("Choose velocity \n(1 slow - 4 fast)  ", 2);
		if (fast <= 1) {
			setAnimationDelay(200);
			gameDelay = 200;
		} else if (fast == 2) {
			setAnimationDelay(140);
			gameDelay = 140;

		} else if (fast == 3) {
			setAnimationDelay(100);
			gameDelay = 100;

		} else if (fast >= 4) {
			setAnimationDelay(70);
			gameDelay = 70;
		}

		else {
			setAnimationDelay(140);
			gameDelay = 140;
		}
	}

	public void askGrow() {
		grown = this.showInputDialogInt("How much do you want to grow when you eat? \n(1 - 10) ", 5);
		if (grown > 10)
			grown = 10;

		if (grown < 1)
			grown = 5;
	}

	public void showOptions() {
		this.showMessageDialog("Players:  " + players + "\nGrow Factor:  " + grown + "\nVelocity:  " + gameDelay
				+ "\nWall:  " + percentWall + "%" + "\nRandom:  " + casuale + "\nBackGround:  " + colorBackground
				+ "\nNumber of Food:  " + numFood +"\nNumber of Spoiled Food   " + numSpoiledFood

		, "Options");

	}

	public void setDefaultOptions() {
		int sure = this.showInputDialogInt("Press 1 if are you sure to reset  defoult options ", 1);

		if (sure == 1) {
			players = 1;
			grown = 5;
			HowPlay = 1;
			percentWall = 8;
			gameDelay = 140;
			casuale = true;
			background = 1;
			colorBackground = "Vintage";
			numFood = 1;
			numSpoiledFood=0;
		}
	}

	public void someQuestions() {
		askWall();

		askGrow();

		askMultyplayer();

		askVelocity();

		askNumberFood();

		askBackground();

	}

	@Override
	protected void animateInit() {
	}

	@Override
	protected void animateNext() {

	}

	@Override
	protected void animateFinal() {

	}

}
