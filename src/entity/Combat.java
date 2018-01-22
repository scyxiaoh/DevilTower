package entity;

import java.util.*;

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
				pSurvive = this.opponent.getAttacked(this.opponent.getAttackDamage());
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
