package uk.co.samatkins.ld26;

import java.util.Random;

import uk.co.samatkins.ld26.ImageRenderer.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ImageGrid extends Widget {
	
	
	private int gridWidth;
	private int gridHeight;
	
	private ImageRenderer image;
	private BlockType[][] target;
	
	public ImageGrid(){
	}
	
	@Override
	public void layout() {
		super.layout();
		
		if (this.image != null) {
			this.image.resize(this.getWidth(), this.getHeight());
			this.image.setPosition(this.getX(), this.getY());
		}
		
		Gdx.app.debug("Grid", this.getX() + ", " + this.getY() + " : " + this.getWidth() + " x " + this.getHeight());
	}
	
	public void init(BlockType[][] target) {
		
		this.gridWidth = target.length;
		this.gridHeight = target[0].length;
		
		this.target = target;
		
		BlockType[][] grid = new BlockType[this.gridWidth][this.gridHeight];
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				if (this.target[i][j] == BlockType.SOLID) {
					grid[i][j] = BlockType.SOLID;
				} else {
					grid[i][j] = BlockType.EMPTY;
				}
			}
		}
		
		this.image = new ImageRenderer(grid);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if (this.image == null) return;
		
		this.image.draw(batch, parentAlpha);
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
