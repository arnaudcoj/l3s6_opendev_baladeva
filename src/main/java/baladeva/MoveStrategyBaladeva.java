package baladeva;

import gameframework.motion.MoveStrategy;
import gameframework.motion.SpeedVector;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveStrategyBaladeva extends KeyAdapter implements
		MoveStrategy {

	protected SpeedVector speedVector;

	public MoveStrategyBaladeva() {
		this(new SpeedVector(new Point(0, 0)));
	}

	public MoveStrategyBaladeva(SpeedVector speedVector) {
		this.speedVector = speedVector;
	}

	@Override
	public SpeedVector getSpeedVector() {
		return speedVector;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		keyPressed(event.getKeyCode());
	}

	public void keyPressed(int keyCode) {
		int x = speedVector.getDirection().x;
		int y = speedVector.getDirection().y;
		switch (keyCode) {
		case KeyEvent.VK_RIGHT:
			x++;
			break;
		case KeyEvent.VK_LEFT:
			x--;
			break;
		case KeyEvent.VK_UP:
			y--;
			break;
		case KeyEvent.VK_DOWN:
			y++;
			break;
		}
		move(new Point(x, y));

	}

	private void move(Point point) {
		speedVector.setDirection(point);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		keyReleased(event.getKeyCode());
	}

	public void keyReleased(int keyCode) {
	}
}
