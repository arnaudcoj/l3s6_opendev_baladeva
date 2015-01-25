package baladeva.utils;

import gameframework.drawing.GameUniverseViewPortDefaultImpl;
import gameframework.game.GameData;

import java.net.URL;

public class BaladevaUniverseViewPortLevelBoss extends
		GameUniverseViewPortDefaultImpl {

	public BaladevaUniverseViewPortLevelBoss(GameData data) {
		super(data);
	}

	@Override
	protected URL backgroundImage() {
		return backgroundImage("/images/level1/backgroundt.png");
	}
}
