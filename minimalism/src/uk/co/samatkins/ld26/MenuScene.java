package uk.co.samatkins.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScene extends Scene {
	
	private Table table;

	public MenuScene(LD26 game) {
		super(game, Color.WHITE);
	}
	
	@Override
	public void show() {
		super.show();
		
		Skin skin = this.game.getSkin();
		
		this.table = new Table(skin);
		
		this.table.setPosition(0, 0);
		this.table.setFillParent(true);
		this.table.pad(10);
		
		this.table.add("DropPix").row();
		this.table.add("A Ludum Dare 26 Jam entry by Sam Atkins").row();
		
		TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreen(new LevelSelectScene(game));
			}
		});
		this.table.add(playButton).row();
		
		TextButton quitButton = new TextButton("Quit", skin);
		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Gdx.app.exit();
			}
		});
		this.table.add(quitButton).row();
		
		this.addActor(table);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}