import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class OPGraphicPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void clean(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void printString(Graphics g, String s){
		g.setColor(new Color(0, 0, 0));
		g.drawString(s, this.getWidth()/2, this.getHeight()/2);
	}
	
	
	
	
}
