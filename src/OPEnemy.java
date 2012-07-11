import java.net.URL;


public class OPEnemy extends OPIcon{

	public OPEnemy(int x, int y, int w, int h, URL u) {
		super(x, y, w, h, u);
		this.hp = 1;
	}

	public void move() {
		this.velocityX = (int) ((Math.random() * 10000)%3+1);
		this.velocityY = (int) ((Math.random() * 10000)%3+1);
		
		if((int)(Math.random()*10000) % 2 == 0){
			this.velocityX *= -1;
		}
		if((int)(Math.random()*10000) % 2 == 0){
			this.velocityY *= -1;
		}
		
		this.positionX += this.velocityX;
		this.positionY += this.velocityY;
	}

}
