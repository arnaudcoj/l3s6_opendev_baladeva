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

/**
 * BaladevaBat represent the enemy who looks like a bat. Its moveStrategy is a RandomStrategy in order to
 * look like a bat's flight.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
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
		this.scorePoints = 500;
	}
	
	/**
	 * Create an enemy bat
	 * @param data the data of the current game
	 * @param pos the position where the enemy will be at the start of the game 
	 * @param goal the goal of the enemy
	 */
	public BaladevaBat(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 1;
		this.scorePoints = 500;
	}
	
	/**
	 * Return the motion strategy of the bat
	 * @param pos the initial position. Not use here. 
	 * @param goal the goal. Not use here.
	 * @return A moveStrategyRandom 
	 */
	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyRandom();
	}
	
	
	@Override
	protected String imageStr() {
		return "/images/level1/red_bat.png";
	}

}
