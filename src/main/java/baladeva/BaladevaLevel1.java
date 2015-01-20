package baladeva;

import java.util.Random;

import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaLevel1 extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;
	
	public BaladevaLevel1(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	@Override
	protected void init() {
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);		
		this.universe.addGameEntity(new BaladevaPlayer(data, normalizeCell(1), normalizeCell(1)));
		this.spwanEnnemies();
		this.createLevelWalls();
	}

	private void spwanEnnemies() {
		Random dice = new Random();
		for(int i = 0 ; i < 10 ; i++) {
			if(dice.nextInt(2) == 1)
				this.universe.addGameEntity(new BaladevaEnemy(data, normalizeCell(dice.nextInt(columns - 5)), normalizeCell(dice.nextInt(rows - 5))));
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

	public int normalizeCell(int n) {
		return n * this.spriteSize;
	}
	
	public void createLeftSideWall() {
		for (int i = 0; i < rows; i++)
			universe.addGameEntity(new BaladevaWall(data, 0, normalizeCell(i)));
	}

	public void createBottomSideWall() {
		for (int i = 0; i < columns; i++)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), normalizeCell(rows - 1)));
	}

	public void createRightSideWall() {
		for (int i = rows; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(columns - 1), normalizeCell(i)));
	}

	public void createTopSideWall() {
		for (int i = columns; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), 0));
	}

}
