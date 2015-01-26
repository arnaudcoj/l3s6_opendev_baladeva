package baladeva.entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategy;
import gameframework.motion.SpeedVector;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

/**
 * BaladevaEnemy represent the entities which will be the opponent of the player
 * they only can hit by touching the player and be hit by the entity BaladevaHit
 * the MoveStrategy have to be implemented by the class which extends this one
 * and should have a image to give to the sprite manager
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
public abstract class BaladevaEnemy extends GameMovable implements
		Overlappable, GameEntity, Drawable {

	protected GameCanvas canvas;
	protected SpriteManager spriteManager;
	protected SpeedVector speed = SpeedVector.createNullVector();
	protected int spriteSize;
	protected GameData data;
	protected int hitPoints;
	protected int scorePoints;
	protected int frameInvulnerability;

	/**
	 * Will return the MoveStrategy linked to the enemy.
	 * @param pos the position of the entity
	 * @param goal the goal of the enemy (most of the case in our game : the goal is the player)
	 * @return the MoveStrategy choose at the creation with the given param
	 */
	protected abstract MoveStrategy getMoveStrategy(Point pos, Point goal);

	/**
	 * Return the link to the picture choose to represent the entity.
	 * @return the link to the picture choose to represent the entity in String format
	 */
	protected abstract String imageStr();

	/**
	 * Initialize the different part needed to move the entity
	 * @param data the data of the current game
	 * @param goal the goal of the enemy
	 */
	protected void initMotion(GameData data, Point goal) {
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(this.getMoveStrategy(this.position, goal));
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);
	}

	/**
	 * Constructor of the class BaladevaEnemy
	 * @param data the data of the current game
	 * @param pos the initial position of the enemy
	 * @param goal the goal of the enemy
	 */
	public BaladevaEnemy(GameData data, Point pos, Point goal) {
		super();

		this.canvas = data.getCanvas();
		this.spriteSize = data.getConfiguration().getSpriteSize();

		DrawableImage img = new DrawableImage(this.imageStr(), canvas);
		this.spriteManager = new SpriteManagerDefaultImpl(img, this.spriteSize,
				3);
		this.initSpriteManager();

		this.setPosition(pos);

		this.initMotion(data, goal);

		this.data = data;
	}

	/*
	 * Initialize the SpriteManager with the basic position of the sprite, only work with picture which 
	 * had 4th position.
	 */
	public void initSpriteManager() {
		this.spriteManager.setTypes("down", "left", "right", "up");
		this.spriteManager.setType("down");
		this.spriteManager.reset();
	}

	/**
	 * Add the direction change to the behavior of oneStepMove
	 */
	@Override
	public void oneStepMoveAddedBehavior() {
		this.changeDirection(this.getSpeedVector());
	}

	/**
	 * Draw the entity on the game window and increment the spriteManager
	 */
	@Override
	public void draw(Graphics g) {
		if (this.frameInvulnerability % 4 == 0)
			this.spriteManager.draw(g, position);
		this.decrementFrameInvulnerability();

		this.spriteManager.increment();

	}

	/**
	 * Manage the invulnerability of the enemy when hit
	 */
	private void decrementFrameInvulnerability() {
		if (this.invincible())
			this.frameInvulnerability--;
	}

	/**
	 * Return the Rectangle which represent the entity in order to manage interaction with other entities
	 * @return the Rectangle which represent the entity
	 */
	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.spriteSize, this.spriteSize);
		rectangle.setLocation(position.x * this.spriteSize, position.y
				* this.spriteSize);
		return rectangle;
	}

	/**
	 * Change the direction of the sprite
	 * @param m the SpeedVector which will give the new direction
	 */
	public void changeDirection(SpeedVector m) {
		Point direction = m.getDirection();
		if (direction.getX() == 1)
			this.spriteManager.setType("right");
		else if (direction.getX() == -1)
			this.spriteManager.setType("left");
		else if (direction.getY() == 1)
			this.spriteManager.setType("down");
		else if (direction.getY() == -1)
			this.spriteManager.setType("up");
	}

	/**
	 * Manage what have to happen when the enemy is hit
	 */
	public void getHit() {
		if (hitPoints > 0) {
			hitPoints--;
			frameInvulnerability = 20;
		}
		if (hitPoints == 0) {
			this.data.getUniverse().removeGameEntity(this);
			this.data.getScore().setValue(
					this.data.getScore().getValue() + this.scorePoints);
			if (noMoreEnemy()) {
				this.data.getEndOfGame().setValue(true);
			}
		}
	}

	/**
	 * Return if there still enemies on the level or not
	 * @return true if all the enemies are dead or false in the other case
	 */
	private boolean noMoreEnemy() {
		Iterator<GameEntity> it = this.data.getUniverse().getGameEntitiesIterator();
		while(it.hasNext())
			if(it.next() instanceof BaladevaEnemy)
				return false;
		return true;
	}

	/**
	 * Return if the enemy is invincible or not
	 * @return true if the enemy still invincible or false in the other case
	 */
	public boolean invincible() {
		return frameInvulnerability > 0;
	}

}
