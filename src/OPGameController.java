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
	
	private ArrayList<OPObject> stackEnemys = new ArrayList<OPObject>();
	private ArrayList<OPObject> stackCharacters = new ArrayList<OPObject>();
	private ArrayList<String> stackTweets = new ArrayList<String>();
	int releaseTimer = 0;
	int mouseClickCount = 0;
	boolean isBullet = false;
	int bulletCount = 0;
	
	public float gameTime = 0;
	
	public OPGameController() throws TwitterException, IOException{
		this.twitter.login();
	}

	public void setObjectsFirst(OPGameFrame f, OPGraphicPanel p) throws MalformedURLException, IllegalStateException, TwitterException{
		int iconWidth = 50;
		int iconHeight = 50;
		p.addObject(this.myselfFactory.createIcon(iconWidth/2, f.getHeight()/2, iconWidth, iconHeight, this.twitter.getCurrentUserIconURL()));
		for (URL u : this.twitter.getFriendUrlsList(10)) {
			OPObject e = this.enemyFactory.createIcon(-iconWidth/2, f.getHeight()/2, iconWidth, iconHeight, u);
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
			Thread.sleep(30);
			gameTime += 0.03;
			releaseTimer ++;
			if (releaseTimer % 100 == 0) {
				if (stackEnemys.size() > 0) {
					OPEnemy e = (OPEnemy)stackEnemys.get((int)Math.random() % stackEnemys.size());
					e.active = true;
					e.positionX = f.getWidth() + e.width/2;
					e.positionY = (int)(Math.random() * (f.getHeight() - e.width/2)) + e.width/2;
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
			stackCharacters.add(this.myselfFactory.createCharacter(25, f.getHeight()/2, (int)(point.getX()-25), (int)(point.getY()-f.getHeight()/2), String.valueOf(c)));	
		}
		mouseClickCount++;
		if (mouseClickCount >= stackTweets.size()) {
			mouseClickCount = 0;
		}
		isBullet = true;
	}
}
