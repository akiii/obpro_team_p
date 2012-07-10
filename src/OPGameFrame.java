import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import twitter4j.TwitterException;


public class OPGameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	private OPTwitter twitter = OPTwitter.sharedInstace();
	private OPGraphicPanel gPanel = new OPGraphicPanel();
	
	
	public static void main(String[] args) throws TwitterException, IOException {
		// TODO Auto-generated method stub
		new OPGameFrame();
	}
	
	public OPGameFrame() throws TwitterException, IOException{
		this.setPreferredSize(new Dimension(300, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(gPanel);
		this.pack();
		this.setVisible(true);
		
		this.twitter.login();
		this.gPanel.printString(this.gPanel.getGraphics(), "ƒeƒXƒg");
		System.out.println(this.twitter.getCurrentUserIconURL());
	}

}
