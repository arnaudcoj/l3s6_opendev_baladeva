package baladeva.entities.enemies;

import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyRandom;
import gameframework.motion.SpeedVector;

import java.awt.Point;
import java.util.Random;

import baladeva.entities.BaladevaEnemy;
import baladeva.entities.BaladevaPlayer;

public class BaladevaBat extends BaladevaEnemy {

	protected Random rand = new Random();
	protected int nbStep = 0;
	protected DrawableImage img;
	protected GameCanvas canvas;
	protected SpriteManager spriteManager;
	protected SpeedVector speed = SpeedVector.createNullVector();
	protected BaladevaPlayer player;
	protected int spriteSize;

	@Deprecated
	public BaladevaBat(GameData data, int x, int y) {
		this(data, new Point(x, y), null);
		this.hitPoints = 1;
	}
	
	public BaladevaBat(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 1;
	}
	
	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyRandom();
	}
	
	@Override
	protected String imageStr() {
		return "/images/level1/red_bat.png";
	}

}
