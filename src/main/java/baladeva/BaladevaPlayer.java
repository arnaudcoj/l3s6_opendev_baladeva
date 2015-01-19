package baladeva;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.GameMovableDriverDefaultImpl;
import gameframework.motion.MoveStrategyKeyboard;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class BaladevaPlayer extends GameMovable implements GameEntity, Drawable {

	protected DrawableImage img;
	protected GameCanvas canvas;
	
	public BaladevaPlayer(GameData data, int x, int y) {
		super();
		this.canvas = data.getCanvas();
		this.img = new DrawableImage("/images/level1/Eva.png",
				this.canvas);
		this.setPosition(new Point(x, y));
		MoveStrategyKeyboard keyboard = new MoveStrategyKeyboard();
		GameMovableDriverDefaultImpl moveDriver = new GameMovableDriverDefaultImpl();
		moveDriver.setStrategy(keyboard);
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		data.getCanvas().addKeyListener(keyboard);
		setDriver(moveDriver);
	}

	@Override
	public void draw(Graphics g) {
		// ugly
		this.canvas.drawImage(g, this.img.getImage(), this.position.x, this.position.y);
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.img.getWidth(), this.img.getWidth());
		rectangle.setLocation(position.x, position.y);
		return rectangle;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub

	}

}
