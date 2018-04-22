import java.awt.Color;
import java.awt.image.BufferedImage;

public class MyPanel implements Panel {

/*I used this Panel as a trick to make 
 *differences between the menu written and the game 
 *(I want the game bigger and the menu written little)
 *
 */
	
		
/**
 * A panel that given an other generic Panel draws (factor x factor) times
 * on this panel
 * (useful for drawing bigger than on the first Panel).
 *     
 * @author Daniele Fakhoury
 * @author NMCGJ, AY 2015-2016
 */
	
	protected int rows;
	protected int cols;
	protected Panel panel;
	protected int fact;   //how much bigger?

	
	/**
	 * Constructs a My Panel from a generic Panel
	 * 
	 * @param panel
	 *            A generic Panel
	 * @param cost
	 *            the increase factor
	 */
	

	public MyPanel(Panel pannello, int cost) {
		panel = pannello;
		fact = cost;
		rows = (panel.getRows() / fact);
		cols = (panel.getColumns() / fact);
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return cols;
	}
	
	
	// draws rectangle, oval, image (factor x factor) times on the panel
	
	public void drawRectangle(int x,  int y,  Color color){
		for (int i =0; i<fact; i++)
			for(int j=0; j<fact; j++){
				panel.drawRectangle( (fact*x) +i,  (fact* y)  +j, color);
			}
					
	}
	
	public void drawOval(int x,  int y,  Color color){
		for (int i =0; i<fact; i++)
			for(int j=0; j<fact; j++){
				panel.drawOval((fact*x) +i,  (fact* y)  +j, color);
			}
	}
	
	public void drawImage(int x, int y, BufferedImage image ){
		for (int i =0; i<fact; i++)
			for(int j=0; j<fact; j++){
				panel.drawImage((fact*x) +i,  (fact* y)  +j, image);
			}
	}
	
	public void clear(int x, int y){
		for (int i =0; i<fact; i++)
			for(int j=0; j<fact; j++){
				panel.clear((fact*x) +i,  (fact* y) +j);
			}
	}
	
	
	public boolean isInside(int x, int y){
		return panel.isInside((fact*x),  (fact* y));
	}
	
	
	public void repaint(){
		panel.repaint();
	}
	
	
	public void setBackground(Color color){
		panel.setBackground(color);
	}
	
	
/*It would be useful create a method that 
 * given (factor x factor) images
 * will print them right to create a big image
*/
	
	
}
