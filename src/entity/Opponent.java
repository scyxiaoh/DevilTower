package entity;

import java.util.*;

public class Opponent extends DirectionedEntity {
	protected OpponentType type;
	protected String name;
	protected int attackDamage;
	protected int armor;
	protected int health;
	protected int attackSpeed;
	
	
	public Opponent(Direction d, DirectedAnimation a, OpponentType t, String n, int damage, int defence, int hp, int as) {
		super(d, a);
		this.type = t;
		this.name = n;
		this.attackDamage = damage;
		this.armor = defence;
		this.health = hp;
		this.attackSpeed = as;
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

}
