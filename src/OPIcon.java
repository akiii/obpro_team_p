import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;


public class OPIcon extends OPObject{
	
	protected int width, height;
	protected int hp;
	protected URL url;

	public OPIcon(int x, int y, int w, int h, URL u) {
		super(x, y);
		this.width = w;
		this.height = h;
		this.url = u;
	}
	
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon(this.url, null);
		icon.paintIcon(null, g, icon.getIconWidth(), icon.getIconHeight());
	}

}
