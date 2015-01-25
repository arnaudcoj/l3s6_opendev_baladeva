package baladeva.levels;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.*;
import baladeva.utils.BaladevaUniverseViewPortLevelBoss;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

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
		this.player = new BaladevaPlayer(data, normalizeCell(1), normalizeCell(1));
		this.gameBoard = new BaladevaUniverseViewPortLevelBoss(this.data);
		this.universe.addGameEntity(this.player);
		this.universe.addGameEntity(new BaladevaKnight(data, normalizeCell(6), normalizeCell(18), player));
		this.createLevelWalls();
	}
	
	@Override
	public void run() {
		super.run();
	}

	protected void createLevelWalls() {
		createLeftSideWall();
		createBottomSideWall();
		createRightSideWall();
		createTopSideWall();
	}

	protected int normalizeCell(int n) {
		return n * this.spriteSize;
	}
	
	private void createLeftSideWall() {
		for (int i = 0; i < rows; i++)
			universe.addGameEntity(new BaladevaWall(data, 0, normalizeCell(i)));
	}

	private void createBottomSideWall() {
		for (int i = 0; i < columns; i++)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), normalizeCell(rows - 1)));
	}

	private void createRightSideWall() {
		for (int i = rows; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(columns - 1), normalizeCell(i)));
	}

	private void createTopSideWall() {
		for (int i = columns; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), 0));
	}


}
