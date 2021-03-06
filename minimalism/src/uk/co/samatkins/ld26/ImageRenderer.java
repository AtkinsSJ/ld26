package uk.co.samatkins.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageRenderer extends Actor {

	public enum BlockType {
		EMPTY(Color.WHITE),
		SOLID(Color.BLUE),
		PLACED(Color.BLACK),
		INVALID(Color.CLEAR);
		
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
	private ShapeRenderer shapeRenderer;
	
	public ImageRenderer(BlockType[][] grid) {
		
		this.shapeRenderer = new ShapeRenderer();
		
		this.grid = grid;
		
		this.gridWidth = grid.length;
		this.gridHeight = grid[0].length;
		
		this.blockWidth = this.getWidth() / this.gridWidth;
		this.blockHeight = this.getHeight() / this.gridHeight;
	}
	
	public void resize(float width, float height) {
		this.setSize(width, height);
		
		this.blockWidth = this.getWidth() / this.gridWidth;
		this.blockHeight = this.getHeight() / this.gridHeight;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		batch.end();
		
		this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		
		// Cells
		this.shapeRenderer.begin(ShapeType.FilledRectangle);
		for (int i=0; i<this.gridWidth; i++) {
			for (int j=0; j<this.gridHeight; j++) {
				this.shapeRenderer.setColor(this.grid[i][j].getColor());
				this.shapeRenderer.filledRect( getX() + (i*blockWidth), getY() + (j*blockHeight), blockWidth, blockHeight);
			}
		}
		this.shapeRenderer.end();
		
		// Lines
		this.shapeRenderer.begin(ShapeType.Line);
		this.shapeRenderer.setColor(Color.BLACK);
		
		float xPos;
		for (int x=0; x<=this.gridWidth; x++) {
			xPos = getX() + (x * blockWidth);
			this.shapeRenderer.line(xPos, getY(), xPos, getY() + getHeight());
		}
		
		float yPos;
		for (int y=0; y<=this.gridHeight; y++) {
			yPos = getY() + (y * blockHeight);
			this.shapeRenderer.line(getX(), yPos, getX() + getWidth(), yPos);
		}
		
		this.shapeRenderer.end();
		
		batch.begin();
	}
	
	public BlockType getBlock(int x, int y) {
		if (x < 0 || x >= this.gridWidth || y < 0 || y >= this.gridHeight) {
			return BlockType.INVALID;
		}
		return this.grid[x][y];
	}
	
	public void setBlock(int x, int y, BlockType type) {
		this.grid[x][y] = type;
	}
	
	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
		Gdx.app.debug("Position", "Setting renderer position: " + x + ", " + y);
	}
	
}
