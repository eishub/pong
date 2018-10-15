package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class PongPanel extends JPanel {
  private static final long serialVersionUID = 6647966072683911536L;
  private final Font scoreFont = new Font("Tahoma", Font.PLAIN, 35);
  private final Font vsFont = new Font("Tahoma", Font.PLAIN, 20);
  private final float[] dash1 = { 10.0f };
  private final BasicStroke dashed = new BasicStroke(5.0f,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
  private Pong game;

  /**
   * Pong Panel constructor.
   * 
   * @param game
   *          the game instance
   */
  public PongPanel(Pong game) {
    setBackground(Color.WHITE);
    this.game = game;
    initializeKeyBindings();
    setFocusable(true);
  }

  /**
   * Initializer for all key bindings.
   */
  private void initializeKeyBindings() {

    InputMap inpMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

    /**
     * General Functionality.
     */
    Action reset = new AbstractAction() {
      private static final long serialVersionUID = 6535;

      public void actionPerformed(ActionEvent event) {
        game.resetGame();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "Reset");
    this.getActionMap().put("Reset", reset);

    /**
     * PLAYER 1.
     */
    Action up = new AbstractAction() {
      private static final long serialVersionUID = 6535;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(1).moveUp();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
    this.getActionMap().put("UP", up);
    
    Action down = new AbstractAction() {
      private static final long serialVersionUID = 12342L;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(1).moveDown();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
    this.getActionMap().put("DOWN", down);
    
    Action stop = new AbstractAction() {
      private static final long serialVersionUID = 4256;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(1).moveStop();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "UPS");
    this.getActionMap().put("UPS", stop);
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "DOWNS");
    this.getActionMap().put("DOWNS", stop);

    /**
     * PLAYER 2.
     */
    Action up2 = new AbstractAction() {
      private static final long serialVersionUID = 46737;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(2).moveUp();
      }
    };
   
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "UP2");
    this.getActionMap().put("UP2", up2);
    
    Action down2 = new AbstractAction() {
      private static final long serialVersionUID = 784876;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(2).moveDown();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "DOWN2");
    this.getActionMap().put("DOWN2", down2);
    
    Action stop2 = new AbstractAction() {
      private static final long serialVersionUID = 566985;

      public void actionPerformed(ActionEvent event) {
        game.getPlayer(2).moveStop();
      }
    };
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "UPS2");
    this.getActionMap().put("UPS2", stop2);
    inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "DOWNS2");
    this.getActionMap().put("DOWNS2", stop2);

  }

  /**
   * General Paint function - called by JAVA.
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    // Paint all layered elements
    paintBackground(graphics);
    paintUi(graphics);
    paintGameElements(graphics);

  }

  /**
   * Paint the background.
   * 
   * @param graphics
   *          Graphics Object
   */
  public void paintBackground(Graphics graphics) {
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 0, game.getWidth(), 3); // Top
    graphics.fillRect(0, game.getHeight() - Pong.BORDER_CORRECTION, game.getWidth(), 3); // Bottom
    graphics.fillRect(0, 0, 3, game.getHeight()); // Left
    graphics.fillRect(game.getWidth() - 9, 0, 3, game.getHeight()); // Right

    if (game.getGameState()) {
      Stroke original = ((Graphics2D) graphics).getStroke();
      ((Graphics2D) graphics).setStroke(dashed);
      graphics.drawLine(game.getWidth() / 2 - 4, 0, game.getWidth() / 2 - 4,
          game.getHeight());
      ((Graphics2D) graphics).setStroke(original);
    }
  }

  /**
   * Paint the UI.
   * 
   * @param graphics
   *          Graphics Object
   */
  public void paintUi(Graphics graphics) {
    // Draw scorebox
    graphics.setColor(new Color(236, 236, 236));
    graphics.fillRect(game.getWidth() / 2 - Pong.BORDER_CORRECTION - 38, 3, 100, 47);
    graphics.setColor(Color.BLACK);
    graphics.drawRect(game.getWidth() / 2 - Pong.BORDER_CORRECTION - 38, 2, 100, 48);

    // Enable anti aliasing
    ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

    // Draw score
    graphics.setFont(scoreFont);
    graphics.setColor(new Color(0, 145, 206));
    graphics.drawString(game.getScore(1) + "", game.getWidth() / 2
        - Pong.BORDER_CORRECTION - 28, 39);
    graphics.drawString(game.getScore(2) + "", game.getWidth() / 2
        - Pong.BORDER_CORRECTION + 33, 39);

    // Draw VS
    graphics.setFont(vsFont);
    graphics.setColor(new Color(206, 24, 0));
    graphics.drawString("vs", game.getWidth() / 2 - Pong.BORDER_CORRECTION + 3, 39);
  }

  /**
   * Draw game elements.
   * 
   * @param graphics
   *          Graphics Object
   */
  public void paintGameElements(Graphics graphics) {
    // Draw players
    game.getPlayer(1).paint(graphics);
    game.getPlayer(2).paint(graphics);

    // Draw state
    if (!game.getGameState()) {
      graphics.setFont(scoreFont);
      graphics.setColor(new Color(0, 145, 206));
      int won;
      if (game.getScore(1) > game.getScore(2)) {
        won = 1;
      } else {
        won = 2;
      }
      graphics.drawString("Player " + won + " WON!", game.getWidth() / 2
          - Pong.BORDER_CORRECTION - 95, game.getHeight() / 2);
      graphics.setFont(vsFont);
      graphics.drawString("press 'r' to reset", game.getWidth() / 2
          - Pong.BORDER_CORRECTION - 51, game.getHeight() / 2 + 25);
    } else {
      // Draw ball
      graphics.setColor(Color.BLACK);
      game.getBall().paint(graphics);
    }
  }
}