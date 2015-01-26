package baladeva.entities.enemies;

import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;

import java.awt.Point;

import baladeva.entities.BaladevaEnemy;

/**
 * BaladevaRed represent the enemy who looks like a human. Its moveStrategy is a StraightLineStragy in order to
 * follow the player.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine
 *
 */
public class BaladevaRed extends BaladevaEnemy {
	
	/**
	 * Create an enemy human.
	 * @param data the data of the current game.
	 * @param pos the position where the enemy will be at the start of the game.
	 * @param goal the goal of the enemy.
	 */
	public BaladevaRed(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 5;
		this.scorePoints = 1500;
	}	

	@Override
	protected String imageStr() {
		return "/images/level1/red.png";
	}

	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyStraightLine(this.position, goal, 5);
	}

}
