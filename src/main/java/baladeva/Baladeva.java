/**
 * 
 */
package baladeva;

import java.net.MalformedURLException;

import baladeva.levels.BaladevaLevel1;
import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;
import gameframework.gui.GameWindow;

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
	
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		GameConfiguration configuration = new GameConfiguration(20, 40, 32, 5);
		GameData data = new GameData(configuration);
		Game baladeva = new Baladeva(data);
		GameWindow w = new GameWindow(data.getCanvas(), configuration, data.getScore(), data.getLife());
		w.createGUI();
		baladeva.start();
	}
}
