import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class OPLife extends OPObject{
	
	protected int width, height;
	protected int hp;
	
	public OPLife(int x, int y, int w, int h, int hp) {
		super(x, y);
		this.positionX = x;
		this.positionY = y;
		this.width = w;
		this.height = h;
		this.hp = hp;
	}

	public void paint(Graphics g){

	}
}
