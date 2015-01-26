package baladeva.entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameEntity;
import gameframework.motion.Movable;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

/**
 * BaladevaHit represent a hiy given by the player, this hit will damage the enemies.
 * They will appear in front of the player.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
public class BaladevaHit extends KeyAdapter implements Movable, Drawable, GameEntity, Overlappable {

	protected SpriteManager spriteManager;
	protected GameCanvas canvas;
	protected int spriteSize;
	protected Point position;
	protected String hitDir;
	
	/**
	 * Constructor of the class.
	 * @param canvas the canvas use to draw.
	 * @param spriteSize the size of the sprite uses.
	 * @param pos the position of the hit.
	 * @param hitDir the direction of the hit.
	 */
	public BaladevaHit(GameCanvas canvas, int spriteSize, Point pos, String hitDir) {
		super();
		this.canvas = canvas;
		this.spriteSize = spriteSize;
		this.spriteManager = new SpriteManagerDefaultImpl(new DrawableImage(
				"/images/level1/hit.png", canvas), this.spriteSize, 1);
		this.position = new Point(32, 32);
		this.hitDir = hitDir;
		this.initSpriteManager(this.hitDir);

		this.position = pos;

		this.canvas.addKeyListener(this);
	}

	/**
	 * Initialize the spriteManager linked to the hit.
	 * @param hitDir the direction of the hit.
	 */
	public void initSpriteManager(String hitDir) {
		this.spriteManager.setTypes("down", "left", "right", "up");
		this.spriteManager.setType(hitDir);
		this.spriteManager.reset();
	}
	

	/**
	 * Return the Rectangle which represent the entity in order to manage interaction with other entities.
	 * @return the Rectangle which represent the entity.
	 */
	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.spriteSize, this.spriteSize);
		rectangle.setLocation(position.x * this.spriteSize, position.y
				* this.spriteSize);
		return rectangle;
	}
	
	/**
	 * Return the position of the hit.
	 */
	@Override
	public Point getPosition() {
		return this.position;
	}
	
	/**
	 * Return the direction of the hit.
	 * @return the direction of the hit.
	 */
	public String getHitDir() {
		return this.hitDir;
	}

	/**
	 * Draw the entity on the game window.
	 */
	@Override
	public void draw(Graphics g) {
		this.spriteManager.draw(g, position);
	}

	/**
	 * Give a nullVector, because the hit is not supposed to move.
	 * @return a nullVector.
	 */
	@Override
	public SpeedVector getSpeedVector() {
		return SpeedVector.createNullVector();
	}

	/**
	 *  Do nothing because the speed vector don't have to change.
	 */
	@Override
	public void setSpeedVector(SpeedVector m) {
	}

	/**
	 * Do nothing because the hit don't move.
	 */
	@Override
	public void oneStepMove() {
	}

}
