package entity;

import java.util.*;

import org.minueto.MinuetoColor;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoWindow;

import scene.Event;
import scene.GameScene;
import scene.Scene;
import scene.SceneManager;

public class Combat {
	protected int timer;
	protected int pSpeed;
	protected int oSpeed;
	protected Event reward;
	protected Opponent opponent;
	protected Player player;
	
	public Combat(Player p, Opponent o, Event reward){
		this.timer = 0; 
		this.player = p;
		this.opponent = o;
		this.reward = reward;
		this.pSpeed = 60/this.player.getMyWeapon().getAttackSpeed();
		this.oSpeed = 60/this.opponent.getAttackSpeed();
	}
	
	public void update(){
		//wait for 1 second when combat begins
		boolean pSurvive = true, oSurvive = true;
		if (timer > 59) {
			if (timer % oSpeed == 0) {
				pSurvive = this.player.getAttacked(this.opponent.getAttackDamage());
			}
			if (timer % pSpeed == 0) {
				oSurvive = this.opponent.getAttacked(this.player.getDamage());
			}
			if (pSurvive) {
				if (!oSurvive) {
					this.opponentKilled();
				}
			} else {
				this.playerKilled();
			}
		}
		this.timer++;
	}
	
	public void draw(MinuetoWindow w) {
		//panel
		w.draw(new MinuetoRectangle(32*15, 32*8, new MinuetoColor(0f,0f,0f,0.9f),true), 3*32, 3*32);
		//title
        w.draw(new MinuetoText("Combat", new MinuetoFont("Arial",30,false, false), MinuetoColor.WHITE, true), 9*32, 3*32+12);
        //player part
        w.draw(new MinuetoText("You", new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 4*32, 5*32);
        w.draw(new MinuetoText("Health: " + this.player.getHealth(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 4*32, 6*32);
        w.draw(new MinuetoText("AttackDamage: " + this.player.getDamage(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 4*32, 7*32);
        w.draw(new MinuetoText("AttackSpeed: " + this.player.getWeapon().getAttackSpeed(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 4*32, 8*32);
        w.draw(new MinuetoText("Armor: " + this.player.getDefence(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 4*32, 9*32);
        //opponent part
        w.draw(new MinuetoText(this.opponent.getName(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 13*32, 5*32);
        w.draw(new MinuetoText("Health: " + this.opponent.getHealth(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 13*32, 6*32);
        w.draw(new MinuetoText("AttackDamage: " + this.opponent.getAttackDamage(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 13*32, 7*32);
        w.draw(new MinuetoText("AttackSpeed: " + this.opponent.getAttackSpeed(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 13*32, 8*32);
        w.draw(new MinuetoText("Armor: " + this.opponent.getDefence(), new MinuetoFont("Arial",18,false, false), MinuetoColor.WHITE, true), 13*32, 9*32);
        //VS
        w.draw(new MinuetoText("VS", new MinuetoFont("Arial",23,false, false), MinuetoColor.WHITE, true), 10*32, 7*32);
	}
	
	public Opponent getOpponent() {
		return opponent;
	}

	public Player getPlayer() {
		return player;
	}
	
	void opponentKilled() {
		Scene scene = SceneManager.getInstance().getCurrentScene();
		assert scene.getClass() == GameScene.class;
		GameScene gS = (GameScene)scene;
		this.opponent.delete();
		gS.setCurrentCombat(null);
		gS.addEventQueue(this.reward);
	}
	
	void playerKilled() {
		//TODO
		//gameover process
	}

}
