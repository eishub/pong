package nl.tudelft.contextproject.eis;

import eishub.pong.Ball;
import eishub.pong.Pong;

import eis.eis2java.annotation.AsAction;
import eis.eis2java.annotation.AsPercept;

import java.util.ArrayList;
import java.util.List;

public class PongPaddle {

  /**
   * Controller used to do actions and percepts.
   */
  private Pong controller;

  /** 
   * Default constructor.
   * @param controller Pong controller
   */
  public PongPaddle(Pong controller) {
    this.controller = controller;
  }

  /**
   * Percepts the position of the ball in the game.
   * @return Integer list with x and y coordinates.
   */
  @AsPercept(name = "ball", multipleArguments = true)
  public List<Integer> ballPosition() {
    List<Integer> result = new ArrayList<Integer>();
    Ball ball = controller.getBall();
    result.add(ball.getPositionX());
    result.add(ball.getPositionY());
    return result;
  }

  /**
   * Percepts location of the player paddle.
   * @return Paddle y coordinate.
   */
  @AsPercept(name = "paddle")
  public int paddle() {
    return controller.getPlayer(1).getPosition();
  }
  
  /**
   * Percepts score of both players.
   * @return List of scores.
   */
  @AsPercept(name = "score", multipleArguments = true)
  public List<Integer> score() {
    List<Integer> result = new ArrayList<Integer>();
    result.add(controller.getScore(1));
    result.add(controller.getScore(2));
    return result;
  }

  /**
   * Moves the paddle in a direction.
   * @param direction The direction to move in, either UP or DOWN.
   */
  @AsAction(name = "move")
  public void movePaddle(String direction) {
    if (direction.equals("DOWN")) {
      controller.getPlayer(1).moveDown();
    }
    if (direction.equals("UP")) {
      controller.getPlayer(1).moveUp();
    }
  }
  
  /**
   * Stops paddle movement.
   */
  @AsAction(name = "stop")
  public void stopPaddle() {
    controller.getPlayer(1).moveStop();
  }
}
