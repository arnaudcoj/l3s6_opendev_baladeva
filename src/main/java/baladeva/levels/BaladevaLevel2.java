package baladeva.levels;

import java.util.Random;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.BaladevaRed;
import baladeva.entities.enemies.BaladevaWolf;
import baladeva.utils.BaladevaUniverseViewPortLevel1;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaLevel2 extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;
	
	protected BaladevaPlayer player;
	
	public BaladevaLevel2(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	@Override
	protected void init() {
		this.player = new BaladevaPlayer(data, normalizeCell(1), normalizeCell(1));
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.universe.addGameEntity(this.player);
		this.spawnEnnemies();
		this.universe.addGameEntity(new BaladevaRed(data, normalizeCell(13), normalizeCell(13), player));
		this.createLevelWalls();
		this.createBlocks();
	}

	private void spawnEnnemies() {
		Random dice = new Random();
		for(int i = 0 ; i < 3 ; i++) {
			if(dice.nextInt(2) == 1);
				this.universe.addGameEntity(new BaladevaWolf(data, normalizeCell(dice.nextInt(columns - 5)), normalizeCell(dice.nextInt(rows - 5)), player));
		}		
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

	private void createBlocks() {
		for (int i = rows - 4; i > 2; i--) universe.addGameEntity(new BaladevaWall(data, normalizeCell(3), normalizeCell(i)));
		for (int i = rows - 4; i > 2; i--) universe.addGameEntity(new BaladevaWall(data, normalizeCell(columns - 4), normalizeCell(i)));
	}
}
