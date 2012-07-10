
public class OPCharacter extends OPObject{

	protected char character;
	
	public OPCharacter(int x, int y, char c) {
		super(x, y);
		this.character = c;
	}
	
	public void move() {
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
	}
	
}
