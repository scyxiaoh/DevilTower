package entity;

import java.util.*;

import org.minueto.image.MinuetoImage;

import game.Assets;
import map.LevelMap;
import scene.Event;
import scene.GameScene;
import scene.Scene;
import scene.SceneManager;

public class Opponent extends DirectionedEntity {
	
	protected int attackDamage;
	protected int armor;
	protected int health;
	protected int attackSpeed;
	
	
	public Opponent(String name, int x, int y, LevelMap m, Direction d, int id) {
		super(name, x, y, m, d, id);
		this.initiateAnimation();
	} 
	
	@Override
	protected void initiateAnimation() {
		// id: 0 - HuiTaiLang
		
		 MinuetoImage[][] sprites = new MinuetoImage[4][4];
		 for (int i = 0; i < 4; i++) {
			 for (int j = 0; j < 4; j++) {
		     	sprites[i][j] = Assets.getOpponentTexturesAt(i*4 + j +16*id);
		     }
		 }

		 DirectedAnimation Animation = new DirectedAnimation(Direction.South, sprites[3], sprites[0], sprites[1], sprites[2]);
		 this.setAnimation(Animation);
		 this.playAnimation();
	}
	
	public int getAttackSpeed(){
		return this.attackSpeed;
	}
	
	public int getAttackDamage(){
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
	
	public void delete() {
		Scene scene = SceneManager.getInstance().getCurrentScene();
		assert scene.getClass() == GameScene.class;
		GameScene gS = (GameScene)scene;
		gS.addEventQueue(new Event() {
			public void invoke() {
				gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
			}
		});
	}
}
