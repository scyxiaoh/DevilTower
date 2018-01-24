package game;

import java.net.URL;
// Start of user code for imports
import java.util.*;

import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
// End of user code
import org.minueto.image.MinuetoImageFile;

/**
 * Assets class definition.
 * Generated by the TouchCORE code generator.
 */
public class Assets {
    
    protected static ArrayList<MinuetoImage> tileTextures;
    protected static ArrayList<MinuetoImage> entityTextures;
    protected static ArrayList<MinuetoImage> playerTextures;
    protected static ArrayList<MinuetoImage> opponentTextures;
    
    public Assets() {
    	tileTextures = new ArrayList<MinuetoImage>();
    	entityTextures = new ArrayList<MinuetoImage>();
    	playerTextures = new ArrayList<MinuetoImage>();
    	opponentTextures = new ArrayList<MinuetoImage>();
    	
        MinuetoImage tempImage = loadImageAt("diamond.gif");
        addEntityTextures(tempImage.crop(0, 0, 16, 16));	//[0] diamond
        
        tempImage = loadImageAt("tileTextures.png");
        addTileTextures(tempImage.crop(0, 0, 32, 32)) ;	//[0] ground tile
        addTileTextures(tempImage.crop(5*32, 2*32, 32, 32)); //[1] wall left end
        addTileTextures(tempImage.crop(6*32, 2*32, 32, 32)); //[2] wall central
        addTileTextures(tempImage.crop(7*32, 2*32, 32, 32)); //[3] wall right end
        addTileTextures(tempImage.crop(5*32, 32, 32, 32)); //[4] empty tile
		
		tempImage = loadImageAt("Actor06-Braver02.png");
		for (int i = 0; i < 4 ;i++){
			for (int j = 0; j < 4; j++){
				addPlayerTextures(tempImage.crop(j*32, i*32, 32, 32));//player textures
			}
		}	// [0,3]- font; [4,7]- left; [8,11]- right; [12,15]- back
		
		tempImage = loadImageAt("Item01-01.png");
        addEntityTextures(tempImage.crop(0, 0, 32, 32)); // [1] yellow key
        addEntityTextures(tempImage.crop(32, 0, 32, 32)); // [2] blue key
        addEntityTextures(tempImage.crop(64, 0, 32, 32)); // [3] red key
        
        tempImage = loadImageAt("Event01-Door01.png"); //[4,7] yellow door [8,11] blue door [12,15] red door
        addEntityTextures(tempImage.crop(0, 0, 32, 32));
        addEntityTextures(tempImage.crop(0, 32, 32, 32));
        addEntityTextures(tempImage.crop(0, 64, 32, 32));
        addEntityTextures(tempImage.crop(0, 96, 32, 32));
        addEntityTextures(tempImage.crop(32, 0, 32, 32));
        addEntityTextures(tempImage.crop(32, 32, 32, 32));
        addEntityTextures(tempImage.crop(32, 64, 32, 32));
        addEntityTextures(tempImage.crop(32, 96, 32, 32));
        addEntityTextures(tempImage.crop(64, 0, 32, 32));
        addEntityTextures(tempImage.crop(64, 32, 32, 32));
        addEntityTextures(tempImage.crop(64, 64, 32, 32));
        addEntityTextures(tempImage.crop(64, 96, 32, 32));
        
        tempImage = loadImageAt("stairs.png"); // [16] down stair [17] up stair
        addEntityTextures(tempImage.crop(0, 0, 32, 32));
        addEntityTextures(tempImage.crop(32, 0, 32, 32));
        
        tempImage = loadImageAt("Actor01-Braver17.png"); // [18,21] elf
		for (int i = 0; i < 4; i++){
			addEntityTextures(tempImage.crop(i*32, 0, 32, 32));
		}
		
		tempImage = loadImageAt("Item01-08.png");
		addEntityTextures(tempImage.crop(0, 32, 32, 32)); // [22] diamond sword
		
        
		tempImage = loadImageAt("Actor02-Monster12.png");
		for (int i = 0; i < 4 ;i++){
			for (int j = 0; j < 4; j++){
				addOpponentTextures(tempImage.crop(j*32, i*32, 32, 32));//Slime textures
			}
		}	// [0,3]- font; [4,7]- left; [8,11]- right; [12,15]- back
		
		
        
    }

    public MinuetoImage loadImageAt(String path) {
    	MinuetoImage toReturn = null;
		try{
			URL imageLocation = this.getClass().getClassLoader().getResource("Resources/" + path);
            toReturn = new MinuetoImageFile(imageLocation);
		}  catch (MinuetoFileException mfe) {
			System.err.println("Could not load image file.");
			System.exit(-1);
			return null;
		}
    	return toReturn;
    }
    
