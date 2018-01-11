package interactive;

// Start of user code for imports
import java.util.*;
// End of user code

import org.minueto.handlers.*;

/**
 * KeyboardHandler class definition.
 * Generated by the TouchCORE code generator.
 */
public class KeyboardHandler implements MinuetoKeyboardHandler{
    
    protected static boolean[] keyState;
    protected static boolean[] prevKeyState;
    protected static int numKeys;
    
    public KeyboardHandler() {
        KeyboardHandler.numKeys = 8;
        KeyboardHandler.keyState = new boolean[numKeys];
        KeyboardHandler.prevKeyState = new boolean[numKeys];
    }

    public void handleKeyPress(int value) {
        if (value == MinuetoKeyboard.KEY_UP || value == MinuetoKeyboard.KEY_W) {
            KeyboardHandler.setKey(Keys.UP, true);
        } else if (value == MinuetoKeyboard.KEY_DOWN || value == MinuetoKeyboard.KEY_S) {
            KeyboardHandler.setKey(Keys.DOWN, true);
        } else if (value == MinuetoKeyboard.KEY_LEFT || value == MinuetoKeyboard.KEY_A) {
            KeyboardHandler.setKey(Keys.LEFT, true);
        } else if (value == MinuetoKeyboard.KEY_RIGHT || value == MinuetoKeyboard.KEY_D) {
            KeyboardHandler.setKey(Keys.RIGHT, true);
        } else if (value == MinuetoKeyboard.KEY_ENTER) {
            KeyboardHandler.setKey(Keys.ENTER, true);
        } else if (value == MinuetoKeyboard.KEY_SPACE) {
            KeyboardHandler.setKey(Keys.SPACE, true);
        } else if (value == MinuetoKeyboard.KEY_Q) {
            KeyboardHandler.setKey(Keys.Q, true);
        } else if (value == MinuetoKeyboard.KEY_E) {
            KeyboardHandler.setKey(Keys.E, true);
        }
    }

    public void handleKeyRelease(int value) {
        if (value == MinuetoKeyboard.KEY_ESC) {
            System.exit(-1);
        } else if (value == MinuetoKeyboard.KEY_UP || value == MinuetoKeyboard.KEY_W) {
            KeyboardHandler.setKey(Keys.UP, false);
        } else if (value == MinuetoKeyboard.KEY_DOWN || value == MinuetoKeyboard.KEY_S) {
            KeyboardHandler.setKey(Keys.DOWN, false);
        } else if (value == MinuetoKeyboard.KEY_LEFT || value == MinuetoKeyboard.KEY_A) {
            KeyboardHandler.setKey(Keys.LEFT, false);
        } else if (value == MinuetoKeyboard.KEY_RIGHT || value == MinuetoKeyboard.KEY_D) {
            KeyboardHandler.setKey(Keys.RIGHT, false);
        } else if (value == MinuetoKeyboard.KEY_ENTER) {
            KeyboardHandler.setKey(Keys.ENTER, false);
        } else if (value == MinuetoKeyboard.KEY_SPACE) {
            KeyboardHandler.setKey(Keys.SPACE, false);
        } else if (value == MinuetoKeyboard.KEY_Q) {
            KeyboardHandler.setKey(Keys.Q, false);
        } else if (value == MinuetoKeyboard.KEY_E) {
            KeyboardHandler.setKey(Keys.E, false);
        }
    }

    public void handleKeyType(char arg0) {
        /* TODO: No message view defined */
    }

    public static void update() {
        for (int i = 0; i < numKeys; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    public static boolean isPressed(Keys k) {
        return keyState[k.ordinal()] && !prevKeyState[k.ordinal()];
    }

    public static boolean isDown(Keys k) {
        return keyState[k.ordinal()];
    }

    public static boolean anyKeyDown() {
        for (int i = 0; i < numKeys; i++) {
            if (keyState[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyKeyPress() {
        for (int i = 0; i < numKeys; i++) {
            if (keyState[i] && !prevKeyState[i]) {
                return true;
            }
        }
        return false;
    }

    public static void setKey(Keys k, boolean b) {
        keyState[k.ordinal()] = b;
    }
}
