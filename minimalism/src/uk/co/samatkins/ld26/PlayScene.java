package uk.co.samatkins.ld26;

import uk.co.samatkins.Base64;
import uk.co.samatkins.ld26.ImageRenderer.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayScene extends Scene {
	
	private Table table;
	
	private BlockType[][] level;
	
	private ImageGrid grid;
	private ImageThumbnail thumb;

	public PlayScene(LD26 game, String levelString) {
		super(game, Color.WHITE);
		
		this.level = this.levelFromString(levelString);
	}

	@Override
	public void show() {
		super.show();
		
		Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("packed.atlas")));
		
		this.table = new Table(skin);
		this.table.setPosition(0, 0);
		this.table.setFillParent(true);
		this.table.pad(10);
		
		int gridW = level.length;
		int gridH = level[0].length;
		
		this.grid = new ImageGrid();
		this.grid.init(level);
		
		Table leftArea = new Table(skin);
		
		leftArea.add();
		
		// Top Buttons
		Table topButtons = new Table(skin);
		for (int i=0; i<gridW; i++) {
			final int j = i;
			TextButton btn = new TextButton(" ", skin);
			btn.addListener(new ClickListener() {
				{ index = j; }
				private int index;
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Gdx.app.debug("ClickListener", "Index: " + this.index);
					grid.fireTop(index);
				}
			});
			topButtons.add(btn).expand().fill();
		}
		
		leftArea.add(topButtons).fill();
		leftArea.add().row();

		// Left Buttons
		Table leftButtons = new Table(skin);
		for (int i=gridH-1; i>=0; i--) {
			final int j = i;
			TextButton btn = new TextButton("     ", skin);
			btn.addListener(new ClickListener() {
				{ index = j; }
				private int index;
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Gdx.app.debug("ClickListener", "Index: " + this.index);
					grid.fireLeft(index);
				}
			});
			leftButtons.add(btn).expand().fill().row();
		}
		
		leftArea.add(leftButtons).fill();
		
		leftArea.add(this.grid).expand();
		
		// Right Buttons
		Table rightButtons = new Table(skin);
		for (int i=gridH-1; i>=0; i--) {
			final int j = i;
			TextButton btn = new TextButton("     ", skin);
			btn.addListener(new ClickListener() {
				{ index = j; }
				private int index;
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Gdx.app.debug("ClickListener", "Index: " + this.index);
					grid.fireRight(index);
				}
			});
			rightButtons.add(btn).expand().fill().row();
		}
		leftArea.add(rightButtons).fill().row();

		// Bottom Buttons
		Table bottomButtons = new Table(skin);
		for (int i=0; i<gridH; i++) {
			final int j = i;
			TextButton btn = new TextButton(" ", skin);
			btn.addListener(new ClickListener() {
				{ index = j; }
				private int index;
				
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					Gdx.app.debug("ClickListener", "Index: " + this.index);
					grid.fireBottom(index);
				}
			});
			bottomButtons.add(btn).expand().fill();
		}
		
		leftArea.add();
		leftArea.add(bottomButtons).fill();
		leftArea.add();
		
		this.table.add(leftArea).fill().expand().space(10);
		
		Table rightArea = new Table(skin);
		
		rightArea.add("Target:").row();
		
		this.thumb = new ImageThumbnail();
		this.thumb.init(level);
		rightArea.add(this.thumb).expand().fill().row();
		
		TextButton restartButton = new TextButton("Restart", skin);
		restartButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				grid.restart();
			}
		});
		rightArea.add(restartButton).bottom().expand().row();
		
		this.table.add(rightArea).space(10).fill();//.expand().fill();
		
		this.addActor(this.table);
		
	}

	@Override
	public void update(float delta) {

	}
	
	@Override
	public void draw() {
		super.draw();
//		Table.drawDebug(this);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		thumb.invalidateHierarchy();
	}
	
	private BlockType[][] levelFromString(String levelString) {
		levelString = Base64.decode(levelString);
		
		String[] parts = levelString.split(":");
		
		String[] dimensions = parts[0].split("x");
		int w = Integer.parseInt(dimensions[0]);
		int h = Integer.parseInt(dimensions[1]);
		
		BlockType[][] level = new BlockType[w][h];
		for (int i=0; i<parts[1].length(); i++) {
			Gdx.app.debug("x,y", i%w + ", " + i/w);
			switch (parts[1].charAt(i)) {
			case '0':
				level[i%w][i/w] = BlockType.EMPTY; break;
			case '1':
				level[i%w][i/w] = BlockType.SOLID; break;
			case '2':
				level[i%w][i/w] = BlockType.PLACED; break;
			}
		}
		
			
		return level;
	}
	
	public void win() {
		Gdx.app.debug("WIN", "You win! :D");
	}
	
	public void lose() {
		Gdx.app.debug("LOSE", "You lose! :(");
	}

}
