import java.net.URL;


public class OPMyselfFactory extends OPObjectFactory{

	@Override
	public OPMyself createIcon(int x, int y, int w, int h, URL u) {
		return new OPMyself(x, y, w, h, u);
	}

	@Override
	public OPCharacter createCharacter(int x, int y, int vx, int vy, String c) {
		return new OPCharacter(x, y, vx, vy, c);
	}

}
