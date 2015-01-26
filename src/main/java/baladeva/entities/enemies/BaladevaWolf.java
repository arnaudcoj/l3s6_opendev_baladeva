package baladeva.entities.enemies;

import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManager;
import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;
import gameframework.motion.SpeedVector;

import java.awt.Point;
import java.util.Random;

import baladeva.entities.BaladevaEnemy;
import baladeva.entities.BaladevaPlayer;

/**
 * BaladevaWolf represent the enemy who looks like a werewolf. Its moveStrategy is a StraightLineStragy in order to
 * follow the player.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
public class BaladevaWolf extends BaladevaEnemy {

	protected Random rand = new Random();
	protected int nbStep = 3;
	protected int currentNbStep = 0;
	protected DrawableImage img;
	protected GameCanvas canvas;
	protected SpriteManager spriteManager;
	protected SpeedVector speed = SpeedVector.createNullVector();
	protected int spriteSize;

	// Waiting for an update
	protected MoveStrategyStraightLine moveStrat;

	@Deprecated
	public BaladevaWolf(GameData data, int x, int y, BaladevaPlayer player) {
		this(data, new Point(x, y), player.getPosition());
		this.hitPoints = 9;
		this.scorePoints = 1000;
	}

	/**
	 * Create an enemy wolf
	 * @param data the data of the current game
	 * @param pos the position where the enemy will be at the start of the game 
	 * @param goal the goal of the enemy
	 */ 
	public BaladevaWolf(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 9;
		this.scorePoints = 1000;
	}

	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyStraightLine(this.position, goal, 3);

	}

	@Override
	protected String imageStr() {
		return "/images/level1/wolf.png";
	}

}
