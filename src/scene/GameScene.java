package scene;

// Start of user code for imports
import java.util.*;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoImage;
import org.minueto.window.MinuetoWindow;
import entity.*;
import game.Assets;
import interactive.KeyboardHandler;
import interactive.Keys;
import map.*;
//End of user code

/**
 * GameScene class definition.
 * Generated by the TouchCORE code generator.
 */
public class GameScene extends Scene {
    
    protected int cameraX;
    protected int cameraY;
    protected boolean eventStart;
    protected boolean eventEnd;
    protected int eventTick;
    protected int currentLevel;
    protected boolean blockInput;
    protected Player player;
    protected LevelMap currentLevelMap;
    protected ArrayList<Dialogue> dialogueQueue;
    protected ArrayList<LevelMap> levels;
    
    public GameScene() {
        dialogueQueue = new ArrayList<Dialogue>();
        levels = new ArrayList<LevelMap>();

    }

    public void update() {
        handleInput();
        currentLevelMap.update();
        //TODO
        //player.update();
    }

    public void init() {
        constructLevels();
        this.setCurrentLevel(levels.get(0));
        
        MinuetoImage[][] playerSprites = new MinuetoImage[4][4];
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) {
        		playerSprites[i][j] = Assets.getPlayerTexturesAt(i*4 + j);
        	}
        }
        DirectedAnimation playerAnimation = new DirectedAnimation(Direction.North, playerSprites[3], playerSprites[0], playerSprites[1], playerSprites[2]);
        this.player = new Player(Direction.North, playerAnimation, "Warrior");
        levels.get(0).getTile(13, 10).setMyEntity(this.player);
    }

    public void draw(MinuetoWindow w) {
    	w.clear(MinuetoColor.BLACK);
    	
        currentLevelMap.draw(w);     
        //TODO
        //player.draw(w);
    }

    public void handleInput() {
        if (KeyboardHandler.isPressed(Keys.E)) {
        	//TODO
            //SceneManager sM = SceneManager.getInstance();
            //sM.setPaused(true);
        }
        if (KeyboardHandler.isDown(Keys.UP)) {
            player.move(Direction.North);
        }
        if (KeyboardHandler.isDown(Keys.DOWN)) {
            player.move(Direction.South);
        }
        if (KeyboardHandler.isDown(Keys.LEFT)) {
            player.move(Direction.West);
        }
        if (KeyboardHandler.isDown(Keys.RIGHT)) {
            player.move(Direction.East);
        }
    }

    Player getPlayer() {
        return this.player;
    }

    boolean setPlayer(Player newObject) {
        this.player = newObject;
        return true;
    }

    boolean addDialogueQueueAt(int index, Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        if (contains) {
            return false;
        }
        dialogueQueue.add(index, a);
        return true;
    }

    boolean removeDialogueQueueAt(int index) {
        Dialogue removedElement = dialogueQueue.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    Dialogue getDialogueQueueAt(int index) {
        Dialogue associated = dialogueQueue.get(index);
        return associated;
    }

    boolean addDialogueQueue(Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        if (contains) {
            return false;
        }
        boolean added = dialogueQueue.add(a);
        return added;
    }

    boolean removeDialogueQueue(Dialogue a) {
        boolean removed = dialogueQueue.remove(a);
        return removed;
    }

    boolean containsDialogueQueue(Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        return contains;
    }

    int sizeOfDialogueQueue() {
        int size = dialogueQueue.size();
        return size;
    }

    ArrayList<Dialogue> getDialogueQueue() {
        return this.dialogueQueue;
    }

    boolean addLevelsAt(int index, LevelMap a) {
        boolean contains = levels.contains(a);
        if (contains) {
            return false;
        }
        levels.add(index, a);
        return true;
    }

    boolean removeLevelsAt(int index) {
        LevelMap removedElement = levels.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    LevelMap getLevelsAt(int index) {
        LevelMap associated = levels.get(index);
        return associated;
    }

    boolean addLevels(LevelMap a) {
        boolean contains = levels.contains(a);
        if (contains) {
            return false;
        }
        boolean added = levels.add(a);
        return added;
    }

    boolean removeLevels(LevelMap a) {
        boolean removed = levels.remove(a);
        return removed;
    }


    boolean containsLevels(LevelMap a) {
        boolean contains = levels.contains(a);
        return contains;
    }

    int sizeOfLevels() {
        int size = levels.size();
        return size;
    }

    ArrayList<LevelMap> getLevels() {
        return this.levels;
    }

    LevelMap getCurrentLevel() {
        return this.currentLevelMap;
    }

    boolean setCurrentLevel(LevelMap newObject) {
        this.currentLevelMap = newObject;
        return true;
    }

    public void constructLevels() {
		Tile tempTile;
		Entity tempEntity;
		Animation tempAnimation;
		MinuetoImage[] tempImageArray;
		
		//levelZero
		levels.add(new LevelMap(21,0));
		for (int i = 0; i < 21; i++){
			for (int j = 0; j < 21; j++){
				if (i == 10){
					tempTile = new Tile(TileType.Ground);
				}
				else if (i == 1 && j <= 8){
					tempTile = new Tile(TileType.WallLeftEnd);
				}
				else if (i == 19 && j <= 8){
					tempTile = new Tile(TileType.WallRightEnd);
				}
				else{
					tempTile = new Tile(TileType.Empty);
				}
				
				if ((j == 0||j == 8) && !(i == 1 || i == 19 || i == 0 || i == 20 || i == 10)){
					tempTile = new Tile(TileType.Wall);
				}

				levels.get(0).setTile(j, i, tempTile);
			}
		}
    }
}
