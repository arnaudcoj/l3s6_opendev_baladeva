package baladeva.levels;

import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;

import java.awt.Point;
import java.util.Iterator;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.BaladevaKnight;
import baladeva.utils.BaladevaUniverseViewPortLevelBoss;

public class BaladevaLevelBoss extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;

	protected BaladevaPlayer player;

	public BaladevaLevelBoss(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	protected void init() {
		Iterator<GameEntity> it = this.data.getUniverse()
				.getGameEntitiesIterator();
		GameEntity tmp;
		while (it.hasNext())
			if ((tmp = it.next()) instanceof BaladevaPlayer)
				player = (BaladevaPlayer) tmp;
		this.gameBoard = new BaladevaUniverseViewPortLevelBoss(this.data);
		this.universe.addGameEntity(new BaladevaKnight(data, new Point(
				normalizeCell(6), normalizeCell(6)), player.getPosition()));
	}

	@Override
	public void run() {
		super.run();
	}

	protected int normalizeCell(int n) {
		return n * this.spriteSize;
	}

}
