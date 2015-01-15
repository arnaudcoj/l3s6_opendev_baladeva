/**
 * 
 */
package baladeva;

import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;

/**
 * @author non0w
 *
 */
public class Baladeva extends GameDefaultImpl {

	public Baladeva(GameData data) {
		super(data);
		this.initLevels();
	}

	public void initLevels() {
		this.data.addLevel(new BaladevaLevel1(data));
	}
}
