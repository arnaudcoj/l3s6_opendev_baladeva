package baladeva.levels;

import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

import java.awt.Point;
import java.util.Random;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.BaladevaBat;
import baladeva.entities.enemies.BaladevaWolf;
import baladeva.utils.BaladevaUniverseViewPortLevel1;

/**
 * Class Representing the first level of the game. In this level we create the
 * side walls, a wolf and some bats
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaLevel1 extends GameLevelDefaultImpl {

	// Fields
	protected int rows;
	protected int columns;
	protected int spriteSize;
	protected BaladevaPlayer player;

	// Methods
	/**
	 * The constructor for the BaladevaLevel1
	 * 
	 * @param data
	 *            the Game's datas
	 */
	public BaladevaLevel1(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameframework.game.GameLevelDefaultImpl#init()
	 */
	@Override
	protected void init() {
		this.player = new BaladevaPlayer(data, normalizeCell(1),
				normalizeCell(1));
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.universe.addGameEntity(this.player);
		this.spawnEnemies();
		this.createLevelWalls();
	}

	/**
	 * Takes an integer representing a column's or row's number an convert it to
	 * a number of pixels. Use it if you want to put an entity on a cell.
	 * 
	 * @param n the number of columns/rows.
	 * @return the number of columns/rows * the sprites' size.
	 */
	protected int normalizeCell(int n) {
		return n * this.spriteSize;
	}

	/**
	 * Creates the Enemies and put them on the board.
	 */
	private void spawnEnemies() {
		Random dice = new Random();
		// Adding the wolf
		this.universe.addGameEntity(new BaladevaWolf(data, new Point(
				normalizeCell(dice.nextInt(columns - 10) + 5),
				normalizeCell(dice.nextInt(rows - 10) + 5)), player
				.getPosition()));
		// Adding some bats
		for (int i = 0; i < dice.nextInt(5) + 5; i++) {
			this.universe.addGameEntity(new BaladevaBat(data, new Point(
					normalizeCell(dice.nextInt(columns - 10) + 5),
					normalizeCell(dice.nextInt(rows - 10) + 5)), this.player
					.getPosition()));
		}
	}

	/**
	 * Creates the side Walls and put them on the board.
	 * (Makes a call to create<Left/Bottom/Right/Top>SideWall)
	 */
	private void createLevelWalls() {
		createLeftSideWall();
		createBottomSideWall();
		createRightSideWall();
		createTopSideWall();
	}

	/**
	 * Creates the left wall and put it on the board
	 */
	private void createLeftSideWall() {
		for (int i = 0; i < rows; i++)
			universe.addGameEntity(new BaladevaWall(data, 0, normalizeCell(i)));
	}
	
	/**
	 * Creates the bottom wall and put it on the board
	 */
	private void createBottomSideWall() {
		for (int i = 0; i < columns; i++)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i),
					normalizeCell(rows - 1)));
	}
	
	/**
	 * Creates the right wall and put it on the board
	 */
	private void createRightSideWall() {
		for (int i = rows; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data,
					normalizeCell(columns - 1), normalizeCell(i)));
	}

	/**
	 * Creates the top wall and put it on the board
	 */
	private void createTopSideWall() {
		for (int i = columns; i > 0; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(i), 0));
	}

}
