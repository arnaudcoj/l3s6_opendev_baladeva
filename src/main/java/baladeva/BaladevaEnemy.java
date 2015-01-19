package baladeva;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyRandom;
import gameframework.motion.SpeedVector;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class BaladevaEnemy extends GameMovable implements GameEntity, Drawable {

	protected DrawableImage img;
	protected GameCanvas canvas;
	protected SpriteManager spriteManager;
	protected SpeedVector speed = SpeedVector.createNullVector();

	public BaladevaEnemy(GameData data, int x, int y) {
		super();
		this.canvas = data.getCanvas();
		this.img = new DrawableImage("/images/level1/red.png", this.canvas);
		this.spriteManager = new SpriteManagerDefaultImpl(img, data
				.getConfiguration().getSpriteSize(), 3);
		this.initSpriteManager();
		this.setPosition(new Point(x, y));
		MoveStrategyRandom random = new MoveStrategyRandom();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(random);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);
	}

	public void initSpriteManager() {
		this.spriteManager.setTypes("down", "left", "right", "up");
		this.spriteManager.setType("down");
		this.spriteManager.reset();
	}

	@Override
	public void draw(Graphics g) {
		this.spriteManager.draw(g, position);
		this.spriteManager.increment();
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.img.getWidth(),
				this.img.getWidth());
		rectangle.setLocation(position.x, position.y);
		return rectangle;
	}

	@Override
	public void oneStepMove() {
		SpeedVector m = moveDriver.getSpeedVector(this);
		if (!m.equals(speedVector))
			this.changeDirection(m);
		if (!(m.getDirection().getX() == 0 && m.getDirection().getY() == 0)) {
			speedVector.setDirection(m.getDirection());
			speedVector.setSpeed(m.getSpeed());
			position.translate((int) speedVector.getDirection().getX()
					* speedVector.getSpeed(), (int) speedVector.getDirection()
					.getY() * speedVector.getSpeed());
			oneStepMoveAddedBehavior();
		}
		else this.spriteManager.reset();
		
			
	}

	public void changeDirection(SpeedVector m) {
		Point direction = m.getDirection();
		this.spriteManager.reset();
		if (direction.getX() == 1)
			this.spriteManager.setType("right");
		else if (direction.getX() == -1)
			this.spriteManager.setType("left");
		else if (direction.getY() == 1)
			this.spriteManager.setType("down");
		else if (direction.getY() == -1)
			this.spriteManager.setType("up");
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub

	}

}