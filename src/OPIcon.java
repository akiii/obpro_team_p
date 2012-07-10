
public class OPIcon extends OPObject{
	
	protected int width, height;
	protected int hp;
	protected String url;

	public OPIcon(int x, int y, int w, int h, String u) {
		super(x, y);
		this.width = w;
		this.height = h;
		this.url = u;
	}

}
