import java.net.URL;

public abstract class OPObjectFactory {
	public abstract OPIcon createIcon(int x, int y, int w, int h, URL u);
	public abstract OPCharacter createCharacter(int x, int y, int vx, int vy, String c);
	public abstract OPBackGround createBackGround(int x, int y,int w,int h);
	public abstract OPLife crateLife(int x, int y, int w, int h, int hp);
	public abstract OPScore createScore(int x, int y,int s);
}
