import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;

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
	private OPTwitter twitter = OPTwitter.sharedInstace();
	private OPGraphicPanel gPanel = new OPGraphicPanel();
	
	private OPObjectFactory factory;
	
	private float gameTime = 0;
	
	
	public static void main(String[] args) throws TwitterException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		new OPGameFrame();
	}
	
	public OPGameFrame() throws TwitterException, IOException, InterruptedException{
		this.setPreferredSize(new Dimension(300, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(gPanel);
		this.pack();
		this.setVisible(true);
		this.addMouseListener(this);
		
		this.twitter.login();
//		this.gPanel.printImage(gPanel.getGraphics(), this.twitter.getCurrentUserIconURL());
//		this.twitter.getFriendsList(100);
		
		this.factory = new OPMyselfFactory();
		
		setStartingGraphic();
		while(true){
			gPanel.repaint();
			Thread.sleep(30);
			gameTime += 0.03;
		}
	}
	
	public void setStartingGraphic() throws MalformedURLException, IllegalStateException, TwitterException{
		OPMyself myself = null;
		myself = (OPMyself) this.factory.createIcon(100, 100, 100, 100, this.twitter.getCurrentUserIconURL());
		this.gPanel.addMyself(myself);
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
