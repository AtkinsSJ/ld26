package uk.co.samatkins.ld26;

import uk.co.samatkins.ld26.ImageRenderer.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ImageThumbnail extends Widget {
	
	private ImageRenderer image;
	
	private int gridWidth;
	private int gridHeight;
	
	@Override
	public void layout() {
		super.layout();
		
		this.setPosition(getX() + this.getParent().getX(),
				getY() + this.getParent().getY());
		
		if (this.image != null) {
			this.image.resize(this.getWidth(), this.getHeight());
			this.image.setPosition(this.getX(), this.getY());
		}
		Gdx.app.debug("Thumb", this.getX() + ", " + this.getY() + " : " + this.getWidth() + " x " + this.getHeight());
		Gdx.app.debug("Thumb parent", this.getParent().getX() + ", " +  this.getParent().getY());
	}
	
	public void init(BlockType[][] grid) {
		this.gridWidth = grid.length;
		this.gridHeight = grid[0].length;
		
		this.image = new ImageRenderer(grid);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (this.image != null) {
			this.image.draw(batch, parentAlpha);
		}
	}
	
	@Override
	public float getMinWidth() {
		return Math.max(this.gridWidth * 4, 100);
	}
	
	 @Override
	public float getMinHeight() {
		return Math.max(this.gridHeight * 4, 100);
	}
	
	@Override
	public float getPrefWidth() {
		return (this.getMinWidth() + this.getMaxWidth() / 2);
	}
	
	@Override
	public float getPrefHeight() {
		return (this.getMinHeight() + this.getMaxHeight() / 2);
	}
	
	@Override
	public float getMaxWidth() {
		return Math.max(this.gridWidth * 8, 100);
	}
	
	 @Override
	public float getMaxHeight() {
		return Math.max(this.gridHeight * 8, 100);
	}
}
