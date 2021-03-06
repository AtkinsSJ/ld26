package uk.co.samatkins.ld26;

import uk.co.samatkins.ld26.ImageRenderer.BlockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ImageGrid extends Widget {
	
	private int gridWidth;
	private int gridHeight;
	
	private ImageRenderer image;
	private BlockType[][] target;
	
	@Override
	public void layout() {
		super.layout();

		this.setPosition(getX() + this.getParent().getX(),
				getY() + this.getParent().getY());
		
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
	
	public void fireTop(int column) {
		int x = column;
		int y = this.gridHeight-1;
		
		if (image.getBlock(x, y) != BlockType.EMPTY) {
			((PlayScene)this.getStage()).lose();
			return;
		} else {
			while (image.getBlock(x, y-1) == BlockType.EMPTY) {
				y--;
			}
			if (image.getBlock(x, y-1) == BlockType.INVALID) {
				((PlayScene)this.getStage()).lose();
				return;
			}
			this.image.setBlock(x, y, BlockType.PLACED);
		}

		if (this.isLevelComplete()) {
			((PlayScene)this.getStage()).win();
		}
	}
	
	public void fireBottom(int column) {
		int x = column;
		int y = 0;
		
		if (image.getBlock(x, y) != BlockType.EMPTY) {
			((PlayScene)this.getStage()).lose();
			return;
		} else {
			while (image.getBlock(x, y+1) == BlockType.EMPTY) {
				y++;
			}
			if (image.getBlock(x, y+1) == BlockType.INVALID) {
				((PlayScene)this.getStage()).lose();
				return;
			}
			this.image.setBlock(x, y, BlockType.PLACED);
		}

		if (this.isLevelComplete()) {
			((PlayScene)this.getStage()).win();
		}
	}
	
	public void fireLeft(int row) {
		int x = 0;
		int y = row;
		
		if (image.getBlock(x, y) != BlockType.EMPTY) {
			((PlayScene)this.getStage()).lose();
			return;
		} else {
			while (image.getBlock(x+1, y) == BlockType.EMPTY) {
				x++;
			}
			if (image.getBlock(x+1, y) == BlockType.INVALID) {
				((PlayScene)this.getStage()).lose();
				return;
			}
			this.image.setBlock(x, y, BlockType.PLACED);
		}
		
		if (this.isLevelComplete()) {
			((PlayScene)this.getStage()).win();
		}
	}
	
	public void fireRight(int row) {
		int x = this.gridWidth-1;
		int y = row;
		
		if (image.getBlock(x, y) != BlockType.EMPTY) {
			((PlayScene)this.getStage()).lose();
			return;
		} else {
			while (image.getBlock(x-1, y) == BlockType.EMPTY) {
				x--;
			}
			if (image.getBlock(x-1, y) == BlockType.INVALID) {
				((PlayScene)this.getStage()).lose();
				return;
			}
			this.image.setBlock(x, y, BlockType.PLACED);
		}
		
		if (this.isLevelComplete()) {
			((PlayScene)this.getStage()).win();
		}
	}
	
	private boolean isLevelComplete() {
		
		// Compare the current state to the target state
		for (int x=0; x<this.gridWidth; x++) {
			for (int y=0; y<this.gridHeight; y++) {
				if (this.image.getBlock(x, y) != target[x][y]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void restart() {
		// Clear all non-fixed cells from the image
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				if (this.image.getBlock(i, j) != BlockType.SOLID) {
					this.image.setBlock(i, j, BlockType.EMPTY);
				}
			}
		}
	}
}
