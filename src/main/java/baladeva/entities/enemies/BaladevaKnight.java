package baladeva.entities.enemies;

import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;

import java.awt.Point;

import baladeva.entities.BaladevaEnemy;

/**
 * BaladevaKnight represent the enemy who looks like a knight. Its moveStrategy is a StraightLineStragy in order to
 * follow the player.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
public class BaladevaKnight extends BaladevaEnemy {
	
	/**
	 * Create an enemy knight.
	 * @param data the data of the current game.
	 * @param pos the position where the enemy will be at the start of the game.
	 * @param goal the goal of the enemy.
	 */
	public BaladevaKnight(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 9;
		this.scorePoints = 2000;
	}	

	@Override
	protected String imageStr() {
		return "/images/level1/Knight.png";
	}

	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyStraightLine(this.position, goal, 5);
	}

}
