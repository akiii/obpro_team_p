import java.net.URL;


public class OPEnemy extends OPIcon{
	
	public boolean active;

	public OPEnemy(int x, int y, int w, int h, URL u) {
		super(x, y, w, h, u);
		this.hp = 1;
		this.active = false;
		this.velocityX = (int)(1 + Math.random() % 5) * -1;
		this.velocityY = 0;
	}

	public void move() {
		if (this.active) {
			this.positionX += this.velocityX;
		}
	}

}
