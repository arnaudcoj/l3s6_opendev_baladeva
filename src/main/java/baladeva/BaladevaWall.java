package baladeva;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.blocking.MoveBlocker;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class BaladevaWall implements GameEntity, MoveBlocker, Drawable {

	protected DrawableImage img;
	protected GameCanvas canvas;
	protected Point position;
	
	public BaladevaWall(GameData data, int x, int y) {
		this.canvas = data.getCanvas();
		this.img = new DrawableImage("/images/level1/wall.png",
				this.canvas);
		this.position = new Point(x,y);
	}
	
	@Override
	public Rectangle getBoundingBox() {
		Rectangle rectangle = new Rectangle(this.img.getWidth(), this.img.getWidth());
		rectangle.setLocation(position.x, position.y);
		return rectangle;
	}

	@Override
	public void draw(Graphics g) {
		this.canvas.drawImage(g, this.img.getImage(), this.position.x, this.position.y);
	}

}
