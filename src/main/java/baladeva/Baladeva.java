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

import baladeva.levels.*;
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
		this.data.addLevel(new BaladevaLevel2(data));
		this.data.addLevel(new BaladevaLevelBoss(data));
	}
	
	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		BaladevaConfiguration configuration = new BaladevaConfiguration(20, 40, 32, 5);
		GameData data = new GameData(configuration);
		Game baladeva = new Baladeva(data);
		GameStatusBarElement<Integer> score = new GameStatusBarElement<Integer>("Score : ", data.getScore());
		GameStatusBarElement<Integer> life = new GameStatusBarElement<Integer>("Life : ", data.getLife());

		GameWindow w = new GameWindow("Baladeva", data.getCanvas(), configuration, life, score);
		w.createGUI();
		baladeva.start();
	}
}
