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
import gameframework.motion.MoveStrategyKeyboard;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import baladeva.utils.MoveStrategyKeyboard8Dir;

public class BaladevaPlayer extends GameMovable implements Observer,
		Overlappable, GameEntity, Drawable, KeyListener {

	protected SpriteManager spriteManager;
	protected GameCanvas canvas;
	protected int spriteSize;
	protected Point direction;

	protected GameData data;
	protected BaladevaHit remainingHit;
	protected int frameHit;
	protected int life;

	public BaladevaPlayer(GameData data, int x, int y) {
		super();
		this.life = data.getCanvas().getLife();
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
		;

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
		this.updateHit();
	}

	protected void changeSpriteDirection() {
		Point d = this.moveDriver.getSpeedVector(this).getDirection();
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

	public boolean isHitting() {
		return this.remainingHit != null;
	}

	@Override
	public void oneStepMove() {
		if (!this.isHitting())
			super.oneStepMove();
	}

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
			} else if (!(d.equals(new Point(0, 0)))) {
				this.spriteManager.increment();
				System.out.println(d);
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
				this.data.getUniverse().addGameEntity(this.remainingHit);
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
	}

	@Override
	public void keyTyped(KeyEvent event) {
		keyTyped(event.getKeyCode());
	}

	public void keyTyped(int keyCode) {
	}

	public void getHit() {
		this.life--;
		update(this.data.getLife(), this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.data.getUniverse().removeGameEntity(this);
		this.data.getUniverse().addGameEntity(this);
		if (this.life >= 0) this.data.getLife().setValue(this.life);
		this.setPosition(new Point(this.position.x+50,this.position.y+50));
	}
}
