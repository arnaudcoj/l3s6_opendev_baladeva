package baladeva;

import gameframework.base.ObservableValue;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameCanvasDefaultImpl;
import gameframework.game.GameConfiguration;
import gameframework.gui.GameWindow;

import java.net.MalformedURLException;

public class Main {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		GameCanvas gcanvas = new GameCanvasDefaultImpl();
		GameConfiguration gconf = new GameConfiguration();
		ObservableValue<Integer> score = new ObservableValue<Integer>(1200);
		ObservableValue<Integer> life = new ObservableValue<Integer>(12);
		GameWindow window = new GameWindow(gcanvas, gconf, score, life);
		window.createGUI();
		
	}
	
}