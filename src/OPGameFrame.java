import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;

import twitter4j.TwitterException;


public class OPGameFrame extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	private OPGameController controller = new OPGameController();
	private OPGraphicPanel gPanel = new OPGraphicPanel();
	
	public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		new OPGameFrame();
	}
	
	public OPGameFrame() throws IllegalStateException, TwitterException, InterruptedException, IOException{
		this.setPreferredSize(new Dimension(800, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(gPanel);
		this.pack();
		this.setVisible(true);
		this.addMouseListener(this);
		this.setResizable(false);
		
		controller.setObjectsFirst(this, gPanel);
		controller.runloop(this, gPanel);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	    if ((e.getModifiers() & java.awt.event.MouseEvent.BUTTON1_MASK) != 0) {
			controller.setBullet(this, gPanel, e.getPoint());
	        System.out.println("left mouse");
	    }
		if ((e.getModifiers() & java.awt.event.MouseEvent.BUTTON3_MASK) != 0) {
			controller.setBullets(this, gPanel, e.getPoint());
			System.out.println("right mouse");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
