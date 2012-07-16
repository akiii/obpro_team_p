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
		Image icon = new ImageIcon("src/notification_text_back.png", null).getImage();
		Image gradient = new ImageIcon("src/gradient.jpg", null).getImage();
		g.drawImage(icon, this.positionX - this.width/2, this.positionY - this.height/2, this.width, this.height, null);
		double gage = this.hp / this.maxHp; 
		int gradientWidth = (int) (gage *0.97* this.width);
		int gradientHeight = (int) (0.7 * this.height);
		g.drawImage(gradient, this.positionX - this.width/2+5, this.positionY - this.height/2+5, gradientWidth, gradientHeight, null);
	}
}
