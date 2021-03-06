package map;

// Start of user code for imports
import java.util.*;
import org.minueto.image.MinuetoImage;
// End of user code

import entity.Entity;
import game.Assets;

/**
 * Tile class definition.
 * Generated by the TouchCORE code generator.
 */
public class Tile {
    
    protected TileType type;
    protected boolean occupied;
    protected MinuetoImage sprite;
    protected Entity myEntity;
    
	public Tile(TileType t){
		this.type = t;
		this.sprite = Assets.getTileTexturesAt(this.type.ordinal());
	}
	
    MinuetoImage getSprite() {
        return this.sprite;
    }

    boolean setSprite(MinuetoImage newObject) {
        this.sprite = newObject;
        return true;
    }

    public Entity getMyEntity() {
        return this.myEntity;
    }

    public boolean setMyEntity(Entity newObject) {
        this.myEntity = newObject;
        return true;
    }

    public void update() {
        if (myEntity != null) {
            boolean checkOnEntity = checkOnEntity(this.myEntity);
            if (checkOnEntity) {
                this.myEntity = null;
            } else {
                myEntity.update();
            }
        }
    }

    private boolean checkOnEntity(Entity e) {
        return false;
    }

	public TileType getType() {
		return type;
	}
    
}
