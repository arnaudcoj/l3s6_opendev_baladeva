package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;

import java.net.URL;

/**
 * Class extending the frameworks' GameUniverseViewPortDefaultImpl. Used in the last level
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaUniverseViewPortLevelBoss extends
		GameUniverseViewPortDefaultImpl {

	/**
	 * Constructor for the BaladevaUniverseViewPortLevelBoss 
	 * @param data the Game's datas
	 */
	public BaladevaUniverseViewPortLevelBoss(GameData data) {
		super(data);
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.drawing.GameUniverseViewPortDefaultImpl#backgroundImage()
	 */
	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/backgroundt.png");
	}
}
