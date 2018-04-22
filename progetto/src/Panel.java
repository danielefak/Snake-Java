import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A generic Panel
 * 
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */

public interface Panel {

	// Some Generic useful Panel methods to implement

	public int getRows();
	
	public int getColumns();
	
	public void drawRectangle(int x,  int y,  Color color);
	
	public void drawOval(int x,  int y,  Color color);
	
	public void drawImage(int x, int y, BufferedImage image );
	
	public void clear(int x, int y);
	
	public boolean isInside(int x, int y);
	
	public void repaint();
	
	public void setBackground(Color color);
}
