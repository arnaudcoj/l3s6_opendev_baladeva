/**
 * 
 */
package baladeva;

import gameframework.game.Game;
import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;
import gameframework.gui.GameStatusBarElement;
import gameframework.gui.GameWindow;

import java.net.MalformedURLException;

import baladeva.levels.BaladevaCongratulations;
import baladeva.levels.BaladevaLevel1;
import baladeva.levels.BaladevaLevel2;
import baladeva.levels.BaladevaLevelBoss;
import baladeva.utils.BaladevaConfiguration;

/**
 * Baladeva main class is a Game. It manage the differents level and their display using the class needed.
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT Antoine  
 *
 */
public class Baladeva extends GameDefaultImpl {

	public Baladeva(GameData data) {
		super(data);
		this.initLevels();
	}

	/**
	 * Add all the levels on the game list
	 */
	public void initLevels() {
		this.data.addLevel(new BaladevaLevel1(data));
		this.data.addLevel(new BaladevaLevel2(data));
		this.data.addLevel(new BaladevaLevelBoss(data));
		this.data.addLevel(new BaladevaCongratulations(data));
	}

	/**
	 * @param args not use here 
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		BaladevaConfiguration configuration = new BaladevaConfiguration(20, 40,
				32, 8);
		GameData data = new GameData(configuration);
		Game baladeva = new Baladeva(data);
		GameStatusBarElement<Integer> score = new GameStatusBarElement<Integer>(
				"Score : ", data.getScore());
		GameStatusBarElement<Integer> life = new GameStatusBarElement<Integer>(
				"Life : ", data.getLife());

		GameWindow w = new GameWindow("Baladeva", data.getCanvas(),
				configuration, life, score);
		w.createGUI();
		baladeva.start();
	}
}
