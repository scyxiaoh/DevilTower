package entity;

import org.minueto.image.MinuetoImage;

import game.Assets;
import map.LevelMap;

public class KeyElement extends UndirectedEntity{
	
	public KeyElement(String name, int x, int y, LevelMap m, int id) {
		super(name, x, y, m, id);
	}

	protected void initiateAnimation() {
		 MinuetoImage[] tempArray = {};
		 switch (id){
		 case 0: // DownStairs
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(16)};
			 break;
		 case 1: // YellowKey
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(1)};
			 break;
		 case 2: // BlueKey
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(2)};
			 break;
		 case 3: // RedKey
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(3)};
			 break;
		 case 4: // YellowDoor
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(4),Assets.getEntityTexturesAt(5),Assets.getEntityTexturesAt(6),Assets.getEntityTexturesAt(7)};
			 break;
		 case 5: // BlueDoor
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(8),Assets.getEntityTexturesAt(9),Assets.getEntityTexturesAt(10),Assets.getEntityTexturesAt(11)};
			 break;
		 case 6: // RedDoor
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(12),Assets.getEntityTexturesAt(13),Assets.getEntityTexturesAt(14),Assets.getEntityTexturesAt(15)};
			 break; 
		 case 7: // UpStairs
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(17)};
			 break;
		 }
		 this.setAnimation(new UndirectedAnimation(tempArray));
	}
	
	public boolean getEncountered(Player p) {
		// TODO Auto-generated method stub
		return false;
	}
}
