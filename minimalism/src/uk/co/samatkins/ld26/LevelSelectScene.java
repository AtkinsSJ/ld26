package uk.co.samatkins.ld26;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelectScene extends Scene {
	
	private Table table;

	public LevelSelectScene(LD26 game) {
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
		
		this.table.add("Choose a level to play").top().expand().row();
		
		// Grid of levels
		String[] levels = new String[]{
			"01", "02", "03", "04", "05"
		};
		
		for (String level : levels) {
			final String l = level;
			TextButton levelButton = new TextButton(level, skin);
			levelButton.addListener(new ClickListener() {
				{ levelName = l; }
				private String levelName;
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					game.loadLevel(this.levelName);
				}
			});
			this.table.add(levelButton).row();
		}

		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				game.setScreen(new MenuScene(game));
			}
		});
		this.table.add(backButton).left().expand();
		
		this.addActor(this.table);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

}
