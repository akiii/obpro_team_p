import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class OPCharacter extends OPObject{

	protected String character;
	
	public OPCharacter(int x, int y, int vx, int vy, String c) {
		super(x, y);
		this.width = 25;
		this.height = 25;
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
		//debug
		g.drawRect(positionX-width/2, positionY-height/2, width, height);
		g.drawString(this.character, this.positionX - this.width/2, this.positionY + this.height/2);
	}
	
	public OPObject getCharacter () {
		return this;
	}

}
