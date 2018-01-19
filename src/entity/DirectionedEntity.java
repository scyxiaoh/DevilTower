package entity;

// Start of user code for imports
import java.util.*;
import org.minueto.window.MinuetoWindow;

import map.*;
//End of user code

/**
 * DirectionedEntity class definition.
 * Generated by the TouchCORE code generator.
 */
public abstract class DirectionedEntity extends Entity {
    
    protected Direction direction;

	protected DirectedAnimation animation;
	
	protected abstract void initiateAnimation();
    
    public DirectionedEntity(String name, Direction d) {
    	super(name);
        this.direction = d;
        initiateAnimation();
    }
    
    public DirectionedEntity(String name, int x, int y, LevelMap m, Direction d) {
    	super(name,x,y,m);
        this.direction = d;
        initiateAnimation();
    }
    
    public DirectionedEntity(String name, int x, int y, LevelMap m, Direction d, int id) {
    	super(name,x,y,m);
        this.direction = d;
        this.id = id;
        initiateAnimation();
    }

    public DirectedAnimation getAnimation() {
        return this.animation;
    }

    boolean setAnimation(DirectedAnimation newObject) {
        this.animation = newObject;
        return true;
    }
    
    public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
		this.animation.setDirection(direction);
	}

    public void update() {
    	// update animation
        if (this.animation != null) {
            animation.update();
        }
        
		// get next position
    	if (moving) getNextPosition();
    	
    	// check stop moving
    	if ((this.positionX == this.destX && this.positionY == this.destY) && moving) moving = false;
    	
    }
    
	public void getNextPosition() {
		
		if(direction == Direction.West && this.positionX > this.destX) this.positionX -= moveSpeed;
		if(direction == Direction.West && this.positionX < this.destX) this.positionX = this.destX;
		
		if(direction == Direction.East && this.positionX < this.destX) this.positionX += moveSpeed;
		if(direction == Direction.East && this.positionX > this.destX) this.positionX = this.destX;
		
		if(direction == Direction.North && this.positionY > this.destY) this.positionY -= moveSpeed;
		if(direction == Direction.North && this.positionY < this.destY) this.positionY = this.destY;
		
		if(direction == Direction.South && this.positionY < this.destY) this.positionY += moveSpeed;
		if(direction == Direction.South && this.positionY > this.destY) this.positionY = this.destY;
		
	}
	
    public void move(Direction d) {
    	if (!moving) {
    		if (d != direction) this.setDirection(d);
    		if (nextPositionAvailable(d)) {
    			moving = true;
    	        if(d == Direction.North) {
    	        	this.setDest(this.getDestX(), this.getDestY()-32);
    	        }
    	        else if (d == Direction.South) {
    	        	this.setDest(this.getDestX(), this.getDestY()+32);
    	        }
    	        else if (d == Direction.East) {
    	        	this.setDest(this.getDestX()+32, this.getDestY());
    	        }
    	        else if (d == Direction.West) {
    	        	this.setDest(this.getDestX()-32, this.getDestY());
    	        }
    		}
    	}
    }
    
    public boolean nextPositionAvailable(Direction d) {
    	int col = this.positionX / 32;
    	int rol = this.positionY / 32;
    	System.out.println(col + " "+ rol);
    	Tile nextTile = null;
        if(d == Direction.North && rol > this.levelMap.getYmin() / 32) {
        	nextTile = this.levelMap.getTile(col, rol-1);
        }
        else if (d == Direction.South && rol < this.levelMap.getYmax() / 32) {
        	nextTile = this.levelMap.getTile(col, rol+1);
        }
        else if (d == Direction.East  && col < this.levelMap.getXmax() / 32) {
        	nextTile = this.levelMap.getTile(col+1, rol);
        }
        else if (d == Direction.West && col > this.levelMap.getXmin() / 32) {
        	nextTile = this.levelMap.getTile(col-1, rol);
        	
        }
        if (nextTile != null && nextTile.getType() == TileType.Ground) return true;
        else return false;
    }
}
