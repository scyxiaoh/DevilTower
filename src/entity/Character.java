package entity;

import org.minueto.image.MinuetoImage;
import map.*;

public class Character extends UndirectedEntity{

	public Character(String name, int x, int y, LevelMap m, int id) {
		super(name, x, y, m, id);
	}
	
	protected void initiateAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean getEncountered(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

}
