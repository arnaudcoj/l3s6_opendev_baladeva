package baladeva.levels;

import baladeva.utils.BaladevaUniverseViewPortCongratulations;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

/**
 * Class managing the congratulation screen after you beat the game
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaCongratulations extends GameLevelDefaultImpl {

	//Methods
	/**
	 * Constructor for the BaladevaCongratulations Class
	 * @param data the Game's datas
	 */
	public BaladevaCongratulations(GameData data) {
		super(data, 30);
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.game.GameLevelDefaultImpl#init()
	 */
	@Override
	protected void init() {
		this.gameBoard = new BaladevaUniverseViewPortCongratulations(this.data);
		data.getEndOfGame().setValue(true);
	}
}
