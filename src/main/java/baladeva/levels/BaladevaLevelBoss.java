package baladeva.levels;

import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.game.GameLevelDefaultImpl;

import java.awt.Point;
import java.util.Iterator;

import baladeva.entities.BaladevaPlayer;
import baladeva.entities.enemies.BaladevaKnight;
import baladeva.utils.BaladevaUniverseViewPortLevelBoss;

/**
 * Class Representing the last level of the game. In this level we create a knight boss
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaLevelBoss extends GameLevelDefaultImpl {

	//Fields
	protected int rows;
	protected int columns;
	protected int spriteSize;
	protected BaladevaPlayer player;

	//Methods
	
	/**
	 * Constructor for the BaladevaLevelBoss
	 * @param data the Game's datas
	 */
	public BaladevaLevelBoss(GameData data) {
		super(data, 30);
		this.rows = this.data.getConfiguration().getNbRows();
		this.columns = this.data.getConfiguration().getNbColumns();
		this.spriteSize = this.data.getConfiguration().getSpriteSize();
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.game.GameLevelDefaultImpl#init()
	 */
	protected void init() {
		Iterator<GameEntity> it = this.data.getUniverse()
				.getGameEntitiesIterator();
		GameEntity tmp;
		while (it.hasNext())
			if ((tmp = it.next()) instanceof BaladevaPlayer)
				player = (BaladevaPlayer) tmp;
		player.setPosition(new Point(normalizeCell(1),normalizeCell(1)));
		this.gameBoard = new BaladevaUniverseViewPortLevelBoss(this.data);
		this.spawnEnemies();
	}

	/**
	 * Creates the Boss and put it on the board.
	 */
	protected void spawnEnemies() {
		//Creating the knight boss
		this.universe.addGameEntity(new BaladevaKnight(data, new Point(
				normalizeCell(6), normalizeCell(6)), player.getPosition()));		
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
