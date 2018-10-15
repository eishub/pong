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
  private Pong game;
  private int xlocation;
  private int ylocation;
  private int xa = 2;
  private int ya = 2;

  /**
   * Ball constructor, ball starts in the center.
   * @param game the pong game instance.
   */
  public Ball(Pong game) {
    this.game = game;
    xlocation = game.getWidth() / 2;
    ylocation = game.getHeight() / 2;
  }

  /**
   * Return the horizontal position.
   * @return the x location of the ball.
   */
  public int getPositionX() {
    return xlocation;
  }

  /**
   * Return the vertical position.
   * @return the y location of the ball.
   */
  public int getPositionY() {
    return ylocation;
  }

  /**
   * Ball tick frame.
   */
  public void update() {
    xlocation += xa;
    ylocation += ya;
    if (xlocation < 0) {
      game.increaseScore(2);
      xlocation = game.getWidth() / 2;
      xa = -xa;
    } else if (xlocation > game.getWidth() - WIDTH - 7) {
      game.increaseScore(1);
      xlocation = game.getWidth() / 2;
      xa = -xa;
    } else if (ylocation < 0 || ylocation > game.getHeight() - HEIGHT - 29) {
      ya = -ya;
    }

    if (game.getScore(1) == 2 || game.getScore(2) == 2) {
      game.setGameState(false);
    }
    checkCollision();
  }

  /**
   * Check for collisions with a player, if this happens, invert horizontal
   * speed.
   */
  public void checkCollision() {
    if (game.getPlayer(1).getBounds().intersects(getBounds())
        || game.getPlayer(2).getBounds().intersects(getBounds())) {
      xa = -xa;
    }
  }

  /**
   * Return the bounds / rect of the ball object.
   * @return the bounds / rect of the ball object.
   */
  public Rectangle getBounds() {
    return new Rectangle(xlocation, ylocation, WIDTH, HEIGHT);
  }

  /**
   * Paint function of the ball.
   * @param graphics the graphics object.
   */
  public void paint(Graphics graphics) {
    graphics.fillOval(xlocation, ylocation, WIDTH, HEIGHT);
  }
}