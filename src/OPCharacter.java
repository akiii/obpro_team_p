import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class OPCharacter extends OPObject{

	protected String character;
	
	public OPCharacter(int x, int y, int vx, int vy, String c) {
		super(x, y);
		this.character = c;
		this.velocityX = (int) (vx*15 / Math.sqrt((vx*vx+vy*vy))) * -1;
		this.velocityY = (int) (vy*15 / Math.sqrt((vx*vx+vy*vy)));
	}
	
	public void move() {
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
	}
	
	public void paint(Graphics g){
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString(this.character, this.positionX, this.positionY);
	}
}
