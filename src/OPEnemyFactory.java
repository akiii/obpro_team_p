
import java.net.URL;


public class OPEnemyFactory extends OPObjectFactory{

	@Override
	public OPEnemy createIcon(int x, int y, int w, int h, URL u) {
		return new OPEnemy(x, y, w, h, u);
	}

	@Override
	public OPCharacter createCharacter(int x, int y, int vx, int vy, String c) {
		return null;
	}

	@Override
	public OPBackGround createBackGround(int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OPLife crateLife(int x, int y, int w, int h, int hp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OPScore createScore(int x, int y, int s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OPGameOver createGameOverTittle(int x, int y, String c) {
		// TODO Auto-generated method stub
		return null;
	}

}
