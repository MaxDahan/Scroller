import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	protected BlockType BT;
	private LoadedTextures LT = new LoadedTextures();
	private Handler handler; 
	
	public Block(int x, int y, BufferedImage img, ID ID, BlockType BT, LoadedTextures LT, Handler handler) {
		super(x, y, img, ID, LT);
		this.handler = handler;
		this.BT = BT;
		this.LT = LT;
		setUpBounds();
	}
	
	public Block(int x, int y, BufferedImage img, double boundpos, ID ID, BlockType BT, LoadedTextures LT, Handler handler) {
		super(x, y, img, boundpos, ID, LT);
		this.handler = handler;
		this.BT = BT;
		this.LT = LT;
		setUpBounds();
	}
	
	public Block(int x, int y, int width, int height, ID ID, BlockType BT, LoadedTextures LT, Handler handler) {
		super(x, y, width, height, ID, LT);
		this.handler = handler;
		this.BT = BT;
		this.LT = LT;
		setUpBounds();
	}
	
	// sets up the multiple edges on the block, is re-used in the update method
	private void setUpBounds() {
		top.setBounds((int)b.getX() + 15, (int)b.getY(), (int) b.getWidth() - 30, (int)b.getHeight()/2);
		bottom.setBounds((int)b.getX() + 15, (int)b.getY() + (int)b.getHeight()/2, (int)b.getWidth() - 30, (int)b.getHeight()/2);
		right.setBounds((int)b.getX() + (int)b.getWidth() - 1, (int)b.getY() + 10, 1, (int)b.getHeight() - 20);
		left.setBounds((int)b.getX(), (int)b.getY() + 10, 1, (int)b.getHeight() - 20);
	}
	
	public void update() {
		b.setBounds((int)(x + (width * boundpos)), (int)(y + (height * boundpos)),
			        (int)(width - (width * boundpos) * 2), (int)(height - (height * boundpos) * 2));
		setUpBounds();
		if(ID == ID.lava) {
			b.setBounds((int)(x + (width * boundpos)), (int)(y + (height * boundpos) + height * 0.1),
			        (int)(width - (width * boundpos) * 2), (int)(height - (height * boundpos) * 2 - height * 0.1));
		}
	}
	
	public void draw(Graphics2D g) {
		// draws image corresponding to block ID
		if(BT == BlockType.castle)
			g.drawImage(LT.getCastle(), x, y, null);
		else if(BT == BlockType.dirt)
			g.drawImage(LT.getDirt(), x, y, null);
		else if(BT == BlockType.dirtGrass)
			g.drawImage(LT.getDirtGrass(), x, y, null);
		else if(BT == BlockType.empty)
			g.drawImage(LT.getEmpty(), x, y, null);
		else if(BT == BlockType.outsidebg)
				g.drawImage(LT.getOutsideBG(), x, y, null);
		else if(BT == BlockType.castlebg)
			g.drawImage(LT.getCastleBG(), x, y, null);
		else if(BT == BlockType.lavaTop)
			g.drawImage(LT.getLavaTop(), x, y, null);
		else if(BT == BlockType.win)
			g.drawImage(LT.getWin(), x, y, null);
		else if(BT == BlockType.lava)
			g.drawImage(LT.getLava(), x, y, null);
		// draws base model
		/*g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		
		// draws hit box
		g.drawRect((int) b.getX(), (int) b.getY(), (int) b.getWidth(), (int) b.getHeight());
		g.drawRect((int) bottom.getX(), (int) bottom.getY(), (int) bottom.getWidth(), (int) bottom.getHeight());
		g.drawRect((int) right.getX(), (int) right.getY(), (int) right.getWidth(), (int) right.getHeight());
		g.drawRect((int) left.getX(), (int) left.getY(), (int) left.getWidth(), (int) left.getHeight());
		g.drawRect((int) top.getX(), (int) top.getY(), (int) top.getWidth(), (int) top.getHeight());*/
	}
	
	public void loadNext() {
		handler.map++;
		handler.objects.clear();

		handler.up = false;
		handler.down = false;
		handler.right = false;
		handler.left = false;
		
		handler.game.loadDrawRun = true;
		
		BufferedImage img = handler.BIL.loadImage("data/maps/map" + handler.map + ".png");
		handler.game.adjustCamera(img);
		handler.game.loadLevel(img);
	}
}
