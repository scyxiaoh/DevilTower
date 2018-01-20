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
			a = new UndirectedAnimation(new MinuetoImage[]{Assets.getEntityTexturesAt(18),Assets.getEntityTexturesAt(19),Assets.getEntityTexturesAt(20),Assets.getEntityTexturesAt(21)});
		}
		this.setAnimation(a);
	}
	
	@Override
	public boolean getEncountered(Player p) {
		Scene scene = SceneManager.getInstance().getCurrentScene();
		assert scene.getClass() == GameScene.class;
		GameScene gS = (GameScene)scene;
		switch(id) {
		case 0:
			//elf
			gS.addDialogueQueue(new Dialogue(this, "???:  You arrive finally, warrior."));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  My king sent me here to resque the princess from this tower. Who are you?"));
			gS.addDialogueQueue(new Dialogue(this, "???:  I know your purpose, warrior. I'm Elisa, the elf guarded this tower. It is full of danger behind this door. You need my instructions firstly."));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  I fear no dangers!"));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Courage is good but not the only thing you need to save the princess. Your wisdom is also necessary."));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  There are magic doors just like the yellow door in front of you. They are everywhere in the tower and they are unbreakable since they are made by magic."));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  How can I access those doors then..."));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  You should find keys to them. There are three types of doors in the tower, each type of doors has its corresponding type of keys."));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Meanwhile, the tower is occupied by many powerful monsters. They have been here for hundreds of years. You need to fight wisely."));
			gS.addDialogueQueue(new Dialogue(p, "Warrior:  Thank you, Elisa. I will kill those monsters and save the princess!"));
			gS.addDialogueQueue(new Dialogue(this, "Elisa:  Good Luck, warrior. May the Mighty Tree bless you."));
			
			return false;
		}
		return false;
	}

}
