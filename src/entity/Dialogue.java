package entity;

// Start of user code for imports
import java.util.*;
import org.minueto.image.MinuetoText;
import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
// End of user code

/**
 * Dialogue class definition.
 * Generated by the TouchCORE code generator.
 */
public class Dialogue {
    
    protected Entity narrator;
    protected MinuetoFont font;
    protected ArrayList<MinuetoText> content;
    
    public Dialogue(Entity n, String c) {
        this.narrator = n;
        MinuetoFont f = new MinuetoFont("Arial",12,false,false);
        setFont(f);
        int charIndex = 0;
        int lastEndOfIndex = 0;
        int endOfWordNotation = 0;
        this.content = new ArrayList<MinuetoText>();
        MinuetoColor white = MinuetoColor.WHITE;
        String thisLine = " ";
        MinuetoText toBeAdded = null;
        int i = 0;
        while (i < c.length()) {
            if (charIndex>=30) {
                if (i<c.length()-1&&c.charAt(i+1)!=' ') {
                    thisLine = c.substring(lastEndOfIndex,endOfWordNotation);
                    toBeAdded = new MinuetoText(thisLine,f,white,true);
                    boolean addContents = addContent(toBeAdded);
                    i = endOfWordNotation+1;
                } else {
                    thisLine = c.substring(lastEndOfIndex,i+1);
                    toBeAdded = new MinuetoText(thisLine,f,white,true);
                    boolean addContents1 = addContent(toBeAdded);
                }
                charIndex = 0;
                if (i<c.length()-1) {
                    lastEndOfIndex = i+1;
                }
            } else if (charIndex == 0 && c.charAt(i)==' ') {
                lastEndOfIndex = lastEndOfIndex + 1;
            } else {
                charIndex = charIndex+1;
            }
            if (i>=c.length() -1) {
                thisLine = c.substring(lastEndOfIndex, i+1);
                toBeAdded = new MinuetoText(thisLine,f,white,true);
                boolean addContents2 = addContent(toBeAdded);
            }
            if (c.charAt(i)==' ') {
                endOfWordNotation = i-1;
            }
            i = i+1;
        }
    }

    public Entity getNarrator() {
        return this.narrator;
    }

    boolean setNarrator(Entity newObject) {
        this.narrator = newObject;
        return true;
    }

    MinuetoFont getFont() {
        return this.font;
    }

    boolean setFont(MinuetoFont newObject) {
        this.font = newObject;
        return true;
    }

    private boolean addContent(MinuetoText a) {
        int maximum = 16;
        int size = content.size();
        boolean added = true;
        if (size == maximum) {
            return false;
        }
        added = content.add(a);
        return added;
    }

    boolean addContentAt(int index, MinuetoText a) {
        content.add(index, a);
        return true;
    }

    boolean removeContentAt(int index) {
        int size = content.size();
        if (size == 0) {
            return false;
        }
        MinuetoText removedElement = content.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoText getContentAt(int index) {
        MinuetoText associated = content.get(index);
        return associated;
    }

    boolean removeContent(MinuetoText a) {
        int size = content.size();
        if (size == 0) {
            return false;
        }
        boolean removed = content.remove(a);
        return removed;
    }
    
    boolean containsContent(MinuetoText a) {
        boolean contains = content.contains(a);
        return contains;
    }

    int sizeOfContent() {
        int size = content.size();
        return size;
    }

    public ArrayList<MinuetoText> getContent() {
        return this.content;
    }
}
