package entity;

import java.util.*;

import org.minueto.image.MinuetoImage;

import game.Assets;
import map.LevelMap;

public class Opponent extends DirectionedEntity {
	
	protected int attackDamage;
	protected int armor;
	protected int health;
	protected int attackSpeed;
	
	
	public Opponent(String name, int x, int y, LevelMap m, Direction d, int id) {
		super(name, x, y, m, d, id);
		this.initiateAnimation();
	}
	
	protected void initiateAnimation() {
		 MinuetoImage[] tempArray = {};
		 switch (id){
		 case 0: // Slime
			 tempArray = Assets.getEntityTextures().subList(23, 26).toArray(new MinuetoImage[4]);
			 break;
		 }
		 this.setAnimation(new DirectedAnimation(direction, tempArray, tempArray, tempArray, tempArray));
	}
	
	public int getSpeed(){
		return this.attackSpeed;
	}
	
	public int getDamage(){
		return this.attackDamage;
	}
	
	public boolean getAttacked(int damage){
		int realDamage = damage - this.armor;
		if (realDamage > 0)
			this.health -= realDamage;
		if (health <= 0)
			return false;
		return true;
	}
	
	public boolean getEncountered(Player p) {
		// TODO Auto-generated method stub
		return false;
	}
}
