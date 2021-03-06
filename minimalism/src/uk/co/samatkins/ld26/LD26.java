package uk.co.samatkins.ld26;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LD26 extends Game {
	
	private Skin skin;

	@Override
	public void create() {
		Gdx.graphics.setTitle("DropPix");
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		this.setScreen(new MenuScene(this));
		//this.playLevel(Gdx.files.internal("levels/01.lvl").readString());
	}
	
	public void loadLevel(String levelName) {
		this.playLevel(levelName, Gdx.files.internal("levels/"+levelName+".lvl").readString());
	}
	
	public void playLevel(String levelName, String level) {
		this.setScreen(new PlayScene(this, levelName, level));
	}
	
	public Skin getSkin() {
		if (this.skin == null) {
			this.skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("packed.atlas")));
		}
		
		return this.skin;
	}

}