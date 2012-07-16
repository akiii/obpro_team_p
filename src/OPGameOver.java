import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class OPGameOver extends OPObject{
	
	protected String tittle;

	public OPGameOver(int x, int y ,String c) {
		super(x, y);
		this.tittle = c;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString(this.tittle, this.positionX, this.positionY);
	}

}
