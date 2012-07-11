import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class OPGraphicPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private OPEnemy[] enemy;
	private OPObject myself;
//	private OPCharacter[] character;
	
	public OPGraphicPanel(){
		myself = new OPObject(0, 0);
	}
	
	public void paint(Graphics g){
		clean(g);
		myself.paint(g);
	}

	public void clean(Graphics g){
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void printString(Graphics g, String s){
		g.setColor(new Color(0, 0, 0));
		g.drawString(s, this.getWidth()/2, this.getHeight()/2);
	}
	
	public void printImage(Graphics g, URL u){
		this.clean(g);
		ImageIcon icon = new ImageIcon(u, null);
		icon.paintIcon(null, g, icon.getIconWidth(), icon.getIconHeight());
	}
	
	public void addMyself(OPObject m){
		myself = m;
	}

}
