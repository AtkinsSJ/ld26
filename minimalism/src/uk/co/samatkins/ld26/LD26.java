package uk.co.samatkins.ld26;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class LD26 extends Game {

	@Override
	public void create() {
		Gdx.graphics.setTitle("LD26: Minimalism, by Sam Atkins");
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		//this.setScreen(new PlayScene(this));
	}
	
	public void playLevel(String level) {
		this.setScreen(new PlayScene(this, level));
	}

}