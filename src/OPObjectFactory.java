import java.net.URL;

public abstract class OPObjectFactory {
	public abstract OPIcon createIcon(int x, int y, int w, int h, URL u);
	public abstract OPCharacter createCharacter(int x, int y, String c);
}
