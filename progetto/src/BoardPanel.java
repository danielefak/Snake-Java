import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * A JPanel for 2D graphics organized on a board.
 * 
 * @author Hendrik Speleers
 * @author NMCGJ, AY 2015-2016
 */
public class BoardPanel extends JPanel {

   private static final long serialVersionUID = 1L;

   protected BoardComponent[][] board;
   protected int rows;
   protected int cols;

   /**
    * Constructs a board panel.
    * 
    * @param rows
    *           the number of rows of the board
    * @param cols
    *           the number of columns of the board
    */
   public BoardPanel(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
      this.board = new BoardComponent[rows][cols];
   }

   /**
    * Returns the number of rows of the board.
    * 
    * @return number of rows
    */
   public int getRows() {
      return rows;
   }

   /**
    * Returns the number of columns of the board.
    * 
    * @return number of columns
    */
   public int getColumns() {
      return cols;
   }
   
   /**
    * Checks whether the position (i, j) is inside or not.
    * 
    * @param i
    *           the row index
    * @param j
    *           the column index
    * @return true if index is inside, false otherwise
    */
   public boolean isInside(int i, int j) {
      return (i >= 0 && i < rows && j >= 0 && j < cols);
   }

   /**
    * Clears the complete board.
    */
   public void clear() {
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            board[i][j] = null;
         }
      }
   }

   /**
    * Clears the component at board position (i, j).
    * 
    * @param i
    *           the row index
    * @param j
    *           the column index
    */
   public void clear(int i, int j) {
      board[i][j] = null;
   }

   /**
    * Draws a filled rectangle at board position (i, j).
    * 
    * @param i
    *           the row index
    * @param j
    *           the column index
    * @param color
    *           the color
    */
   public void drawRectangle(int i, int j, Color color) {
      board[i][j] = new BoardRectangle(color);
   }

   /**
    * Draws a filled oval at board position (i, j).
    * 
    * @param i
    *           the row index
    * @param j
    *           the column index
    * @param color
    *           the color
    */
   public void drawOval(int i, int j, Color color) {
      board[i][j] = new BoardOval(color);
   }

   /**
    * Draws a (scaled) image at board position (i, j).
    * 
    * @param i
    *           the row index
    * @param j
    *           the column index
    * @param image
    *           the image to be drawn
    */
   public void drawImage(int i, int j, Image image) {
      board[i][j] = new BoardImage(image);
   }

   /**
    * Creates an image from the graphics on the panel.
    * 
    * @return the image
    */
   public BufferedImage createImage() {
      BufferedImage image = new BufferedImage(getWidth(), getHeight(),
            BufferedImage.TYPE_INT_RGB);
      Graphics2D graphics = image.createGraphics();
      print(graphics);
      return image;
   }

   /**
    * Draws all the components on the panel.
    * 
    * @param g
    *           the graphics context
    */
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      int width = getWidth() / cols;
      int height = getHeight() / rows;
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (board[i][j] != null) {
               board[i][j].draw(g, j * width, i * height, width, height);
            }
         }
      }
   }

}

/**
 * An interface for components to be drawn.
 */
interface BoardComponent {

   /**
    * Draws the component.
    * 
    * @param g
    *           the graphics context
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    */
   public void draw(Graphics g, int x, int y, int width, int height);

}

/**
 * A BoardComponent to draw rectangles.
 */
class BoardRectangle implements BoardComponent {
   public Color color;

   /**
    * Constructs a rectangle drawer.
    * 
    * @param color
    *           the color of the rectangle
    */
   BoardRectangle(Color color) {
      this.color = color;
   }

   /**
    * Draws the rectangle.
    * 
    * @param g
    *           the graphics context
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    */
   public void draw(Graphics g, int x, int y, int width, int height) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
   }
}

/**
 * A BoardComponent to draw ovals.
 */
class BoardOval implements BoardComponent {
   public Color color;

   /**
    * Constructs a oval drawer.
    * 
    * @param color
    *           the color of the oval
    */
   BoardOval(Color color) {
      this.color = color;
   }

   /**
    * Draws the oval.
    * 
    * @param g
    *           the graphics context
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    */
   public void draw(Graphics g, int x, int y, int width, int height) {
      g.setColor(color);
      g.fillOval(x, y, width, height);
   }
}

/**
 * A BoardComponent to draw images.
 */
class BoardImage implements BoardComponent {
   public Image image;

   /**
    * Constructs an image drawer.
    * 
    * @param image
    *           the image to be drawn
    */
   BoardImage(Image image) {
      this.image = image;
   }

   /**
    * Draws the image.
    * 
    * @param g
    *           the graphics context
    * @param x
    *           the x-coordinate of the rectangle
    * @param y
    *           the y-coordinate of the rectangle
    * @param width
    *           the width of the rectangle
    * @param height
    *           the height of the rectangle
    */
   public void draw(Graphics g, int x, int y, int width, int height) {
      g.drawImage(image, x, y, width, height, null);
   }
}
