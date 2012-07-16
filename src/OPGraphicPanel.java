import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class OPGraphicPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OPGraphicPanel(){

	}
	
	public void paint(Graphics g, ArrayList<OPObject> objects){
		clean(g);
		for (OPObject o : objects) {
			o.paint(g);
		}
	}
	
	public void moveObjects(ArrayList<OPObject> objects){
		for (OPObject o : objects) {
			o.move();
		}
	}

	public void clean(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
