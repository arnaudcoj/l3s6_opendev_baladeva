package baladeva.entities;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameUniverse;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import baladeva.utils.MoveStrategyKeyboard8Dir;

public class BaladevaPlayer extends GameMovable implements Overlappable, GameEntity,
		Drawable, KeyListener {

	protected SpriteManager spriteManager;
	protected GameCanvas canvas;
	protected int spriteSize;
	protected Point direction;

	protected GameUniverse universe;
	protected BaladevaHit remainingHit;
	protected int frameHit;

	public BaladevaPlayer(GameData data, int x, int y) {
		super();
		this.canvas = data.getCanvas();
		this.universe = data.getUniverse();
		this.spriteSize = data.getConfiguration().getSpriteSize();
		this.spriteManager = new SpriteManagerDefaultImpl(new DrawableImage(
				"/images/level1/baladeva_hero.png", canvas), this.spriteSize, 3);
		this.direction = new Point(0, 0);
		this.initSpriteManager();

		this.setPosition(new Point(x, y));

		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard8Dir();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();

		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());

		data.getCanvas().addKeyListener(keyboard);
		setDriver(moveDriver);

		data.getCanvas().addKeyListener(this);

	}

	public void initSpriteManager() {
		this.spriteManager.setTypes("down", "left", "right", "up", "sdown",
				"sleft", "sright", "sup");
		this.spriteManager.setType("down");
		this.spriteManager.reset();
	}

	@Override
	public void draw(Graphics g) {
		this.spriteManager.draw(g, position);
		if (remainingHit != null) {
			if (this.frameHit <= 0) {
				this.universe.removeGameEntity(remainingHit);
				remainingHit = null;
			} else {
				this.frameHit--;
			}
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.spriteSize, this.spriteSize);
		rectangle.setLocation(position.x * this.spriteSize, position.y
				* this.spriteSize);
		return rectangle;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		Point d = this.moveDriver.getSpeedVector(this).getDirection();
		if ((!direction.equals(d)) && d.equals(new Point(1, 0))) {
			this.spriteManager.setType("right");
			direction = d;
		} else if ((!direction.equals(d)) && d.equals(new Point(-1, 0))) {
			this.spriteManager.setType("left");
			direction = d;
		} else if ((!direction.equals(d)) && d.equals(new Point(0, -1))) {
			this.spriteManager.setType("up");
			direction = d;
		} else if ((!direction.equals(d)) && d.equals(new Point(0, 1))) {
			this.spriteManager.setType("down");
			direction = d;
		} else if (!(d.equals(new Point(0, 0)))) {
			this.spriteManager.increment();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keyPressed(arg0.getKeyCode());
	}

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
				this.universe.addGameEntity(this.remainingHit);
				this.frameHit = 3;
				break;
			}
			default:
				;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keyReleased(event.getKeyCode());
	}

	public void keyReleased(int keyCode) {
		/*
		 * switch (keyCode) { case KeyEvent.VK_SPACE: { if
		 * ((direction.equals(new Point(1, 0)))) {
		 * this.spriteManager.setType("right"); } else if ((direction.equals(new
		 * Point(-1, 0)))) { this.spriteManager.setType("left"); } else if
		 * ((direction.equals(new Point(0, -1)))) {
		 * this.spriteManager.setType("up"); } else {
		 * this.spriteManager.setType("down"); } break; } default: ; }
		 */
	}

	@Override
	public void keyTyped(KeyEvent event) {
		keyTyped(event.getKeyCode());
	}

	public void keyTyped(int keyCode) {
	}
}