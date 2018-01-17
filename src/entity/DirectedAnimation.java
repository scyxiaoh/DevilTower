package entity;

// Start of user code for imports
import java.util.*;
import org.minueto.image.MinuetoImage;
// End of user code

/**
 * DirectedAnimation class definition.
 * Generated by the TouchCORE code generator.
 */
public class DirectedAnimation extends Animation {
    
    protected int index;
    protected int delay;
    protected int timesPlayed;
    protected long timer;
    protected Direction direction;
    protected long lastTime;
    protected int speed;
    protected ArrayList<MinuetoImage> upFrames;
    protected ArrayList<MinuetoImage> downFrames;
    protected ArrayList<MinuetoImage> leftFrames;
    protected ArrayList<MinuetoImage> rightFrames;
    
    public DirectedAnimation(Direction initialD, MinuetoImage[] u, MinuetoImage[] d, MinuetoImage[] l, MinuetoImage[] r) {
        this.speed = 100;
        this.index = 0;
        this.lastTime = System.currentTimeMillis();
        this.upFrames = new ArrayList<MinuetoImage>();
        this.downFrames = new ArrayList<MinuetoImage>();
        this.leftFrames = new ArrayList<MinuetoImage>();
        this.rightFrames = new ArrayList<MinuetoImage>();
        this.direction = initialD;
        this.playing = false;
        for (int i = 0; i < u.length; i++) {
        	addFrames(Direction.North, u[i]);
        }
        
        for (int i = 0; i < d.length; i++) {
        	addFrames(Direction.South, d[i]);
        }
        
        for (int i = 0; i < l.length; i++) {
        	addFrames(Direction.West, l[i]);
        }
        
        for (int i = 0; i < r.length; i++) {
        	addFrames(Direction.East, r[i]);
        }
    }

    private boolean addFrames(Direction d, MinuetoImage i) {
        boolean added = false;
        if (d == Direction.North) {
            if (upFrames.size() == 16) {
                return false;
            }
            added = upFrames.add(i);
        } else if (d == Direction.South) {
            if (downFrames.size() == 16) {
                return false;
            }
            added = downFrames.add(i);
        } else if (d == Direction.West) {
            if (leftFrames.size() == 16) {
                return false;
            }
            added = leftFrames.add(i);
        } else if (d == Direction.East) {
            if (rightFrames.size() == 16) {
                return false;
            }
            added = rightFrames.add(i);
        }
        return added;
    }

    private int sizeOfFrames(Direction d) {
        int size = 0;
        if (d == Direction.North) {
            size = upFrames.size();
        } else if (d == Direction.South) {
            size = downFrames.size();
        } else if (d == Direction.East) {
            size = rightFrames.size();
        } else if (d == Direction.West) {
            size = leftFrames.size();
        }
        return size;
    }

    public void update() {
    	if (playing) {
            this.timer = timer+System.currentTimeMillis()-lastTime;
            this.lastTime = System.currentTimeMillis();
            if (timer>speed) {
                this.index = index+1;
                this.timer = 0;
                if (index >= sizeOfFrames(this.direction)) {
                    this.index = 0;
                }
            }
    	} 
    }

    public MinuetoImage getCurrentFrame() {
        MinuetoImage frameAt = null;
        if (this.direction == Direction.North) {
            frameAt = upFrames.get(index);
        } else if (this.direction == Direction.South) {
            frameAt = downFrames.get(index);
        } else if (this.direction == Direction.West) {
            frameAt = leftFrames.get(index);
        } else if (this.direction == Direction.East) {
            frameAt = rightFrames.get(index);
        }
        return frameAt;
    }

    public int getIndex() {
        return this.index;
    }

    boolean addUpFramesAt(int index, MinuetoImage a) {
        upFrames.add(index, a);
        return true;
    }

