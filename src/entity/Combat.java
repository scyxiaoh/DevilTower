package entity;

import java.util.*;

public class Combat {
	protected boolean finished;
	protected boolean ifPlayerWin;
	protected long pTimer;
	protected long oTimer;
	protected long lastTime;
	protected Opponent opponent;
	protected Player player;
	
	public Combat(Opponent o, Player p){
		this.opponent = o;
		this.player = p;
		this.finished = false;
		this.lastTime = System.currentTimeMillis();
		this.ifPlayerWin = false;
	}
	
	public void update(){
		long temp = System.currentTimeMillis();
		oTimer = oTimer + temp;
		pTimer = pTimer + temp;
		lastTime = System.currentTimeMillis();
		
		if (pTimer > player.getSpeed()){
			pTimer = 0;
			int oDamage = opponent.getDamage();
			if (!player.getAttacked(oDamage)){
				finished = true;
				ifPlayerWin = false;
			}
		}
		if (oTimer > opponent.getSpeed() && !this.finished){
			oTimer = 0;
			int pDamage = player.getDamage();
			if (!opponent.getAttacked(pDamage)){
				finished = true;
				ifPlayerWin = true;
			}
		}
	}
}
