package scene;

// Start of user code for imports
import java.util.*;
import org.minueto.window.MinuetoWindow;
import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
// End of user code

/**
 * ResultScene class definition.
 * Generated by the TouchCORE code generator.
 */
public class ResultScene extends GameScene {
    
    protected int alpha;
    protected int ticks;
    protected boolean win;
    protected ArrayList<MinuetoText> resultDisplay;
    
    public ResultScene(boolean win) {
    	this.resultDisplay = new ArrayList<MinuetoText>();
    	this.win = win;
    	this.ticks = 0;
    	this.alpha = 255;
    }
    
    boolean removeResultDisplay(MinuetoText a) {
        int size = resultDisplay.size();
        if (size == 0) {
            return false;
        }
        boolean removed = resultDisplay.remove(a);
        return removed;
    }

    boolean addResultDisplayAt(int index, MinuetoText a) {
        resultDisplay.add(index, a);
        return true;
    }

    boolean removeResultDisplayAt(int index) {
        int size = resultDisplay.size();
        if (size == 0) {
            return false;
        }
        MinuetoText removedElement = resultDisplay.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoText getResultDisplayAt(int index) {
        MinuetoText associated = resultDisplay.get(index);
        return associated;
    }

    boolean addResultDisplay(MinuetoText a) {
        boolean added = resultDisplay.add(a);
        return added;
    }



    boolean containsResultDisplay(MinuetoText a) {
        boolean contains = resultDisplay.contains(a);
        return contains;
    }

    int sizeOfResultDisplay() {
        int size = resultDisplay.size();
        return size;
    }

    ArrayList<MinuetoText> getResultDisplay() {
        return this.resultDisplay;
    }

    public void update() {
        this.ticks = ticks + 1;
        if (ticks < 120) {
            this.alpha = (int)(255 - 255 * (1.0 * ticks / 120));
            if (alpha < 0) {
                this.alpha = 0;
            }
        }
        if (ticks > 120 + 120) {
            this.alpha = (int) (255 * (1.0 * ticks - 120 - 120) / 120);
            if (alpha > 255) {
                this.alpha = 255;
            }
        }
        if (ticks > 120 + 120 + 120) {
            TitleScene newScene = new TitleScene(true);
            SceneManager sM = SceneManager.getInstance();
            sM.setScene(newScene);
        }
    }

    public void init() {
    	if (win) {
    		this.resultDisplay.add(new MinuetoText("You complete Devil Tower", new MinuetoFont("Arial",55,false, false), MinuetoColor.WHITE, true));
    	}
    	else {
    		this.resultDisplay.add(new MinuetoText("Game Over", new MinuetoFont("Arial",120,false, false), MinuetoColor.WHITE, true));
    	}
    }

    public void draw(MinuetoWindow w) {
    	w.clear(MinuetoColor.BLACK);
        if (win) {
            w.draw(this.getResultDisplayAt(0), 15, 170);
        }
        else {
        	w.draw(this.getResultDisplayAt(0), 20, 170);
        }
    	MinuetoColor transparency = new MinuetoColor(0, 0, 0, this.alpha);
    	MinuetoRectangle transparentMask = new MinuetoRectangle(672, 480, transparency, true);
    	w.draw(transparentMask, 0, 0);
    }

    public void handleInput() {
        return;
    }
}
