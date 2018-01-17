package entity;

// Start of user code for imports
import java.util.*;
import org.minueto.window.MinuetoWindow;
//End of user code

/**
 * Entity class definition.
 * Generated by the TouchCORE code generator.
 */
public abstract class Entity {

	protected int width;
    protected int height;
    protected int positionX;
    protected int positionY;
    protected int destX;
    protected int destY;
    protected boolean moving;
    protected double moveSpeed;
    
    public abstract void update();
    
	public void draw(MinuetoWindow w, int x, int y) {
		w.draw(this.getAnimation().getCurrentFrame(), x, y);
	}

    public abstract Animation getAnimation();
    
    public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPosition(int x, int y) {
		this.positionX = x;
		this.positionY = y;
		this.destX = this.positionX;
		this.destY = this.positionY;
	}

	public int getDestX() {
		return destX;
	}
	
	public int getDestY() {
		return destY;
	}
	
	public void setDest(int x, int y) {
		this.destX = x;
		this.destY = y;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public void playAnimation() {
		this.getAnimation().play();
	}
	
	public void stopAnimation() {
		this.getAnimation().stop();
	}
}
