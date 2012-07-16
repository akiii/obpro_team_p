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
	private OPObjectFactory scoreFactory = new OPScoreFactory();
	private OPObjectFactory lifeFactory = new OPLifeFactory();
	private OPObjectFactory bgFactory = new OPBackGroundFactory();
	private OPObjectFactory gameOverFactory = new OPGameOverFactory();
	
	private OPObject bg;
	private OPLife gage;
	private OPObject myself;
	private OPScore score;
	private OPGameOver gameOver;

	private ArrayList<OPObject> enemies = new ArrayList<OPObject>();
	private ArrayList<OPObject> characters = new ArrayList<OPObject>();

	private ArrayList<URL> stackEnemiesUrls = new ArrayList<URL>();
	private ArrayList<OPObject> stackCharacters = new ArrayList<OPObject>();
	private ArrayList<String> stackTweets = new ArrayList<String>();
	
	private int timer = 0;
	private int duration = 100;
	private int leftMouseCount = 0;
	private int rightMouseCount = 0;
	public boolean isBullet = false;
	private int bulletCount = 0;
		
	public OPGameController() throws TwitterException, IOException{
		this.twitter.login();
	}

	public void setObjectsFirst(OPGameFrame f, OPGraphicPanel p) throws MalformedURLException, IllegalStateException, TwitterException{
		bg = this.bgFactory.createBackGround(0, 0, 2*f.getWidth(), 2*f.getHeight());
		int gageWidth = 350;
		int gageHeight = 45;
		gage = this.lifeFactory.crateLife(180, 30, gageWidth, gageHeight, 10);
		int iconWidth = 50;
		int iconHeight = 50;
		score = this.scoreFactory.createScore(400, 45, 0);
		gameOver = this.gameOverFactory.createGameOverTittle(f.getWidth()/2, f.getHeight()/2, "GameOver");
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
			paintingObjects.add(bg);
			paintingObjects.add(gage);
			paintingObjects.add(score);
			paintingObjects.addAll(characters);
			paintingObjects.addAll(enemies);
			paintingObjects.add(myself);
			p.paint(p.getGraphics(), paintingObjects);
			
			ArrayList<OPObject> movingObjects = new ArrayList<OPObject>();
			movingObjects.addAll(enemies);
			movingObjects.addAll(characters);
			movingObjects.add(myself);
			p.moveObjects(movingObjects);
			
			this.reduceLife(f, enemies);
			this.removeOutsideObjects(f, enemies);
			this.removeOutsideObjects(f, characters);
			
			ArrayList<OPObject> collisionEnemies = new ArrayList<OPObject>();
			for (OPObject e : enemies) {
				for (OPObject c : characters) {
					if (this.checkConflict(e, c)) {
						score.score += 1;
						collisionEnemies.add(e);
						stackEnemiesUrls.add(((OPEnemy)e).url);
					}
				}
			}
			enemies.removeAll(collisionEnemies);			
			
			Thread.sleep(30);
			timer ++;
			if (duration > 10) {
				duration = 100 - timer / 50;
				System.out.println(duration);
			}
			if (timer % duration == 0) {
				if (stackEnemiesUrls.size() > 0) {
					int iconWidth = 50;
					int iconHeight = 50;
					URL u = stackEnemiesUrls.get((int)Math.random() % stackEnemiesUrls.size());
					OPEnemy e = (OPEnemy)this.enemyFactory.createIcon(-iconWidth/2, (int)(Math.random() * (f.getHeight() - iconWidth/2*3)) + iconWidth/2, iconWidth, iconHeight, u);
					e.active = true;
					e.velocityX +=timer/500;
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
			if(gage.hp == 0){
				paintingObjects.add(gameOver);
				p.paint(p.getGraphics(), paintingObjects);
			}
		}
	}
	
	public void setBullets(OPGameFrame f, OPGraphicPanel p, Point point) {
		for(char c :stackTweets.get(rightMouseCount).toCharArray()){
			OPObject ch = this.myselfFactory.createCharacter(f.getWidth() - myself.getWidth(), f.getHeight()/2, (int)(point.getX()-f.getWidth()+myself.getWidth()), (int)(point.getY()-f.getHeight()/2), String.valueOf(c));
			stackCharacters.add(ch);
		}
		rightMouseCount++;
		if (rightMouseCount >= stackTweets.size()) {
			rightMouseCount = 0;
		}
		isBullet = true;
	}
	
	public void setBullet(OPGameFrame f, OPGraphicPanel p, Point point) {
		OPObject ch = this.myselfFactory.createCharacter(f.getWidth() - myself.getWidth(), f.getHeight()/2, (int)(point.getX()-f.getWidth()+myself.getWidth()), (int)(point.getY()-f.getHeight()/2), String.valueOf(stackTweets.get(rightMouseCount).charAt(leftMouseCount)));
		stackCharacters.add(ch);
		leftMouseCount++;
		if (leftMouseCount >= stackTweets.get(rightMouseCount).length()) {
			leftMouseCount = 0;
			rightMouseCount++;
			if (rightMouseCount >= stackTweets.size()) {
				rightMouseCount = 0;
			}
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
				if (o.getClass().equals(OPEnemy.class)) {
					stackEnemiesUrls.add(((OPEnemy)o).url);
				}
			}
		}
		for (OPObject o : removeObjects) {
			objects.remove(o);
		}
	}
	
	private boolean checkConflict(OPObject e, OPObject c) {
		if (Math.abs(e.positionX - c.positionX) < e.width/2 + c.width/2		&&
			Math.abs(e.positionY - c.positionY) < e.height/2 + c.height/2		) {
			return true;
		}
		return false;
	}
	
	private void reduceLife(OPGameFrame f , ArrayList<OPObject> objects){
		for (OPObject o : objects) {
			if (o.positionX < -o.width/2 				||
				o.positionX > f.getWidth() + o.width 	||
				o.positionY < -o.height/2				||
				o.positionY > f.getHeight() + o.height		) {
					gage.hp -= 1;
			}
		}
	}
}