    static boolean addTileTexturesAt(int index, MinuetoImage a) {
        boolean contains = tileTextures.contains(a);
        if (contains) {
            return false;
        }
        tileTextures.add(index, a);
        return true;
    }

    static boolean removeTileTexturesAt(int index) {
        MinuetoImage removedElement = tileTextures.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    public static MinuetoImage getTileTexturesAt(int index) {
        MinuetoImage associated = tileTextures.get(index);
        return associated;
    }

    static boolean addTileTextures(MinuetoImage a) {
        boolean contains = tileTextures.contains(a);
        if (contains) {
            return false;
        }
        boolean added = tileTextures.add(a);
        return added;
    }

    static boolean removeTileTextures(MinuetoImage a) {
        boolean removed = tileTextures.remove(a);
        return removed;
    }

    static boolean containsTileTextures(MinuetoImage a) {
        boolean contains = tileTextures.contains(a);
        return contains;
    }

    static int sizeOfTileTextures() {
        int size = tileTextures.size();
        return size;
    }

    public static ArrayList<MinuetoImage> getTileTextures() {
        return tileTextures;
    }

    static boolean addEntityTexturesAt(int index, MinuetoImage a) {
        boolean contains = entityTextures.contains(a);
        if (contains) {
            return false;
        }
        entityTextures.add(index, a);
        return true;
    }

    static boolean removeEntityTexturesAt(int index) {
        MinuetoImage removedElement = entityTextures.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    public static MinuetoImage getEntityTexturesAt(int index) {
        MinuetoImage associated = entityTextures.get(index);
        return associated;
    }

    static boolean addEntityTextures(MinuetoImage a) {
        boolean contains = entityTextures.contains(a);
        if (contains) {
            return false;
        }
        boolean added = entityTextures.add(a);
        return added;
    }

    static boolean removeEntityTextures(MinuetoImage a) {
        boolean removed = entityTextures.remove(a);
        return removed;
    }

    static boolean containsEntityTextures(MinuetoImage a) {
        boolean contains = entityTextures.contains(a);
        return contains;
    }

    static int sizeOfEntityTextures() {
        int size = entityTextures.size();
        return size;
    }

    public static ArrayList<MinuetoImage> getEntityTextures() {
        return entityTextures;
    }

    static boolean addPlayerTexturesAt(int index, MinuetoImage a) {
        boolean contains = playerTextures.contains(a);
        if (contains) {
            return false;
        }
        playerTextures.add(index, a);
        return true;
    }

    static boolean removePlayerTexturesAt(int index) {
        MinuetoImage removedElement = playerTextures.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    public static MinuetoImage getPlayerTexturesAt(int index) {
        MinuetoImage associated = playerTextures.get(index);
        return associated;
    }

    static boolean addPlayerTextures(MinuetoImage a) {
        boolean contains = playerTextures.contains(a);
        if (contains) {
            return false;
        }
        boolean added = playerTextures.add(a);
        return added;
    }

    static boolean removePlayerTextures(MinuetoImage a) {
        boolean removed = playerTextures.remove(a);
        return removed;
    }

    static boolean containsPlayerTextures(MinuetoImage a) {
        boolean contains = playerTextures.contains(a);
        return contains;
    }

    static int sizeOfPlayerTextures() {
        int size = playerTextures.size();
        return size;
    }

    public static ArrayList<MinuetoImage> getPlayerTextures() {
        return playerTextures;
    }
    
    static boolean addOpponentTexturesAt(int index, MinuetoImage a) {
        boolean contains = opponentTextures.contains(a);
        if (contains) {
            return false;
        }
        opponentTextures.add(index, a);
        return true;
    }

    static boolean removeOpponentTexturesAt(int index) {
        MinuetoImage removedElement = opponentTextures.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    public static MinuetoImage getOpponentTexturesAt(int index) {
        MinuetoImage associated = opponentTextures.get(index);
        return associated;
    }

    static boolean addOpponentTextures(MinuetoImage a) {
        boolean contains = opponentTextures.contains(a);
        if (contains) {
            return false;
        }
        boolean added = opponentTextures.add(a);
        return added;
    }

    static boolean removeOpponentTextures(MinuetoImage a) {
        boolean removed = opponentTextures.remove(a);
        return removed;
    }

    static boolean containsOpponentTextures(MinuetoImage a) {
        boolean contains = opponentTextures.contains(a);
        return contains;
    }

    static int sizeOfOpponentTextures() {
        int size = opponentTextures.size();
        return size;
    }

    public static ArrayList<MinuetoImage> getOpponentTextures() {
        return opponentTextures;
    }

}
