package baladeva.entities.enemies;

import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;

import java.awt.Point;

import baladeva.entities.BaladevaEnemy;

public class BaladevaRed extends BaladevaEnemy {
	
	public BaladevaRed(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 7;
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
