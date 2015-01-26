package baladeva.levels;

import baladeva.utils.BaladevaUniverseViewPortCongratulations;
import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaCongratulations extends GameLevelDefaultImpl {

	public BaladevaCongratulations(GameData data) {
		super(data, 30);
	}

	@Override
	protected void init() {
		this.gameBoard = new BaladevaUniverseViewPortCongratulations(this.data);
		data.getEndOfGame().setValue(true);
	}
}
