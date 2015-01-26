package baladeva.levels;

import java.util.Random;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.BaladevaBat;
import baladeva.entities.enemies.BaladevaWolf;
import baladeva.utils.BaladevaUniverseViewPortLevel1;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaLevel1 extends GameLevelDefaultImpl {

	protected int rows;
	protected int columns;
	protected int spriteSize;

	protected BaladevaPlayer player;

	public BaladevaLevel1(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	@Override
	protected void init() {
		this.player = new BaladevaPlayer(data, normalizeCell(1),
				normalizeCell(1));
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.universe.addGameEntity(this.player);
		this.spawnEnnemies();
		// test
		// this.universe.addGameEntity(new BaladevaWolf(data, normalizeCell(6),
		// normalizeCell(18), player));
		this.createLevelWalls();
	}

	private void spawnEnnemies() {
		Random dice = new Random();
		for (int i = 0; i < /*dice.nextInt(5) + 5*/2; i++) {
			this.universe.addGameEntity(new BaladevaBat(data,
					normalizeCell(dice.nextInt(columns - 10) + 5),
					normalizeCell(dice.nextInt(rows - 10) + 5)));
		}
	}

	private void createLevelWalls() {
		createLeftSideWall();
		createBottomSideWall();
		createRightSideWall();
		createTopSideWall();
	}

	private int normalizeCell(int n) {
		return n * this.spriteSize;
	}

	private void createLeftSideWall() {
		for (int i = 0; i < rows; i++)
			universe.addGameEntity(new BaladevaWall(data, 0, normalizeCell(i)));
	}

	private void createBottomSideWall() {
		for (int i = 0; i < columns; i++)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i),
					normalizeCell(rows - 1)));
	}

	private void createRightSideWall() {
		for (int i = rows; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data,
					normalizeCell(columns - 1), normalizeCell(i)));
	}

	private void createTopSideWall() {
		for (int i = columns; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), 0));
	}

}
