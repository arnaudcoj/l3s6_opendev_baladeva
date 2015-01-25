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

	protected abstract MoveStrategy getMoveStrategy(Point pos, Point goal);

	protected abstract String imageStr();

	protected void initMotion(GameData data, Point goal) {
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(this.getMoveStrategy(this.position, goal));
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		setDriver(moveDriver);
	}

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
	 * Maybe to change according to the attacks
	 */
	public void initSpriteManager() {
		this.spriteManager.setTypes("down", "left", "right", "up");
		this.spriteManager.setType("down");
		this.spriteManager.reset();
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		this.changeDirection(this.getSpeedVector());
	}

	@Override
	public void draw(Graphics g) {
		if (this.frameInvulnerability % 4 == 0)
			this.spriteManager.draw(g, position);
		this.decrementFrameInvulnerability();

		this.spriteManager.increment();
	}

	private void decrementFrameInvulnerability() {
		if (this.invincible())
			this.frameInvulnerability--;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.spriteSize, this.spriteSize);
		rectangle.setLocation(position.x * this.spriteSize, position.y
				* this.spriteSize);
		return rectangle;
	}

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

	public void getHit() {
		if (hitPoints > 0) {
			hitPoints--;
			this.frameInvulnerability = 20;
		}
		if (hitPoints == 0) {
			this.data.getUniverse().removeGameEntity(this);
			this.data.getScore().setValue(
					this.data.getScore().getValue() + this.scorePoints);
			// trying to end the level. Not sure it's the way to do
			 int i = 0;
			 Iterator<GameEntity> it =
			 this.data.getUniverse().getGameEntitiesIterator();
			 BaladevaPlayer player = null;
			
			 while(it.hasNext()) {
			 GameEntity tmp = it.next();
			 if (tmp instanceof BaladevaEnemy) i++;
			 if (tmp instanceof BaladevaPlayer) player = (BaladevaPlayer) tmp;
			 }
			 if (i == 0) {
			 this.data.getUniverse().removeGameEntity(player);
			 this.data.getEndOfGame().setValue(true);
			 }
		}
	}

	public boolean invincible() {
		return frameInvulnerability > 0;
	}

}
