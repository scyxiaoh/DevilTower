package scene;

public class Information {
	
	protected String content;
	protected int timer;
	
	public Information(String content) {
		this.content = content;
		this.timer = 0;
	}
	
	public void update() {
		if (this.timer == 179) {
			Scene scene = SceneManager.getInstance().getCurrentScene();
			assert scene.getClass() == GameScene.class;
			GameScene gS = (GameScene)scene;
			gS.removeInfoQueue(this);
		}
		else {
			this.timer++;
		}
	}
	
	public String getContent() {
		return this.content;
	}

}
