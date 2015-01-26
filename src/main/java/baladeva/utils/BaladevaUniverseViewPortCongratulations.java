package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;

import gameframework.game.GameData;

import java.net.URL;

/**
 * Class extending the frameworks' GameUniverseViewPortDefaultImpl. Used in the congratulations screen.
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaUniverseViewPortCongratulations extends
		GameUniverseViewPortDefaultImpl {

	/**
	 * Constructor for the BaladevaUniverseViewPortCongratulations
	 * @param data the Game's datas
	 */
	public BaladevaUniverseViewPortCongratulations(GameData data) {
		super(data);
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.drawing.GameUniverseViewPortDefaultImpl#backgroundImage()
	 */
	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/congratulations.png");
	}

	
}
