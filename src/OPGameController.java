import java.awt.Point;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import twitter4j.TwitterException;



public class OPGameController {

	private OPTwitter twitter = OPTwitter.sharedInstace();
	private OPObjectFactory myselfFactory = new OPMyselfFactory();
	private OPObjectFactory enemyFactory = new OPEnemyFactory();
	
	private OPObject myself;
	private ArrayList<OPObject> stackEnemys = new ArrayList<OPObject>();
	private ArrayList<OPObject> stackCharacters = new ArrayList<OPObject>();
	private ArrayList<String> stackTweets = new ArrayList<String>();
	
	private int releaseTimer = 0;
	private int mouseClickCount = 0;
	public boolean isBullet = false;
	private int bulletCount = 0;
	
	public float gameTime = 0;
	
	public OPGameController() throws TwitterException, IOException{
		this.twitter.login();
	}

	public void setObjectsFirst(OPGameFrame f, OPGraphicPanel p) throws MalformedURLException, IllegalStateException, TwitterException{
		int iconWidth = 50;
		int iconHeight = 50;
		myself = this.myselfFactory.createIcon(f.getWidth() - iconWidth/2, f.getHeight()/2, iconWidth, iconHeight, this.twitter.getCurrentUserIconURL());
		p.addObject(myself);
		for (URL u : this.twitter.getFriendUrlsList(10)) {
			OPObject e = this.enemyFactory.createIcon(f.getWidth() + iconWidth/2, f.getHeight()/2, iconWidth, iconHeight, u);
			p.addObject(e);
			stackEnemys.add(e);
		}
		for (String s : this.twitter.getMyTweets(10)) {
			stackTweets.add(s);
		}
	}
	
	public void runloop(OPGameFrame f, OPGraphicPanel p) throws InterruptedException{
		while(true){
			p.repaint();
			p.moveObject();
			p.checkRemoveObject(f);
			p.checkConflict();
			Thread.sleep(30);
			gameTime += 0.03;
			releaseTimer ++;
			if (releaseTimer % 100 == 0) {
				if (stackEnemys.size() > 0) {
					OPEnemy e = (OPEnemy)stackEnemys.get((int)Math.random() % stackEnemys.size());
					e.active = true;
					e.positionX = -e.width/2;
					e.positionY = (int)(Math.random() * (f.getHeight() - e.width/2*3)) + e.width/2;
					stackEnemys.remove(e);
				}
			}
			if (releaseTimer % 2 == 0 && isBullet) {
				p.addObject(stackCharacters.get(bulletCount));
				bulletCount++;
				if (bulletCount >= stackCharacters.size()) {
					isBullet = false;
					bulletCount = 0;
					stackCharacters.clear();
				}
			}
		}
	}
	public void setBullet(OPGameFrame f, OPGraphicPanel p, Point point) {
		for(char c :stackTweets.get(mouseClickCount).toCharArray()){
			stackCharacters.add(this.myselfFactory.createCharacter(f.getWidth() - myself.getWidth(), f.getHeight()/2, (int)(point.getX()-25), (int)(point.getY()-f.getHeight()/2), String.valueOf(c)));	
		}
		mouseClickCount++;
		if (mouseClickCount >= stackTweets.size()) {
			mouseClickCount = 0;
		}
		isBullet = true;
	}
}
