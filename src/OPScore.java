import java.awt.Color;
import java.awt.Graphics;


public class OPScore extends OPObject{
	
	protected int score; 

	public OPScore(int x, int y ,int s) {
		super(x, y);
		this.score = s;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		String scoreStr = String.valueOf(this.score);
		g.drawString(scoreStr, this.positionX, this.positionY);
	}

}
