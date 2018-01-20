package game;

// Start of user code for imports
import java.util.*;

import org.minueto.window.MinuetoFrame;
import org.minueto.window.MinuetoWindow;

import interactive.KeyboardHandler;
import scene.SceneManager;

import org.minueto.MinuetoEventQueue;
// End of user code
import org.minueto.MinuetoOptions;

/**
 * Game class definition.
 * Generated by the TouchCORE code generator.
 */
public class Game {
    
    protected int screenWidth;
    protected int screenHeight;
    protected boolean inGame;
    protected int FPS;
    protected double nsPerFrame;
    protected long lastTime;
    protected long timer;
    protected long nextFrameTime;
    protected Assets myAssets;
    protected MinuetoWindow window;
    protected MinuetoEventQueue eventQueue;
    
    public Game() {
        this.eventQueue = new MinuetoEventQueue();
        this.screenWidth = 672;
        this.screenHeight = 480;
        this.window = new MinuetoFrame(this.screenWidth, this.screenHeight, true);
        this.myAssets = new Assets();
        window.setVisible(true);
        window.setTitle("Devil Tower");
        registerHandler();
        MinuetoOptions.enableAlpha(true);
        this.inGame = false;
        this.FPS = 0;
        this.nsPerFrame = 1000000000/60;
        this.lastTime = System.nanoTime();
        this.timer = 0;
        this.nextFrameTime = System.nanoTime();
    }

    public void startRenderLoop() {
        while(true) {
        	doDraw();
        	window.render();
        }
    }

    public void doDraw() {
        long now = System.nanoTime();
        this.timer = timer + now - lastTime;
        this.lastTime = now;
        if (now < nextFrameTime) {
            long sleepTime = (long)((nextFrameTime - System.nanoTime())/1000000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                
            }
        }
        this.nextFrameTime = (long)(nextFrameTime + nsPerFrame);
        while (eventQueue.hasNext()) {
            eventQueue.handle();
        }
        SceneManager sM = SceneManager.getInstance();
        sM.update();
        sM.draw(this.window);
        KeyboardHandler.update();
        this.FPS = FPS + 1;
        if (timer >= 1000000000) {
            System.out.println("FPS: " + FPS);
            this.FPS = 0;
            this.timer = timer - 1000000000;
        }
    }

    Assets getMyAssets() {
        return this.myAssets;
    }

    boolean setMyAssets(Assets newObject) {
        this.myAssets = newObject;
        return true;
    }

    public void registerHandler() {
        this.window.registerKeyboardHandler(new KeyboardHandler(), this.eventQueue);
    }
}