    boolean removeUpFramesAt(int index) {
        int size = upFrames.size();
        if (size == 0) {
            return false;
        }
        MinuetoImage removedElement = upFrames.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoImage getUpFramesAt(int index) {
        MinuetoImage associated = upFrames.get(index);
        return associated;
    }

    boolean removeUpFrames(MinuetoImage a) {
        int size = upFrames.size();
        if (size == 0) {
            return false;
        }
        boolean removed = upFrames.remove(a);
        return removed;
    }

    boolean addUpFrames(MinuetoImage a) {
        boolean added = upFrames.add(a);
        return added;
    }

    boolean containsUpFrames(MinuetoImage a) {
        boolean contains = upFrames.contains(a);
        return contains;
    }

    int sizeOfUpFrames() {
        int size = upFrames.size();
        return size;
    }

    ArrayList<MinuetoImage> getUpFrames() {
        return this.upFrames;
    }

    boolean addDownFramesAt(int index, MinuetoImage a) {
        downFrames.add(index, a);
        return true;
    }

    boolean removeDownFramesAt(int index) {
        int size = downFrames.size();
        if (size == 0) {
            return false;
        }
        MinuetoImage removedElement = downFrames.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoImage getDownFramesAt(int index) {
        MinuetoImage associated = downFrames.get(index);
        return associated;
    }

    boolean removeDownFrames(MinuetoImage a) {
        int size = downFrames.size();
        if (size == 0) {
            return false;
        }
        boolean removed = downFrames.remove(a);
        return removed;
    }

    boolean addDownFrames(MinuetoImage a) {
        boolean added = downFrames.add(a);
        return added;
    }

    boolean containsDownFrames(MinuetoImage a) {
        boolean contains = downFrames.contains(a);
        return contains;
    }

    int sizeOfDownFrames() {
        int size = downFrames.size();
        return size;
    }

    ArrayList<MinuetoImage> getDownFrames() {
        return this.downFrames;
    }

    boolean addLeftFramesAt(int index, MinuetoImage a) {
        leftFrames.add(index, a);
        return true;
    }

    boolean removeLeftFramesAt(int index) {
        int size = leftFrames.size();
        if (size == 0) {
            return false;
        }
        MinuetoImage removedElement = leftFrames.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoImage getLeftFramesAt(int index) {
        MinuetoImage associated = leftFrames.get(index);
        return associated;
    }

    boolean removeLeftFrames(MinuetoImage a) {
        int size = leftFrames.size();
        if (size == 0) {
            return false;
        }
        boolean removed = leftFrames.remove(a);
        return removed;
    }

    boolean addLeftFrames(MinuetoImage a) {
        boolean added = leftFrames.add(a);
        return added;
    }

    boolean containsLeftFrames(MinuetoImage a) {
        boolean contains = leftFrames.contains(a);
        return contains;
    }

    int sizeOfLeftFrames() {
        int size = leftFrames.size();
        return size;
    }

    ArrayList<MinuetoImage> getLeftFrames() {
        return this.leftFrames;
    }

    boolean addRightFramesAt(int index, MinuetoImage a) {
        rightFrames.add(index, a);
        return true;
    }

    boolean removeRightFramesAt(int index) {
        int size = rightFrames.size();
        if (size == 0) {
            return false;
        }
        MinuetoImage removedElement = rightFrames.remove(index);
        boolean result = removedElement != null;
        return result;
    }

    MinuetoImage getRightFramesAt(int index) {
        MinuetoImage associated = rightFrames.get(index);
        return associated;
    }

    boolean removeRightFrames(MinuetoImage a) {
        int size = rightFrames.size();
        if (size == 0) {
            return false;
        }
        boolean removed = rightFrames.remove(a);
        return removed;
    }

    boolean addRightFrames(MinuetoImage a) {
        boolean added = rightFrames.add(a);
        return added;
    }

    boolean containsRightFrames(MinuetoImage a) {
        boolean contains = rightFrames.contains(a);
        return contains;
    }

    int sizeOfRightFrames() {
        int size = rightFrames.size();
        return size;
    }

    ArrayList<MinuetoImage> getRightFrames() {
        return this.rightFrames;
    }
}
