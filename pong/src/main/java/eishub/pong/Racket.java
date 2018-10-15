package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Graphics;
import java.awt.Rectangle;

public class Racket {
  private static final int WIDTH = 10;
  private static final int HEIGHT = 60;
  private Pong game;
  private int xlocation;
  private int ylocation;
  private int ya;

  /**
   * Racket constructor.
   * @param game the game instance
   * @param xlocation the required x coordinate
   */
  public Racket(Pong game, int xlocation) {
    this.game = game;
    this.xlocation = xlocation;
    ylocation = game.getHeight() / 2 - HEIGHT / 2;
  }

  /**
   * Update Tick.
   */
  public void update() {
    if (ylocation > 0 && ylocation < game.getHeight() - HEIGHT - 29) {
      ylocation += ya;
    } else if (ylocation <= 0) {
      ylocation++;
    } else if (ylocation >= game.getHeight() - HEIGHT - 29) {
      ylocation--;
    }
  }

  /**
   * Move player 1 unit up.
   */
  public void moveUp() {
    ya = -2;
  }

  /**
   * Move player 1 unit down.
   */
  public void moveDown() {
    ya = 2;
  }

  /**
   * Stop player movement.
   */
  public void moveStop() {
    ya = 0;
  }

  /**
   * This returns the vertical position of the player.
   * @return the y location of the player.
   */
  public int getPosition() {
    return ylocation;
  }

  /**
   * Get Racket bounds.
   * @return the bounds of the racket
   */
  public Rectangle getBounds() {
    return new Rectangle(xlocation, ylocation, WIDTH, HEIGHT);
  }

  /**
   * Paint Racket.
   * @param graphics the Graphics class
   */
  public void paint(Graphics graphics) {
    graphics.fillRect(xlocation, ylocation, WIDTH, HEIGHT);
  }
}