package uk.co.samatkins.ld26;

import uk.co.samatkins.ld26.LD26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Scene extends Stage implements Screen {
	
	public LD26 game;
	private Color backgroundColor;
	
	public Scene(LD26 game, Color backgroundColor) {
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		this.game = game;
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.update(delta);
		this.draw();
	}

	abstract public void update(float delta);

	@Override
	public void resize(int width, int height) {
		this.setViewport(width, height, false);
	}

	@Override
	public void show() {
		this.clear();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		// Remove all entities
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
