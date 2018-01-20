package entity;

import org.minueto.image.MinuetoImage;
import game.Assets;
import map.*;
import scene.*;

public class Character extends UndirectedEntity{

	public Character(String name, int x, int y, LevelMap m, int id) {
		super(name, x, y, m, id);
	}
	
	protected void initiateAnimation() {
		UndirectedAnimation a = null;
		switch(id) {
		case 0:
			//elf
			a = new UndirectedAnimation(Assets.getEntityTextures().subList(18, 22).toArray(new MinuetoImage[4]));
		}
		this.setAnimation(a);
		this.playAnimation();
	}
	
	@Override
	public boolean getEncountered(Player p) {
		Scene scene = SceneManager.getInstance().getCurrentScene();
		assert scene.getClass() == GameScene.class;
		GameScene gS = (GameScene)scene;
		switch(id) {
		case 0:
			//elf
			gS.addDialogueQueue(new Dialogue(this, "???:  You arrive finally, warrior.", 40));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  My king sent me here to resque the princess from this tower. Who are you?", 40));
			gS.addDialogueQueue(new Dialogue(this, "???:  I know your purpose, warrior. I'm Elisa, the elf guarded this tower. It is full of danger behind this door. You need my instructions firstly.", 40));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  I fear no dangers!", 40));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Courage is good but not the only thing you need to save the princess. Your wisdom is also necessary.", 40));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  There are magic doors just like the yellow door in front of you. They are everywhere in the tower and they are unbreakable since they are made by magic.", 40));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  How can I access those doors then...", 40));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  You should find keys to them. There are three types of doors in the tower, each type of doors has its corresponding type of keys.", 40));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Meanwhile, the tower is occupied by many powerful monsters. They have been here for hundreds of years. You need to fight wisely.", 40));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  Thank you, Elisa. I will kill those monsters and save the princess!", 40));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Good Luck, warrior. May the Mighty Tree bless you.", 40));
			
			return false;
		}
		return false;
	}

}
