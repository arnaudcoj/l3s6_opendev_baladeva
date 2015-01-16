package baladeva;

import gameframework.game.GameData;
import gameframework.game.GameLevelDefaultImpl;

public class BaladevaLevel1 extends GameLevelDefaultImpl{

	public BaladevaLevel1(GameData data) {
		super(data);
	}

	@Override
	protected void init() {
		this.gameBoard = new BaladevaUniverseViewPortLevel1(this.data);
		this.universe = this.data.getUniverse();
	}

	@Override
	public void run() {
		super.run();
	}

}
