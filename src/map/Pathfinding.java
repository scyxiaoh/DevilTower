package map;

// Start of user code for imports
import java.util.*;
// End of user code

/**
 * Pathfinding class definition.
 * Generated by the TouchCORE code generator.
 */
public class Pathfinding {
    
    protected Tile Destination;
    protected Tile Start;
    
    Tile getDestination() {
        return this.Destination;
    }

    boolean setDestination(Tile newObject) {
        this.Destination = newObject;
        return true;
    }

    Tile getStart() {
        return this.Start;
    }

    boolean setStart(Tile newObject) {
        this.Start = newObject;
        return true;
    }

    public Path findPath(Tile start, Tile destination) {
        /* TODO: No message view defined */
        return null;
    }
}
