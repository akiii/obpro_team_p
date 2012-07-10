import java.awt.Graphics;


public class OPCharacter extends OPObject{

	protected String character;
	
	public OPCharacter(int x, int y, String c) {
		super(x, y);
		this.character = c;
	}
	
	public void move() {
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
	}
	
	public void paint(Graphics g) {
		g.drawString(this.character, this.positionX, this.positionY);
	}
}
