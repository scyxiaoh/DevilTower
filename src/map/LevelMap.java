package map;

// Start of user code for imports
import java.util.*;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoWindow;
import entity.Animation;
import entity.Entity;
import entity.Player;
// End of user code

/**
 * LevelMap class definition.
 * Generated by the TouchCORE code generator.
 */
public class LevelMap {
    
    protected int size;
    protected int level;
	protected int camX;
    protected int camY;
    protected int camDestX;
    protected int camDestY;
    protected int speed;
    protected Tile[][] tiles;
	protected int xmin;
    protected int ymin;
    protected int xmax;
    protected int ymax;
    protected boolean moving;
    protected RandomMapGenerator rmg;
    protected Pathfinding pathfinding;
    
    public LevelMap(int s, int l) {
        this.size = s;
        this.level = l;
        this.tiles = new Tile[size][size];
        this.speed = 4;
        this.xmin = 0;
        this.ymin = 0;
        this.xmax = (size-1) * 32;
        this.ymax = (size-1) * 32;
    }

    public void setTile(int x, int y, Tile t) {
        tiles[x][y] = t;
    }

    public Tile getTile(int x, int y) {
        Tile tile = tiles[x][y];
        return tiles[x][y];
    }
    
    public int getXmin() {
		return xmin;
	}

	public int getYmin() {
		return ymin;
	}

	public int getXmax() {
		return xmax;
	}

	public int getYmax() {
		return ymax;
	}
	
    public void update() {
        if (camX < camDestX) {
            this.camX = camX + speed;
            if (camX > camDestX) {
                this.camX = camDestX;
            }
        }
        if (camX > camDestX) {
            this.camX = camX - speed;
            if (camX < camDestX) {
                this.camX = camDestX;
            }
        }
        if (camY < camDestY) {
            this.camY = camY + speed;
            if (camY > camDestY) {
                this.camY = camDestY;
            }
        }
        if (camY > camDestY) {
            this.camY = camY - speed;
            if (camY < camDestY) {
                this.camY = camDestY;
            }
        }
        
        if (camX != camDestX ||camY != camDestY) {
            this.moving = true;
        } else {
            this.moving = false;
        }
    }

    public void draw(MinuetoWindow w, Player p) {
        int numRowsToDraw = 15;
        int numColsToDraw = 22;
        int colOffset = (int) Math.floor(camX / 32);
        int rowOffset = (int) Math.floor(camY / 32);
        int camAdjustX = camX - colOffset * 32;
        int camAdjustY = camY - rowOffset * 32;
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= size) {
                break;
            }
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= size) {
                    break;
                }
                Tile tile = getTile(col, row);
                MinuetoImage sprite = tile.getSprite();
                int drawX = col * 32 - camX;
                int drawY = row * 32 - camY;
                w.draw(sprite, drawX, drawY);
                //System.out.println("drawn: row " + row + " col " + col + " drawX " + drawX + " drawY " + drawY );
                Entity entity = tile.getMyEntity();
                if (entity != null) {
                    Animation animation = entity.getAnimation();
                    MinuetoImage currentFrame = animation.getCurrentFrame();
                    w.draw(currentFrame, drawX, drawY);
                    entity.update();
                    drawX = entity.getPositionX() - camX;
                    drawY = entity.getPositionY() - camY;
                    entity.draw(w, drawX, drawY);
                }
            }
        }
        int drawX = p.getPositionX() - camX;
        int drawY = p.getPositionY() - camY;
        p.draw(w, drawX, drawY);
    }

    public void fixBounds() {
        if (camDestX < xmin) {
            this.camDestX = xmin;
        }
        if (camDestY < ymin) {
            this.camDestY = ymin;
        }
        if (camDestX > (xmax - 20 * 32)) {
            this.camDestX = xmax - 20 * 32;
        }
        if (camDestY > (ymax - 13 * 32)) {
            this.camDestY = ymax - 13 * 32;
        }
    }

    RandomMapGenerator getRmg() {
        return this.rmg;
    }

    boolean setRmg(RandomMapGenerator newObject) {
        this.rmg = newObject;
        return true;
    }

    Pathfinding getPathfinding() {
        return this.pathfinding;
    }

    boolean setPathfinding(Pathfinding newObject) {
        this.pathfinding = newObject;
        return true;
    }
    
    public void setCam(int x, int y) {
    	this.camX = x;
    	this.camY = y;
    	setCamDest(this.camX, this.camY);
    }
    
    public void setCamDest(int x, int y) {
    	this.camDestX = x;
    	this.camDestY = y;
    	fixBounds();
    }
    
    public int getCamX() {
		return camX;
	}

	public int getCamY() {
		return camY;
	}

	public int getCamDestX() {
		return camDestX;
	}

	public int getCamDestY() {
		return camDestY;
	}
}
