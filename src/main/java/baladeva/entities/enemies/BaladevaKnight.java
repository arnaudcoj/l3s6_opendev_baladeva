package baladeva.entities.enemies;

import gameframework.game.GameData;

import java.awt.Point;

import baladeva.entities.BaladevaPlayer;

public class BaladevaKnight extends BaladevaWolf {

	public BaladevaKnight(GameData data, int x, int y, BaladevaPlayer player) {
		super(data, x, y, player);
		this.hitPoints = 18;
		this.scorePoints = 2000;
	}
	
	public BaladevaKnight(GameData data, Point pos, Point goal) {
		super(data, pos, goal);
		this.hitPoints = 18;
		this.scorePoints = 2000;
	}	

	@Override
	protected String imageStr() {
		return "/images/level1/Knight.png";
	}

}
