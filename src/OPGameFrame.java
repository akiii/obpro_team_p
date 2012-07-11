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
				
		controller.setObjectsFirst(this, gPanel);
		controller.runloop(this, this.gPanel);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouse clicked");
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

}
