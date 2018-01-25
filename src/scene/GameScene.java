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
import entity.Character;
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
    protected Combat currentCombat;
    protected ArrayList<Dialogue> dialogueQueue;
    protected ArrayList<Event> eventQueue;
    protected ArrayList<LevelMap> levels;
    protected ArrayList<Information> infoQueue;
    private MinuetoFont fontUI;
    protected int dialogueCounter;
    
    public GameScene() {
        dialogueQueue = new ArrayList<Dialogue>();
        eventQueue = new ArrayList<Event>();
        levels = new ArrayList<LevelMap>();
        infoQueue = new ArrayList<Information>();
        this.fontUI = new MinuetoFont("Arial", 17, false, false);
        this.dialogueCounter = 0;
    }

    public void update() {
        handleInput();
        handleEvents();
        levels.get(currentLevel).update();
        if(this.currentCombat != null) {
        	this.currentCombat.update();
        }
        player.update();
        for (int i = this.infoQueue.size()-1; i >= 0; i--) {
        	this.infoQueue.get(i).update();
        }
    }

    public void init() {
        //initiate levels
        constructLevels();
        this.currentLevel = 0;
      
        //initiate the player object
        this.player = new Player("Warrior", 10*32, 13*32, levels.get(currentLevel), Direction.North);
        
        //initiate the camera
        levels.get(0).setCam((10-10)*32, (13-7)*32);
    }

    public void draw(MinuetoWindow w) {
    	w.clear(MinuetoColor.BLACK);
        levels.get(currentLevel).draw(w, this.getPlayer());     
        displayUI(w);
        if (this.currentCombat != null) {
        	this.currentCombat.draw(w);
        } else {
            displayDialogue(w);
        }
        displayInfomation(w);
    }

    public void handleInput() {
    	//block input 
    	if (this.currentCombat != null) return;
    	
    	//block input except enter
    	if (dialogueQueue != null && dialogueQueue.size() > 0) {
    		if (KeyboardHandler.isPressed(Keys.ENTER)) {
    			this.passDialogue();
    		}
    	}
    	
    	//handle input
    	else {
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
    }

    Player getPlayer() {
        return this.player;
    }

    boolean setPlayer(Player newObject) {
        this.player = newObject;
        return true;
    }

    public boolean addDialogueQueueAt(int index, Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        if (contains) {
            return false;
        }
        dialogueQueue.add(index, a);
        return true;
    }

    public boolean removeDialogueQueueAt(int index) {
        Dialogue removedElement = dialogueQueue.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    public Dialogue getDialogueQueueAt(int index) {
        Dialogue associated = dialogueQueue.get(index);
        return associated;
    }

    public boolean addDialogueQueue(Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        if (contains) {
            return false;
        }
        boolean added = dialogueQueue.add(a);
        return added;
    }

    public boolean removeDialogueQueue(Dialogue a) {
        boolean removed = dialogueQueue.remove(a);
        return removed;
    }

    public boolean containsDialogueQueue(Dialogue a) {
        boolean contains = dialogueQueue.contains(a);
        return contains;
    }

    public int sizeOfDialogueQueue() {
        int size = dialogueQueue.size();
        return size;
    }

    public ArrayList<Dialogue> getDialogueQueue() {
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
    
    boolean removeEventQueueAt(int index) {
        Event removedElement = eventQueue.remove(index);
        boolean result = removedElement != null;
        return result;
    }
    
    Event getEventQueueAt(int index) {
        Event associated = eventQueue.get(index);
        return associated;
    }
    
    public boolean addEventQueue(Event a) {
        boolean contains = eventQueue.contains(a);
        if (contains) {
            return false;
        }
        boolean added = eventQueue.add(a);
        return added;
    }
    
    public boolean addInfoQueueAt(int index, Information a) {
        boolean contains = infoQueue.contains(a);
        if (contains) {
            return false;
        }
        infoQueue.add(index, a);
        return true;
    }
    
    public boolean removeInfoQueue(Information a) {
        boolean removed = infoQueue.remove(a);
        return removed;
    }

    public LevelMap getCurrentMap() {
    	return this.levels.get(this.currentLevel);
    }
    
    public void constructLevels() {
		Tile tempTile;
		
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
				levels.get(0).setTile(i, j, tempTile);
			}
		}
		levels.get(0).getTile(10, 10).setMyEntity(new Character("elf", 10*32, 10*32, levels.get(0), 0));
		levels.get(0).getTile(10, 0).setMyEntity(new KeyElement("UpStairs", 10*32, 0, levels.get(0), 0));
		levels.get(0).getTile(10, 8).setMyEntity(new KeyElement("YellowDoor", 10*32, 8*32, levels.get(0),4));
		levels.get(0).getTile(10, 9).setMyEntity(new KeyElement("YellowKey", 10*32, 9*32, levels.get(0),1));

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
        
        levels.get(1).getTile(10,19).setMyEntity(new KeyElement("DownStairs", 10*32, 19*32, levels.get(1), 7));
        levels.get(1).getTile(13,18).setMyEntity(new KeyElement("YellowKey", 13*32, 18*32, levels.get(1), 1));
        levels.get(1).getTile(1,2).setMyEntity(new KeyElement("YellowKey", 1*32, 2*32, levels.get(1), 1));
        levels.get(1).getTile(1,3).setMyEntity(new KeyElement("YellowKey", 1*32, 3*32, levels.get(1), 1));
        levels.get(1).getTile(3,7).setMyEntity(new KeyElement("YellowKey", 3*32, 7*32, levels.get(1), 1));
        levels.get(1).getTile(5,6).setMyEntity(new KeyElement("YellowKey", 5*32, 6*32, levels.get(1), 1));
        levels.get(1).getTile(1,16).setMyEntity(new KeyElement("YellowKey", 1*32, 16*32, levels.get(1), 1));
        levels.get(1).getTile(19,2).setMyEntity(new KeyElement("YellowKey", 19*32, 2*32, levels.get(1), 1));
        levels.get(1).getTile(1,9).setMyEntity(new KeyElement("BlueKey", 1*32, 9*32, levels.get(1), 2));
        levels.get(1).getTile(3,11).setMyEntity(new KeyElement("BlueKey", 3*32, 11*32, levels.get(1), 2));
        levels.get(1).getTile(11,8).setMyEntity(new KeyElement("BlueKey", 11*32, 8*32, levels.get(1), 2));
        levels.get(1).getTile(1,18).setMyEntity(new KeyElement("RedKey", 1*32, 18*32, levels.get(1), 3));
        levels.get(1).getTile(19,3).setMyEntity(new KeyElement("RedKey", 19*32, 3*32, levels.get(1), 3));
        levels.get(1).getTile(1,7).setMyEntity(new KeyElement("YellowDoor", 1*32, 7*32, levels.get(1), 4));
        levels.get(1).getTile(1,8).setMyEntity(new KeyElement("YellowDoor", 1*32, 8*32, levels.get(1), 4));
        levels.get(1).getTile(2,4).setMyEntity(new KeyElement("YellowDoor", 2*32, 4*32, levels.get(1), 4));
        levels.get(1).getTile(3,8).setMyEntity(new KeyElement("YellowDoor", 3*32, 8*32, levels.get(1), 4));
        levels.get(1).getTile(3,13).setMyEntity(new KeyElement("YellowDoor", 3*32, 13*32, levels.get(1), 4));
        levels.get(1).getTile(10,7).setMyEntity(new KeyElement("YellowDoor", 10*32, 7*32, levels.get(1), 4));
        levels.get(1).getTile(15,7).setMyEntity(new KeyElement("YellowDoor", 15*32, 7*32, levels.get(1), 4));
        levels.get(1).getTile(2,16).setMyEntity(new KeyElement("BlueDoor", 2*32, 16*32, levels.get(1), 5));
        levels.get(1).getTile(2,18).setMyEntity(new KeyElement("BlueDoor", 2*32, 18*32, levels.get(1), 5));
        levels.get(1).getTile(17,2).setMyEntity(new KeyElement("BlueDoor", 17*32, 2*32, levels.get(1), 5));
        levels.get(1).getTile(4,2).setMyEntity(new KeyElement("RedDoor", 4*32, 2*32, levels.get(1), 6));
        levels.get(1).getTile(19,11).setMyEntity(new KeyElement("RedDoor", 19*32, 11*32, levels.get(1), 6));
        levels.get(1).getTile(9, 3).setMyEntity(new Opponent("Slime", 9*32, 3*32, levels.get(1), Direction.South, 0, 15, 0, 200, 1));
    }
    
    void displayUI(MinuetoWindow w){
    	MinuetoImage toDraw = player.getWeapon().getIcon();
    	w.draw(toDraw, 10, 10);
        MinuetoRectangle newRec = new MinuetoRectangle(672, 32, MinuetoColor.BLACK, true);
        w.draw(newRec, 0, 448);
        String tempString = "HP: " + player.getHealth();
        MinuetoText toWrite = new MinuetoText(tempString, this.fontUI, MinuetoColor.WHITE, true);
        w.draw(toWrite, 16, 454);
        tempString = "Attack: " + player.getDamage();
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 112, 454);
        tempString = "Armor: " + player.getDefence();
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 208, 454);
        tempString = "Exp: " + player.getExp();
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 304, 454);
        toDraw = Assets.getEntityTexturesAt(1);
        w.draw(toDraw, 380, 448);
        tempString = " : " + player.getKeyNum(0);
        MinuetoColor yellow = MinuetoColor.YELLOW;
        toWrite = new MinuetoText(tempString, this.fontUI, yellow, true);
        w.draw(toWrite, 412, 454);
        toDraw = Assets.getEntityTexturesAt(2);
        w.draw(toDraw, 444, 448);
        tempString = " : " + player.getKeyNum(1);
        MinuetoColor blue = new MinuetoColor(140, 142, 252);
        toWrite = new MinuetoText(tempString, this.fontUI, blue, true);
        w.draw(toWrite, 476, 454);
        toDraw = Assets.getEntityTexturesAt(3);
        w.draw(toDraw, 508, 448);
        tempString = " : " + player.getKeyNum(2);
        MinuetoColor red = MinuetoColor.RED;
        toWrite = new MinuetoText(tempString, this.fontUI, red, true);
        w.draw(toWrite, 540, 454);
        tempString = "Level: " + currentLevel;
        toWrite = new MinuetoText(tempString, this.fontUI,  MinuetoColor.WHITE, true);
        w.draw(toWrite, 592, 454);
        
    }
    
    void displayDialogue(MinuetoWindow w) {
    	if (this.dialogueQueue != null && dialogueQueue.size() > 0) {
    		Dialogue currentDialogue = this.dialogueQueue.get(0);
			if (currentDialogue.getNarrator() != null){
				w.draw(new MinuetoRectangle(32*13, 32*3, new MinuetoColor(1f,1f,1f,0.5f),true), 4*32, 10*32);
				ArrayList<MinuetoText> content = currentDialogue.getContent();
				for (int k = 0; k < content.size(); k++){
					w.draw(content.get(k),  6*32, 10*32+15*k+12);
				}
			}
			if (dialogueCounter >= 0 && dialogueCounter < 60) {
				w.draw(new MinuetoText("Press Enter To Continue",new MinuetoFont("Arial",14,false, true),MinuetoColor.WHITE),260,420);
			}
			else if (dialogueCounter == 119) {
				this.dialogueCounter = -1;
			}
			this.dialogueCounter++;
    	}
    }
    
    void displayInfomation(MinuetoWindow w) {
		for (int i = 0; i < infoQueue.size(); i++){
            MinuetoColor transparency = new MinuetoColor(1f, 1f, 1f, 1.0f - 0.1f * i);
			w.draw(new MinuetoText(infoQueue.get(i).getContent(), fontUI, transparency),4,32*13-i*16);
		}
    }
    
    boolean passDialogue() {
    	return removeDialogueQueueAt(0);
    }
    
    void handleEvents() {
    	if (this.dialogueQueue != null && dialogueQueue.size() > 0) return;
    	while(this.eventQueue.size() > 0) {
    		this.getEventQueueAt(0).invoke();
    		this.removeEventQueueAt(0);
    	}
    }
    
    public boolean setCurrentLevel(int newLevel){
    	if (newLevel >= 0 && newLevel < this.levels.size()) {
    		this.currentLevel = newLevel;
    		this.player.setLevelMap(this.getCurrentMap());
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void setCurrentCombat(Combat c) {
    	this.currentCombat = c;
    }
    
    public void pushInformation(Information i) {
    	this.addInfoQueueAt(0, i);
    }
}
