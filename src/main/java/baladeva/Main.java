package baladeva;

import gameframework.game.Game;
import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.gui.GameWindow;

import java.net.MalformedURLException;

public class Main {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		GameConfiguration configuration = new GameConfiguration(30,50,5,5);
		GameData data = new GameData(configuration);
		Game baladeva = new Baladeva(data);
		GameWindow w = new GameWindow(data.getCanvas(), configuration, data.getScore(), data.getLife());
		w.createGUI();
		baladeva.start();
	}
}