package scene;

// Start of user code for imports
import java.util.*;
import org.minueto.window.MinuetoWindow;

import woven_tilebasedgameengine.CollectionOfAssociated;

import org.minueto.image.MinuetoText;
// End of user code

/**
 * ResultScene class definition.
 * Generated by the TouchCORE code generator.
 */
public class ResultScene extends GameScene {
    
    protected long tick;
    protected int rank;
    protected CollectionOfAssociated resultDisplay;
    
    boolean removeResultDisplay(MinuetoText a) {
        int size = resultDisplay.size();
        if (size == minimum) {
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
        if (size == minimum) {
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

    ResultScene() {
        CollectionOfAssociated collection = new CollectionOfAssociated();
    }

    boolean containsResultDisplay(MinuetoText a) {
        boolean contains = resultDisplay.contains(a);
        return contains;
    }

    int sizeOfResultDisplay() {
        int size = resultDisplay.size();
        return size;
    }

    CollectionOfAssociated getResultDisplay() {
        return this.resultDisplay;
    }

    public void update() {
        /* TODO: No message view defined */
    }

    public void init() {
        /* TODO: No message view defined */
    }

    public void draw(MinuetoWindow w) {
        /* TODO: No message view defined */
    }

    public void handleInput() {
        /* TODO: No message view defined */
    }
}