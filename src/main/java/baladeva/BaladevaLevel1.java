package baladeva;

import gameframework.game.GameData;
import gameframework.game.GameLevel;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaLevel1 extends GameLevelDefaultImpl implements GameLevel {

	public BaladevaLevel1(GameData data) {
		super(data);
	}

	@Override
	protected void init() {
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.universe = new BaladevaUniverseLevel1(this.data);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
