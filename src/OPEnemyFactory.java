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

}
