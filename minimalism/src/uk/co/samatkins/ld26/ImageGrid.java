package uk.co.samatkins.ld26;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ImageGrid extends Widget {
	
	public enum BlockType {
		EMPTY(Color.WHITE),
		SOLID(Color.BLUE),
		PLACED(Color.BLACK);
		
		BlockType(Color c) {
			this.color = c;
		}
		
		private Color color;
		public Color getColor() {
			return this.color;
		}
	};
	
	private int gridWidth;
	private int gridHeight;
	
	private float blockWidth;
	private float blockHeight;
	
	private BlockType[][] grid;
	private BlockType[][] target;
	private ShapeRenderer shapeRenderer;
	
	public ImageGrid(){//float x, float y, float width, float height) {
		
		//this.setBounds(0, 0, 100, 100);
		
		this.shapeRenderer = new ShapeRenderer();
		
		//this.setFillParent(true);
		//this.setLayoutEnabled(true);
	}
	
	@Override
	public void layout() {
		super.layout();
		
		this.blockWidth = this.getWidth() / this.gridWidth;
		this.blockHeight = this.getHeight() / this.gridHeight;
		
		Gdx.app.debug("Grid", this.getX() + ", " + this.getY() + " : " + this.getWidth() + " x " + this.getHeight());
	}
	
	public void init(BlockType[][] target) {
		
		this.gridWidth = target.length;
		this.gridHeight = target[0].length;
		
		this.blockWidth = this.getWidth() / this.gridWidth;
		this.blockHeight = this.getHeight() / this.gridHeight;
		
		this.target = target;
		
		Random r = new Random();
		
		this.grid = new BlockType[this.gridWidth][this.gridHeight];
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				if (this.target[i][j] == BlockType.SOLID) {
					this.grid[i][j] = BlockType.SOLID;
				} else {
					this.grid[i][j] = BlockType.EMPTY;
				}
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (this.grid == null) return;
		
		batch.end();
		
		this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		
		this.shapeRenderer.begin(ShapeType.FilledRectangle);
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				this.shapeRenderer.setColor(this.grid[i][j].getColor());
				this.shapeRenderer.filledRect( getX() + (i*blockWidth), getY() + (j*blockHeight), blockWidth, blockHeight);
			}
		}
		this.shapeRenderer.end();
		
		batch.begin();
	}
	
	@Override
	public float getMinWidth() {
		return this.gridWidth;
	}
	
	 @Override
	public float getMinHeight() {
		return this.gridHeight;
	}
	
	@Override
	public float getPrefWidth() {
		return this.getParent().getWidth();
	}
	
	@Override
	public float getPrefHeight() {
		return this.getParent().getHeight();
	}
}