package entity;

import org.minueto.image.MinuetoImage;

import game.Assets;
import map.LevelMap;
import scene.Event;
import scene.GameScene;
import scene.Scene;
import scene.SceneManager;

public class KeyElement extends UndirectedEntity{
	
	public KeyElement(String name, int x, int y, LevelMap m, int id) {
		super(name, x, y, m, id);
	}

	protected void initiateAnimation() {
		 MinuetoImage[] tempArray = {};
		 switch (id){
		 case 0: // UpStairs
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(17)};
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
		 case 7: // DownStairs
			 tempArray = new MinuetoImage[]{Assets.getEntityTexturesAt(16)};
			 break;
		 }
		 this.setAnimation(new UndirectedAnimation(tempArray));
	}
	
	public boolean getEncountered(Player p) {
		Scene scene = SceneManager.getInstance().getCurrentScene();
		assert scene.getClass() == GameScene.class;
		GameScene gS = (GameScene)scene;
		
		switch(id){
		case 0:	// UpStairs level 0-0
			gS.setCurrentLevel(1);
			p.setPosition(10*32, 19*32);
			gS.getCurrentMap().setCam(0, 7*32);
			break;
		case 1:
			p.changeOnKeys(0, 1);
			gS.addEventQueue(new Event() {
				public void invoke() {
					Scene scene = SceneManager.getInstance().getCurrentScene();
					assert scene.getClass() == GameScene.class;
					GameScene gS = (GameScene)scene;
					gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
				}
			});
			break;
		case 2:
			p.changeOnKeys(1, 1);
			gS.addEventQueue(new Event() {
				public void invoke() {
					Scene scene = SceneManager.getInstance().getCurrentScene();
					assert scene.getClass() == GameScene.class;
					GameScene gS = (GameScene)scene;
					gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
				}
			});
			break;
		case 3:
			p.changeOnKeys(2, 1);
			gS.addEventQueue(new Event() {
				public void invoke() {
					Scene scene = SceneManager.getInstance().getCurrentScene();
					assert scene.getClass() == GameScene.class;
					GameScene gS = (GameScene)scene;
					gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
				}
			});
			break;
		case 4:
			if (p.changeOnKeys(0, -1)){
				gS.addEventQueue(new Event() {
					public void invoke() {
						Scene scene = SceneManager.getInstance().getCurrentScene();
						assert scene.getClass() == GameScene.class;
						GameScene gS = (GameScene)scene;
						gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
					}
				});
			}
			break;
		case 5:
			if (p.changeOnKeys(1, -1)){
				gS.addEventQueue(new Event() {
					public void invoke() {
						Scene scene = SceneManager.getInstance().getCurrentScene();
						assert scene.getClass() == GameScene.class;
						GameScene gS = (GameScene)scene;
						gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
					}
				});
			}
			break;
		case 6:
			if (p.changeOnKeys(2, -1)){
				gS.addEventQueue(new Event() {
					public void invoke() {
						Scene scene = SceneManager.getInstance().getCurrentScene();
						assert scene.getClass() == GameScene.class;
						GameScene gS = (GameScene)scene;
						gS.getCurrentMap().getTile(positionX/32, positionY/32).setMyEntity(null);
					}
				});
			}
			break;
		case 7:	//DownStairs level 1-0
			gS.setCurrentLevel(0);
			p.setPosition(10*32, 0*32);
			gS.getCurrentMap().setCam(0, 0);
			break;
		}
		return false;
	}
}
