package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;

import java.net.URL;

/**
 * Class extending the frameworks' GameUniverseViewPortDefaultImpl. Used in the levels 1 & 2.
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaUniverseViewPortLevel1 extends
		GameUniverseViewPortDefaultImpl {

	/**
	 * Constructor for the BaladevaUniverseViewPortLevel1 
	 * @param data the Game's datas
	 */
	public BaladevaUniverseViewPortLevel1(GameData data) {
		super(data);
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.drawing.GameUniverseViewPortDefaultImpl#backgroundImage()
	 */
	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/background.png");
	}
}
