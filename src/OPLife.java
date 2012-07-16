import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class OPLife extends OPObject{
	
	protected int width, height;
	protected int hp;
	private final int maxHp;
	
	public OPLife(int x, int y, int w, int h, int hp) {
		super(x, y);
		this.width = w;
		this.height = h;
		this.maxHp = hp;
		this.hp = hp;
	}

	public void paint(Graphics g){
		Image icon = new ImageIcon("src/gift_button_red_back.png", null).getImage();
		double gage = this.hp / this.maxHp; 
		int gageWidth = (int) (gage * this.width);
		g.drawImage(icon, this.positionX - this.width/2, this.positionY - this.height/2, gageWidth, this.height, null);
	}
}
