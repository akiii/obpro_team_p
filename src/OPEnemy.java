import java.net.URL;


public class OPEnemy extends OPIcon{

	public OPEnemy(int x, int y, int w, int h, URL u) {
		super(x, y, w, h, u);
		this.hp = 1;
	}

	public void move() {
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
	}

}
