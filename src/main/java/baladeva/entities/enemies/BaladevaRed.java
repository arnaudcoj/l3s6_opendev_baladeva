package baladeva.entities.enemies;

import gameframework.game.GameData;
import java.awt.Point;
import baladeva.entities.BaladevaPlayer;

public class BaladevaRed extends BaladevaWolf {

	public BaladevaRed(GameData data, int x, int y, BaladevaPlayer player) {
		super(data, x, y, player);
		this.hitPoints = 12;
		this.scorePoints = 1500;
	}
	
	public BaladevaRed(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 12;
		this.scorePoints = 1500;
	}	

	@Override
	protected String imageStr() {
		return "/images/level1/red.png";
	}

}
