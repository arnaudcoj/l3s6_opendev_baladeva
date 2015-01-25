/**
 * 
 */
package baladeva;

import gameframework.game.Game;
import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;
import gameframework.gui.GameStatusBarElement;
import gameframework.gui.GameWindow;

import java.io.IOException;
import java.net.MalformedURLException;

import baladeva.levels.BaladevaLevel1;
import baladeva.utils.BaladevaConfiguration;

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
		BaladevaConfiguration configuration = new BaladevaConfiguration(20, 40, 32, 1);
		GameData data = new GameData(configuration);
		Game baladeva = new Baladeva(data);
		GameWindow w = new GameWindow("Baladeva", data.getCanvas() , configuration, new GameStatusBarElement<>("Score:", data.getScore()),new GameStatusBarElement<>("Life:", data.getLife()));
		w.createGUI();
		baladeva.start();
	}
}
