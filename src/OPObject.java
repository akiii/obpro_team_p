import java.awt.Graphics;


public class OPObject {
	protected int width, height;
	protected int positionX, positionY;
	protected int velocityX, velocityY;
	
	public OPObject(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	public void paint(Graphics g) {
	}
	
	public void move() {
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
}
