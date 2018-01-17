package scene;

// Start of user code for imports
import java.util.*;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
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
    private MinuetoFont fontUI;
    
    public GameScene() {
        dialogueQueue = new ArrayList<Dialogue>();
        levels = new ArrayList<LevelMap>();
        this.fontUI = new MinuetoFont("Arial", 17, false, false);
    }

    public void update() {
        handleInput();
        currentLevelMap.update();
        player.update();
    }

    public void init() {
        //initiate levels
        constructLevels();
        this.setCurrentLevel(levels.get(0));
      
        //initiate the player object and the camera
        MinuetoImage[][] playerSprites = new MinuetoImage[4][4];
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) {
        		playerSprites[i][j] = Assets.getPlayerTexturesAt(i*4 + j);
        	}
        }
        DirectedAnimation playerAnimation = new DirectedAnimation(Direction.North, playerSprites[3], playerSprites[0], playerSprites[1], playerSprites[2]);
        this.player = new Player(Direction.North, playerAnimation, "Warrior");
        this.player.setPosition(10*32, 13*32);
        levels.get(0).setCam((10-10)*32, (13-7)*32);
    }

    public void draw(MinuetoWindow w) {
    	w.clear(MinuetoColor.BLACK);
        currentLevelMap.draw(w, this.getPlayer());     
        drawUI(w);
    }

    public void handleInput() {
        if (KeyboardHandler.isPressed(Keys.E)) {
        	SceneManager sM = SceneManager.getInstance();
        	sM.setPaused(true);
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

		//levelOne
		levels.add(new LevelMap(21,1));
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                if (i==0) {
                    tempTile = new Tile(TileType.WallLeftEnd);
                } else if (i == 20) {
                    tempTile = new Tile(TileType.WallRightEnd);
                } else if ((j==0 || j==1)&& i!=0 && i!= 20) {
                    tempTile = new Tile(TileType.Wall);
                } else if (j==19 && i!=0 && i!=20 && i!=10) {
                    tempTile = new Tile(TileType.Wall);
                } else if (j==20 && i!=0 && i!=20) {
                    tempTile = new Tile(TileType.Wall);
                } else if (i == 2 && j!=4 && j!=16 && j!=18 && j>1 && j<19) {
                    tempTile = new Tile(TileType.WallRightEnd);
                } else if (i==4 && j!=2 && j!=18 && j>1 && j<19) {
                    tempTile = new Tile(TileType.WallLeftEnd);
                } else if (i==16 && j!=2 && j!=6 && j!=18 && j>1 && j<19) {
                    tempTile = new Tile(TileType.WallLeftEnd);
                } else if (i==17 && j!=2 && j!=6 && j!=18 && j>1 && j<19) {
                    tempTile = new Tile(TileType.Wall);
                } else if (i==18 && (j==4||j==5||j==7)) {
                    tempTile = new Tile(TileType.Wall);
                } else if (i==1 && (j==13 || j==14 || j==15 || j==17)) {
                    tempTile = new Tile(TileType.Wall);
                } else if (j==17 && i!=0 && i!=3 && i!=4 && i!=2 && i<14) {
                    tempTile = new Tile(TileType.Wall);
                } else if (j==3 && i>3 && i!=9 && i!=11 && i!=12 && i!=14 && i!=15 && i<18) {
                    tempTile = new Tile(TileType.Wall);
                } else if ((j==4 || j==5) && ((i>3 && i!=14 && i!=15 && i<18) || (i==19))) {
                    tempTile = new Tile(TileType.Wall);
                } else if ((j == 7) && ((i>3 && i!=10 && i!=14 && i!=15 && i<18) || (i==19))) {
                    tempTile = new Tile(TileType.Wall);
                } else if ((j==8) && (i>3 && i!=10 && i!=11 && i!=14 && i!=15 && i<18)) {
                    tempTile = new Tile(TileType.Wall);
                } else if ((j==9) && (i>3 && i!=14 && i!=15 && i<18)) {
                    tempTile = new Tile(TileType.Wall);
                } else if ((i==14) && (j<19 && j>2 && j!=6)) {
                    tempTile = new Tile(TileType.WallRightEnd);
                } else if ((i==18) && (j<18 && j>7)) {
                    tempTile = new Tile(TileType.WallRightEnd);
                } else if (i == 17 && j == 3){
                	tempTile = new Tile(TileType.WallRightEnd);
                } else {
                    tempTile = new Tile(TileType.Ground);
                }
                levels.get(1).setTile(i, j, tempTile);
            }
        }
    }
    
    void drawUI(MinuetoWindow w){
        MinuetoRectangle newRec = new MinuetoRectangle(672, 32, MinuetoColor.BLACK, true);
        w.draw(newRec, 0, 448);
        String tempString = "HP: ";
        MinuetoText toWrite = new MinuetoText(tempString, this.fontUI, MinuetoColor.WHITE, true);
        w.draw(toWrite, 16, 454);
        tempString = "Attack: ";
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 112, 454);
        tempString = "Armor: " ;
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 208, 454);
        tempString = "Exp: " ;
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 304, 454);
        /** TODO
        toDraw = assets.getEntityTexturesAt(EntityType.YellowKey.ordinal());
        w.draw(toDraw, 400, 448);
        tempString = " : " + countKeysYellow;
        MinuetoColor yellow = MinuetoColor.YELLOW;
        toWrite = new MinuetoText(tempString, this.fontUI, yellow, true);
        w.draw(toWrite, 432, 454);
        toDraw = assets.getEntityTexturesAt(EntityType.BlueKey.ordinal());
        w.draw(toDraw, 464, 448);
        tempString = " : " + countKeysBlue;
        MinuetoColor blue = new MinuetoColor(140, 142, 252);
        toWrite = new MinuetoText(tempString, this.fontUI, blue, true);
        w.draw(toWrite, 496, 454);
        toDraw = assets.getEntityTexturesAt(EntityType.RedKey.ordinal());
        w.draw(toDraw, 528, 448);
        tempString = " : " + countKeysRed;
        MinuetoColor red = MinuetoColor.RED;
        toWrite = new MinuetoText(tempString, this.fontUI, red, true);
        w.draw(toWrite, 560, 454);*/
        tempString = "Level: " + currentLevel;
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 592, 454);
        
    }
}
