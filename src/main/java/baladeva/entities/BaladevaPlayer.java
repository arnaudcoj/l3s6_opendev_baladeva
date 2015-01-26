package baladeva.entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevel;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.MoveStrategyKeyboard8Dir;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import baladeva.entities.enemies.BaladevaWolf;

/**
 * BaladevaPlayer represent the entity which will be the main character
 * controled by the player with the MoveStrategyKeyboard8Dir, the player can
 * also hit with SPACE.
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaPlayer extends GameMovable implements Observer,
		Overlappable, GameEntity, Drawable, KeyListener {

	protected SpriteManager spriteManager;
	protected GameCanvas canvas;
	protected int spriteSize;
	protected Point direction;

	protected GameData data;
	protected BaladevaHit remainingHit;
	protected int frameHit;

	protected int frameInvulnerability;

	/**
	 * Constructor of the class BaladevaPlayer.
	 * 
	 * @param data
	 *            the actual data of the game.
	 * @param x
	 *            the initial x position.
	 * @param y
	 *            the initial y position.
	 */
	public BaladevaPlayer(GameData data, int x, int y) {
		super();
		this.frameInvulnerability = 0;
		this.frameHit = 0;
		this.canvas = data.getCanvas();
		this.data = data;
		this.spriteSize = data.getConfiguration().getSpriteSize();
		this.spriteManager = new SpriteManagerDefaultImpl(new DrawableImage(
				"/images/level1/baladeva_hero.png", canvas), this.spriteSize, 3);
		this.direction = new Point(0, 1);
		this.initSpriteManager();

		this.setPosition(new Point(x, y));

		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard8Dir();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();

		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());

		data.getCanvas().addKeyListener(keyboard);
		setDriver(moveDriver);

		data.getCanvas().addKeyListener(this);

		data.getLife().addObserver(this);

	}

	/**
	 * Initialize the spriteManager with a picture composed by 8 kind of
	 * animation, with 4 directions, and 4 directions hits.
	 */
	public void initSpriteManager() {
		this.spriteManager.setTypes("down", "left", "right", "up", "sdown",
				"sleft", "sright", "sup");
		this.spriteManager.setType("down");
		this.spriteManager.reset();
	}

	/**
	 * Draw the entity on the game window.
	 */
	@Override
	public void draw(Graphics g) {
		if (this.frameInvulnerability % 4 == 0)
			this.spriteManager.draw(g, position);
		this.decrementFrameInvulnerability();
		this.updateHit();
	}

	/**
	 * Manage the invulnerability frame decrement in order to make the player invulnerable only a few moment.
	 */
	private void decrementFrameInvulnerability() {
		if (this.invincible())
			this.frameInvulnerability--;
	}

	/**
	 * Change the sprite direction, this method is called after a hit to make the sprite back to its original state.
	 */
	protected void changeSpriteDirection() {
		if (direction.equals(new Point(1, 0))) {
			this.spriteManager.setType("right");
		} else if (direction.equals(new Point(-1, 0))) {
			this.spriteManager.setType("left");
		} else if (direction.equals(new Point(0, -1))) {
			this.spriteManager.setType("up");
		} else if (direction.equals(new Point(0, 1))) {
			this.spriteManager.setType("down");
		}
	}

	/**
	 * Manage the different hit made by the player.
	 */
	protected void updateHit() {
		if (this.isHitting()) {
			if (this.frameHit <= 0) {
				this.data.getUniverse().removeGameEntity(remainingHit);
				this.remainingHit = null;
				this.spriteManager.reset();
				this.changeSpriteDirection();
			} else {
				this.frameHit--;
				this.spriteManager.increment();
			}
		}
	}

	/**
	 * Return if the player is hitting or not.
	 * @return true if the player is hitting and false in the other case.
	 */
	public boolean isHitting() {
		return this.remainingHit != null;
	}

	/**
	 * Do one step move only if the player isn't hitting.
	 */
	@Override
	public void oneStepMove() {
		if (!this.isHitting())
			super.oneStepMove();
	}

	/**
	 * Add the sprite change side if the player change the direction.
	 */
	@Override
	public void oneStepMoveAddedBehavior() {
		Point d = this.moveDriver.getSpeedVector(this).getDirection();
		if (!direction.equals(d)) {
			if (d.equals(new Point(1, 0))) {
				this.spriteManager.setType("right");
				direction = d;
			} else if (d.equals(new Point(-1, 0))) {
				this.spriteManager.setType("left");
				direction = d;
			} else if (d.equals(new Point(0, -1))) {
				this.spriteManager.setType("up");
				direction = d;
			} else if (d.equals(new Point(0, 1))) {
				this.spriteManager.setType("down");
				direction = d;
			}
		}
		if (!(d.equals(new Point(0, 0)))) {
			this.spriteManager.increment();
		}
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
	 * Override keyPressed to know if the player pressed or not space
	 * @param arg0 the key pressed
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		keyPressed(arg0.getKeyCode());
	}

	/**
	 * Called by keyPressed, make a hit if the previous hit is finished.
	 * @param keyCode the keyCode give by keyPressed.
	 */
	public void keyPressed(int keyCode) {
		if (remainingHit == null) {
			switch (keyCode) {
			case KeyEvent.VK_SPACE: {
				Point hitPos = new Point((this.position.x + this.direction.x
						* this.spriteSize), (this.position.y + this.direction.y
						* this.spriteSize));
				String hitDir;
				if ((direction.equals(new Point(1, 0)))) {
					this.spriteManager.setType("sright");
					hitDir = "right";
				} else if ((direction.equals(new Point(-1, 0)))) {
					this.spriteManager.setType("sleft");
					hitDir = "left";
				} else if ((direction.equals(new Point(0, -1)))) {
					this.spriteManager.setType("sup");
					hitDir = "up";
				} else {
					this.spriteManager.setType("sdown");
					hitDir = "down";
				}
				this.remainingHit = new BaladevaHit(canvas, spriteSize, hitPos,
						hitDir);
				this.data.getUniverse().addGameEntity(this.remainingHit);
				this.frameHit = 3;
				break;
			}
			default:
				;
			}
		}
	}

	/**
	 * Not used here.
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		keyReleased(event.getKeyCode());
	}

	/**
	 * Not used here.
	 * @param keyCode the keyCode give by keyReleased.
	 */
	public void keyReleased(int keyCode) {
	}

	/**
	 * Not used here.
	 */
	@Override
	public void keyTyped(KeyEvent event) {
		keyTyped(event.getKeyCode());
	}

	/**
	 * Not used here.
	 * @param keyCode the keyCode give by keyTyped.
	 */
	public void keyTyped(int keyCode) {
	}

	/**
	 * Manage if the player is hit or not.
	 */
	public void getHit() {
		if (frameInvulnerability <= 0) {

			maj();
			this.frameInvulnerability = 20;
		}
	}

	/**
	 * Do nothing here.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
	}

	// Nom à changer, c'est quoi les histoires là
	public void maj() {
		this.data.getLife().setValue(this.data.getLife().getValue() - 1);
		if (this.data.getLife().getValue() > 0) {
			this.data.getUniverse().removeGameEntity(this);
			this.data.getUniverse().addGameEntity(this);
			Iterator<GameEntity> it = this.data.getUniverse()
					.getGameEntitiesIterator();
			while (it.hasNext()) {
				GameEntity ge = it.next();
				if (ge instanceof BaladevaWolf) {
					((BaladevaWolf) ge).initMotion(data, this.getPosition());
				}
			}
		} else {
			Iterator<GameLevel> it = this.data.getLevels().iterator();
			while (it.hasNext()) {
				GameLevel tmp = it.next();
				this.data.getLevels().remove(tmp);
			}
			this.data.getEndOfGame().setValue(true);
		}
	}

	/**
	 * Return if the player is currently invincible or not.
	 * @return true if the player is invincible and false in the other case.
	 */
	public boolean invincible() {
		return this.frameInvulnerability > 0;
	}
}
