import java.awt.Color;
import java.awt.image.BufferedImage;

public class LittleBoardPanel implements Panel {

	/**
	 * A Panel that given a BoardPanel draws just to a little part of it.
	 *     
	 * @author Daniele Fakhoury
	 * @author NMCGJ, AY 2015-2016
	 */
	
	
	/*I used this Panel as a trick to draw the game just in 
	 * a little portion of the boardPanel
	 * without correct all the code written before.
	 *
	 *this was useful to no rewrite my code
	 *
	 *this was useful to leave space on bottom of the gameBoard boardPanel
	 *to add the score during game.
	 */
	
	
	protected int rows; //new rows
	protected int cols; //new cols
	protected BoardPanel panel;

	protected int factRows;  //how many rows want to leave out
	protected int factCols;   //how many rows want to leave out

	/**
	 * Constructs a LittlePanel from a BoardPanel
	 * 
	 * @param pannello
	 *            A BoardPanel
	 * @param factX
	 *            the number of less rows
	 * @param factY
	 *            the number of less columns
	 */
	
	
	public LittleBoardPanel(BoardPanel pannello, int factX, int factY) {
		panel = pannello;
		factRows=factX;
		factCols=factY;
		rows = (panel.rows - factRows);
		cols = (panel.cols - factCols);
	}

	
	/*Are the same methods of the generic Panel
	 * but now rows and columns are modified 
	*/
	
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return cols;
	}

	public void drawRectangle(int x, int y, Color color) {
		panel.drawRectangle(x, y, color);
	}


	public void drawOval(int x, int y, Color color) {
		panel.drawOval(x, y, color);
	}


	public void drawImage(int x, int y, BufferedImage image) {
				panel.drawImage( x ,  y  , image);
			}

	public void clear(int x, int y) {
				panel.clear( x,  y);
			}

	public boolean isInside(int x, int y) {
		return panel.isInside( x, y);
	}

	public void repaint() {
		panel.repaint();
	}
	
	public void setBackground( Color color){
		panel.setBackground(color);
	}
	
	
}
