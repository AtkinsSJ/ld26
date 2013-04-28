package uk.co.samatkins.ld26;

import uk.co.samatkins.ld26.ImageRenderer.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayScene extends Scene {
	
	private Table table;
	
	private ImageGrid grid;
	private ImageThumbnail thumb;

	public PlayScene(LD26 game) {
		super(game, Color.WHITE);
	}
	
	@Override
	public void show() {
		super.show();
		
		Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("packed.atlas")));
		
		this.table = new Table(skin);
		this.table.setPosition(0, 0);
		this.table.setFillParent(true);
		
		Table leftArea = new Table(skin);
		
		leftArea.add();
		leftArea.add("Top Buttons");
		leftArea.add().row();
		leftArea.add("L");
		
		BlockType[][] image = new BlockType[][]{
				{BlockType.EMPTY, BlockType.EMPTY, BlockType.EMPTY, BlockType.PLACED},
				{BlockType.PLACED, BlockType.EMPTY, BlockType.PLACED, BlockType.SOLID}
		};
		
		this.grid = new ImageGrid();
		this.grid.init(image);
		leftArea.add(this.grid).expand();
		
		leftArea.add("R").row();
		
		leftArea.add();
		leftArea.add("Bottom Buttons");
		leftArea.add();
		
		this.table.add(leftArea).fill().expand();
		
		Table rightArea = new Table(skin);
		
		rightArea.add("Target:").row();
		
		this.thumb = new ImageThumbnail();
		this.thumb.init(image);
		rightArea.add(this.thumb);
		
		this.table.add(rightArea);
		
		this.addActor(this.table);
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		this.thumb.invalidate();
	}

}
