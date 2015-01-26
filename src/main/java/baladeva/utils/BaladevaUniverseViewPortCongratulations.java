package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;

import java.net.URL;

public class BaladevaUniverseViewPortCongratulations extends
		GameUniverseViewPortDefaultImpl {

	public BaladevaUniverseViewPortCongratulations(GameData data) {
		super(data);
	}

	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/congratulations.png");
	}

	
}
