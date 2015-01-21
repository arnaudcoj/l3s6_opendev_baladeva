package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;

import java.net.URL;

public class BaladevaUniverseViewPortLevel1 extends
		GameUniverseViewPortDefaultImpl {

	public BaladevaUniverseViewPortLevel1(GameData data) {
		super(data);
	}

	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/background.png");
	}
}
