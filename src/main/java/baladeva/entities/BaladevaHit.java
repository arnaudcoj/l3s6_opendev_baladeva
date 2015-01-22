package baladeva.entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameEntity;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

public class BaladevaHit extends KeyAdapter implements Drawable, GameEntity, Overlappable {

	protected SpriteManager spriteManager;
	protected GameCanvas canvas;
	protected int spriteSize;
	protected Point position;
	
	public BaladevaHit(GameCanvas canvas, int spriteSize, Point pos, String hitDir) {
		super();
		this.canvas = canvas;
		this.spriteSize = spriteSize;
		this.spriteManager = new SpriteManagerDefaultImpl(new DrawableImage(
				"/images/level1/hit.png", canvas), this.spriteSize, 1);
		this.position = new Point(32, 32);
		this.initSpriteManager(hitDir);

		this.position = pos;

		this.canvas.addKeyListener(this);
	}

	public void initSpriteManager(String hitDir) {
		this.spriteManager.setTypes("down", "left", "right", "up");
		this.spriteManager.setType(hitDir);
		this.spriteManager.reset();
	}
	
	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.spriteSize, this.spriteSize);
		rectangle.setLocation(position.x * this.spriteSize, position.y
				* this.spriteSize);
		return rectangle;
	}
	
	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void draw(Graphics g) {
		this.spriteManager.draw(g, position);
	}

}
