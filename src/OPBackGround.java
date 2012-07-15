import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class OPBackGround extends OPObject{
	
	protected int width, height;

	public OPBackGround(int x, int y,int w,int h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}

	public void paint(Graphics g){
		Image icon = new ImageIcon("src/space.jpg", null).getImage();
		g.drawImage(icon, this.positionX - this.width/2, this.positionY - this.height/2, this.width, this.height, null);
	}
}
