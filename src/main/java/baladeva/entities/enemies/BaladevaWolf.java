package baladeva.entities.enemies;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;
import gameframework.motion.SpeedVector;

import java.awt.Point;
import java.util.Random;

import baladeva.entities.BaladevaEnemy;
import baladeva.entities.BaladevaPlayer;

public class BaladevaWolf extends BaladevaEnemy {

	protected Random rand = new Random();
	protected int nbStep = 0;
	protected DrawableImage img;
	protected GameCanvas canvas;
	protected SpriteManager spriteManager;
	protected SpeedVector speed = SpeedVector.createNullVector();
	protected int spriteSize;

	@Deprecated
	public BaladevaWolf(GameData data, int x, int y, BaladevaPlayer player) {
		this(data, new Point(x, y), player.getPosition());
	}

	public BaladevaWolf(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
	}	

	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyStraightLine(this.position, goal);
	}

	@Override
	protected String imageStr() {
		return "/images/level1/wolf.png";
	}

}
