package entity;

import java.util.*;
import map.LevelMap;

public class Opponent extends DirectionedEntity {
	
	protected int attackDamage;
	protected int armor;
	protected int health;
	protected int attackSpeed;
	
	
	public Opponent(String name, int x, int y, LevelMap m, Direction d, DirectedAnimation a, int id) {
		super(name, x, y, m, d, id);
	}
	
	protected void initiateAnimation() {
		// TODO Auto-generated method stub
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
