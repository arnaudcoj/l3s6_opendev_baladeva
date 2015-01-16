package baladeva;

import gameframework.game.GameData;
import gameframework.game.GameUniverseDefaultImpl;

public class BaladevaUniverseLevel1 extends GameUniverseDefaultImpl {
	
	public BaladevaUniverseLevel1(GameData data) {
		super();
		this.setGameData(data);
		this.data.getUniverse().addGameEntity(new BaladevaPlayer(this.data, 2, 2));
	}
	
}
