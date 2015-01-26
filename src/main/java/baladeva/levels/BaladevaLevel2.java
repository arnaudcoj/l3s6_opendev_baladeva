package baladeva.levels;

import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;

import java.awt.Point;
import java.util.Iterator;
import java.util.Random;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.BaladevaWall;
import baladeva.entities.enemies.BaladevaRed;
import baladeva.entities.enemies.BaladevaWolf;
import baladeva.utils.BaladevaUniverseViewPortLevel1;

/**
 * Class Representing the second level of the game. In this level we create some
 * wolf and a human enemy (Red)
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaLevel2 extends GameLevelDefaultImpl {

	// Fields
	protected int rows;
	protected int columns;
	protected int spriteSize;
	protected BaladevaPlayer player;

	// Methods

	/**
	 * Constructor for the BaladevaLevel2
	 * 
	 * @param data
	 *            the Game's datas
	 */
	public BaladevaLevel2(GameData data) {
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
		Iterator<GameEntity> it = this.data.getUniverse()
				.getGameEntitiesIterator();
		GameEntity tmp;
		while (it.hasNext())
			if ((tmp = it.next()) instanceof BaladevaPlayer)
				player = (BaladevaPlayer) tmp;
		player.setPosition(new Point(normalizeCell(1), normalizeCell(1)));
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.spawnEnnemies();
		this.createBlocks();
	}

	/**
	 * Creates the Enemies and put them on the board.
	 */
	private void spawnEnnemies() {
		Random dice = new Random();
		//Creating some wolves
		for (int i = 0; i < 3; i++) {
			this.universe.addGameEntity(new BaladevaWolf(data, new Point(
					normalizeCell(dice.nextInt(columns - 10) + 5),
					normalizeCell(dice.nextInt(rows - 10) + 5)), player
					.getPosition()));
		}
		//Creating Red
		this.universe.addGameEntity(new BaladevaRed(data, new Point(
				normalizeCell(13), normalizeCell(13)), player.getPosition()));

	}

	/**
	 * Creates some blocks and put them on the board
	 */
	private void createBlocks() {
		for (int i = rows - 4; i > 2; i--)
			universe.addGameEntity(new BaladevaWall(data, normalizeCell(3),
					normalizeCell(i)));
		for (int i = rows - 4; i > 2; i--)
			universe.addGameEntity(new BaladevaWall(data,
					normalizeCell(columns - 4), normalizeCell(i)));
	}

	/**
	 * Takes an integer representing a column's or row's number an convert it to
	 * a number of pixels. Use it if you want to put an entity on a cell.
	 * 
	 * @param n
	 *            the number of columns/rows.
	 * @return the number of columns/rows * the sprites' size.
	 */
	protected int normalizeCell(int n) {
		return n * this.spriteSize;
	}
}
