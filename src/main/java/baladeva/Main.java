package baladeva;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import gameframework.base.ObservableValue;
import gameframework.drawing.BackgroundImage;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.GameCanvasDefaultImpl;
import gameframework.drawing.MockGraphics;
import gameframework.game.GameConfiguration;
import gameframework.gui.GameWindow;

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