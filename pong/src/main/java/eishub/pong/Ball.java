package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	private static final int WIDTH = 15;
	private static final int HEIGHT = 15;
	private final Pong game;
	private int xlocation;
	private int ylocation;
	private int xa = 2;
	private int ya = 2;

	/**
	 * Ball constructor, ball starts in the center.
	 *
	 * @param game the pong game instance.
	 */
	public Ball(final Pong game) {
		this.game = game;
		this.xlocation = game.getWidth() / 2;
		this.ylocation = game.getHeight() / 2;
	}

	/**
	 * Return the horizontal position.
	 *
	 * @return the x location of the ball.
	 */
	public int getPositionX() {
		return this.xlocation;
	}

	/**
	 * Return the vertical position.
	 *
	 * @return the y location of the ball.
	 */
	public int getPositionY() {
		return this.ylocation;
	}

	/**
	 * Ball tick frame.
	 */
	public void update() {
		this.xlocation += this.xa;
		this.ylocation += this.ya;
		if (this.xlocation < 0) {
			this.game.increaseScore(2);
			this.xlocation = this.game.getWidth() / 2;
			this.xa = -this.xa;
		} else if (this.xlocation > this.game.getWidth() - WIDTH - 7) {
			this.game.increaseScore(1);
			this.xlocation = this.game.getWidth() / 2;
			this.xa = -this.xa;
		} else if (this.ylocation < 0 || this.ylocation > this.game.getHeight() - HEIGHT - 29) {
			this.ya = -this.ya;
		}

		if (this.game.getScore(1) == 2 || this.game.getScore(2) == 2) {
			this.game.setGameState(false);
		}
		checkCollision();
	}

	/**
	 * Check for collisions with a player, if this happens, invert horizontal speed.
	 */
	public void checkCollision() {
		// don't just reverse direction, it may cause ball to 'lock up' when on top of
		// bat.
		if (this.game.getPlayer(1).getBounds().intersects(getBounds())) {
			this.xa = -1; // right player hits to the left
		} else if (this.game.getPlayer(2).getBounds().intersects(getBounds())) {
			this.xa = 1; // left player hits to the right
		}
	}

	/**
	 * Return the bounds / rect of the ball object.
	 *
	 * @return the bounds / rect of the ball object.
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.xlocation, this.ylocation, WIDTH, HEIGHT);
	}

	/**
	 * Paint function of the ball.
	 *
	 * @param graphics the graphics object.
	 */
	public void paint(final Graphics graphics) {
		graphics.fillOval(this.xlocation, this.ylocation, WIDTH, HEIGHT);
	}
}