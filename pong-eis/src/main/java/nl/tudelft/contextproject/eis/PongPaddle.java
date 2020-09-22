package nl.tudelft.contextproject.eis;

import java.util.ArrayList;
import java.util.List;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;
import eishub.pong.Ball;
import eishub.pong.Pong;

public class PongPaddle {
	/**
	 * Controller used to do actions and percepts.
	 */
	private final Pong controller;

	/**
	 * Default constructor.
	 *
	 * @param controller Pong controller
	 */
	public PongPaddle(final Pong controller) {
		this.controller = controller;
	}

	/**
	 * Percepts the position of the ball in the game.
	 *
	 * @return Integer list with x and y coordinates.
	 */
	@AsPercept(name = "ball", multipleArguments = true)
	public List<Integer> ballPosition() {
		final List<Integer> result = new ArrayList<>();
		final Ball ball = this.controller.getBall();
		result.add(ball.getPositionX());
		result.add(ball.getPositionY());
		return result;
	}

	/**
	 * Percepts location of the player paddle.
	 *
	 * @return Paddle y coordinate.
	 */
	@AsPercept(name = "paddle")
	public int paddle() {
		return this.controller.getPlayer(1).getPosition();
	}

	/**
	 * Percepts score of both players.
	 *
	 * @return List of scores.
	 */
	@AsPercept(name = "score", multipleArguments = true)
	public List<Integer> score() {
		final List<Integer> result = new ArrayList<>();
		result.add(this.controller.getScore(1));
		result.add(this.controller.getScore(2));
		return result;
	}

	/**
	 * Moves the paddle in a direction.
	 *
	 * @param direction The direction to move in, either UP or DOWN.
	 */
	@AsAction(name = "move")
	public void movePaddle(final String direction) {
		if (direction.equals("DOWN")) {
			this.controller.getPlayer(1).moveDown();
		}
		if (direction.equals("UP")) {
			this.controller.getPlayer(1).moveUp();
		}
	}

	/**
	 * Stops paddle movement.
	 */
	@AsAction(name = "stop")
	public void stopPaddle() {
		this.controller.getPlayer(1).moveStop();
	}
}
