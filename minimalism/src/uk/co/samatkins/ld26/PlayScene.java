package uk.co.samatkins.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayScene extends Scene {
	
	private Table table;
	
	private ImageGrid grid;

	public PlayScene(LD26 game) {
		super(game, Color.BLACK);
	}
	
	@Override
	public void show() {
		super.show();
		
		Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("packed.atlas")));
		
		this.table = new Table(skin);
		this.table.debug();
		this.table.setPosition(0, 0);
		this.table.setFillParent(true);
		
		this.table.add();
		this.table.add("Top Buttons");
		this.table.add().row();
		this.table.add("L");
		
		this.grid = new ImageGrid();//0, 0, getWidth(), getHeight());
		this.grid.init(20, 20);
		this.table.add(this.grid).expand();
		
		this.table.add("R").row();
		
		this.table.add();
		this.table.add("Bottom Buttons");
		this.table.add();
		
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
		Table.drawDebug(this);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

}
