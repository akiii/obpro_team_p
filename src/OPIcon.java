import java.awt.Graphics;
import java.awt.Image;
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
		Image icon = new ImageIcon(this.url, null).getImage();
		g.drawImage(icon, this.positionX - this.width/2, this.positionY - this.height/2, this.width, this.height, null);
	}

}
