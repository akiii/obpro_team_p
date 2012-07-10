import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class OPObject {
	protected int positionX, positionY;
	protected int velocityX, velocityY;
	
	public OPObject(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		Timer timer = new Timer();
		timer.schedule(new MoveTask(), 0, 30);
	}
	
	public void paint(Graphics g) {
	}
	
	public void move() {
	}
	
	private class MoveTask extends TimerTask {
		public void run() {
			move();
		}
	}
}