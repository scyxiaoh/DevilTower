package entity;

import map.LevelMap;

public class KeyElement extends UndirectedEntity{
	
	public KeyElement(String name, int x, int y, LevelMap m, int id) {
		super(name, x, y, m, id);
	}

	protected void initiateAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getEncountered(Player p) {
		// TODO Auto-generated method stub
		return false;
	}
}
