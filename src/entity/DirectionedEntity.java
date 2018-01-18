package entity;

// Start of user code for imports
import java.util.*;
import org.minueto.window.MinuetoWindow;
// End of user code

/**
 * DirectionedEntity class definition.
 * Generated by the TouchCORE code generator.
 */
public class DirectionedEntity extends Entity {
    
    protected Direction direction;

	protected DirectedAnimation animation;
    
    public DirectionedEntity(Direction d, DirectedAnimation a) {
        this.animation = a;
        this.direction = d;
        this.moving = false;
        this.moveSpeed = 4;
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
    		if (nextPositionAvailable()) {
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
    
    public boolean nextPositionAvailable() {
    	//TODO
    	return true;
    }
}
