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
	private ArrayList<OPObject> enemies = new ArrayList<OPObject>();
	private ArrayList<OPObject> characters = new ArrayList<OPObject>();
	
	private ArrayList<URL> stackEnemiesUrls = new ArrayList<URL>();
	private ArrayList<OPObject> stackCharacters = new ArrayList<OPObject>();
	private ArrayList<String> stackTweets = new ArrayList<String>();
	
	private int timer = 0;
	private int mouseClickCount = 0;
	public boolean isBullet = false;
	private int bulletCount = 0;
		
	public OPGameController() throws TwitterException, IOException{
		this.twitter.login();
	}

	public void setObjectsFirst(OPGameFrame f, OPGraphicPanel p) throws MalformedURLException, IllegalStateException, TwitterException{
		int iconWidth = 50;
		int iconHeight = 50;
		myself = this.myselfFactory.createIcon(f.getWidth() - iconWidth/2, f.getHeight()/2, iconWidth, iconHeight, this.twitter.getCurrentUserIconURL());
		for (URL u : this.twitter.getFriendUrlsList(10)) {
			stackEnemiesUrls.add(u);
		}
		for (String s : this.twitter.getMyTweets(10)) {
			stackTweets.add(s);
		}
	}
	
	public void runloop(OPGameFrame f, OPGraphicPanel p) throws InterruptedException{
		while(true){
			ArrayList<OPObject> paintingObjects = new ArrayList<OPObject>();
			paintingObjects.addAll(characters);
			paintingObjects.addAll(enemies);
			paintingObjects.add(myself);
			p.paint(p.getGraphics(), paintingObjects);
			
			ArrayList<OPObject> movingObjects = new ArrayList<OPObject>();
			movingObjects.addAll(enemies);
			movingObjects.addAll(characters);
			movingObjects.add(myself);
			p.moveObjects(movingObjects);
			
			this.removeOutsideObjects(f, enemies);
			this.removeOutsideObjects(f, characters);
			
			ArrayList<OPObject> collisionEnemies = new ArrayList<OPObject>();
			for (OPObject e : enemies) {
				for (OPObject c : characters) {
					if (this.checkConflict(e, c)) {
						collisionEnemies.add(e);
					}
				}
			}
			enemies.removeAll(collisionEnemies);			
			
			Thread.sleep(30);
			timer ++;
			if (timer % 100 == 0) {
				if (stackEnemiesUrls.size() > 0) {
					int iconWidth = 50;
					int iconHeight = 50;
					URL u = stackEnemiesUrls.get((int)Math.random() % stackEnemiesUrls.size());
					OPEnemy e = (OPEnemy)this.enemyFactory.createIcon(-iconWidth/2, (int)(Math.random() * (f.getHeight() - iconWidth/2*3)) + iconWidth/2, iconWidth, iconHeight, u);
					e.active = true;
					stackEnemiesUrls.remove(u);
					enemies.add(e);
				}
			}
			
			if (timer % 2 == 0 && isBullet) {
				OPObject c = stackCharacters.get(bulletCount);
				characters.add(c);
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
			OPObject ch = this.myselfFactory.createCharacter(f.getWidth() - myself.getWidth(), f.getHeight()/2, (int)(point.getX()-25), (int)(point.getY()-f.getHeight()/2), String.valueOf(c));
			stackCharacters.add(ch);
		}
		mouseClickCount++;
		if (mouseClickCount >= stackTweets.size()) {
			mouseClickCount = 0;
		}
		isBullet = true;
	}
	
	private void removeOutsideObjects(OPGameFrame f, ArrayList<OPObject> objects){
		ArrayList<OPObject> removeObjects = new ArrayList<OPObject>();
		for (OPObject o : objects) {
			if (o.positionX < -o.width/2 				||
				o.positionX > f.getWidth() + o.width 	||
				o.positionY < -o.height/2				||
				o.positionY > f.getHeight() + o.height		) {
				removeObjects.add(o);
			}
		}
		for (OPObject o : removeObjects) {
			objects.remove(o);
		}
	}
	
	private boolean checkConflict(OPObject e, OPObject c) {
		if (Math.abs(e.positionX - c.positionX) < e.width/2 + c.width/2		&&
			Math.abs(e.positionY - c.positionY) < e.height/2 + c.height/2		) {
			System.out.println("collision");
			return true;
		}
		return false;
	}
}
