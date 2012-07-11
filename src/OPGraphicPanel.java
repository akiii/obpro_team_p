import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class OPGraphicPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private OPEnemy[] enemy;
//	private OPCharacter[] character;
	private ArrayList<OPObject> objects = new ArrayList<OPObject>();
	
	public OPGraphicPanel(){

	}
	
	public void paint(Graphics g){
		clean(g);

		for (OPObject o : objects) {
			o.move();
			o.paint(g);
		}
	}

	public void clean(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
//	public void printString(Graphics g, String s){
//		g.setColor(new Color(0, 0, 0));
//		g.drawString(s, this.getWidth()/2, this.getHeight()/2);
//	}
//	
//	public void printImage(Graphics g, URL u){
//		this.clean(g);
//		ImageIcon icon = new ImageIcon(u, null);
//		icon.paintIcon(null, g, icon.getIconWidth(), icon.getIconHeight());
//	}
	
	public void addObject(OPObject o){
		objects.add(o);
	}

}
