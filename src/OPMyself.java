import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;


public class OPMyself extends OPIcon implements KeyListener,MouseListener{

	public OPMyself(int x, int y, int w, int h, URL u) {
		super(x, y, w, h, u);
		this.hp = 5;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		new OPCharacter(10,10,"Test");
		System.out.print("click");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_RIGHT) { 
			this.positionX += 5;
		} else if (k == KeyEvent.VK_LEFT) {
			this.positionX -= 5; 
		}else if (k == KeyEvent.VK_DOWN) {
			this.positionY += 5; 
		} else if (k == KeyEvent.VK_UP) {
			this.positionY -= 5;  
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
