package baladeva.entities.enemies;

import gameframework.game.GameData;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyStraightLine;

import java.awt.Point;

import baladeva.entities.BaladevaEnemy;

public class BaladevaKnight extends BaladevaEnemy {
	
	public BaladevaKnight(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 1;
		this.scorePoints = 2000;
	}	

	@Override
	protected String imageStr() {
		return "/images/level1/Knight.png";
	}

	@Override
	protected MoveStrategy getMoveStrategy(Point pos, Point goal) {
		return new MoveStrategyStraightLine(this.position, goal, 3);
	}

}
