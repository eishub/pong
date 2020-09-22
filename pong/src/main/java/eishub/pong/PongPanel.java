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

/**
 * The game panel. paints current game status and handles key presses.
 */
public class PongPanel extends JPanel {
	// beware of bug JDK=8167263 if you want to change these.
	private static final int DOWN1_KEY = KeyEvent.VK_DOWN;
	private static final int UP1_KEY = KeyEvent.VK_UP;
	private static final int DOWN2_KEY = KeyEvent.VK_Q;
	private static final int UP2_KEY = KeyEvent.VK_1;
	private static final long serialVersionUID = 6647966072683911536L;
	private static final Font scoreFont = new Font("Tahoma", Font.PLAIN, 35);
	private static final Font vsFont = new Font("Tahoma", Font.PLAIN, 20);
	private static final float[] dash1 = { 10.0f };
	private static final BasicStroke dashed = new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
			dash1, 0.0f);
	private final Pong game;

	/**
	 * Pong Panel constructor.
	 *
	 * @param game the game instance
	 */
	public PongPanel(final Pong game) {
		setBackground(Color.WHITE);
		this.game = game;
		initializeKeyBindings();
		setFocusable(true);
	}

	/**
	 * Initializer for all key bindings.
	 */
	private void initializeKeyBindings() {

		final InputMap inpMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		/**
		 * General Functionality.
		 */
		final Action reset = new AbstractAction() {
			private static final long serialVersionUID = 6535;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.resetGame();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "Reset");
		getActionMap().put("Reset", reset);

		/**
		 * PLAYER 1.
		 */
		final Action up = new AbstractAction() {
			private static final long serialVersionUID = 6535;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(1).moveUp();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(UP1_KEY, 0, false), "UP");
		getActionMap().put("UP", up);

		final Action down = new AbstractAction() {
			private static final long serialVersionUID = 12342L;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(1).moveDown();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(DOWN1_KEY, 0, false), "DOWN");
		getActionMap().put("DOWN", down);

		final Action stop = new AbstractAction() {
			private static final long serialVersionUID = 4256;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(1).moveStop();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(UP1_KEY, 0, true), "UPS");
		getActionMap().put("UPS", stop);
		inpMap.put(KeyStroke.getKeyStroke(DOWN1_KEY, 0, true), "DOWNS");
		getActionMap().put("DOWNS", stop);

		/**
		 * PLAYER 2.
		 */
		final Action up2 = new AbstractAction() {
			private static final long serialVersionUID = 46737;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(2).moveUp();
			}
		};

		inpMap.put(KeyStroke.getKeyStroke(UP2_KEY, 0, false), "UP2");
		getActionMap().put("UP2", up2);

		final Action down2 = new AbstractAction() {
			private static final long serialVersionUID = 784876;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(2).moveDown();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(DOWN2_KEY, 0, false), "DOWN2");
		getActionMap().put("DOWN2", down2);

		final Action stop2 = new AbstractAction() {
			private static final long serialVersionUID = 566985;

			@Override
			public void actionPerformed(final ActionEvent event) {
				PongPanel.this.game.getPlayer(2).moveStop();
			}
		};
		inpMap.put(KeyStroke.getKeyStroke(UP2_KEY, 0, true), "UPS2");
		getActionMap().put("UPS2", stop2);
		inpMap.put(KeyStroke.getKeyStroke(DOWN2_KEY, 0, true), "DOWNS2");
		getActionMap().put("DOWNS2", stop2);

	}

	/**
	 * General Paint function - called by JAVA.
	 */
	@Override
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);

		// Paint all layered elements
		paintBackground(graphics);
		paintUi(graphics);
		paintGameElements(graphics);

	}

	/**
	 * Paint the background.
	 *
	 * @param graphics Graphics Object
	 */
	public void paintBackground(final Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.game.getWidth(), 3); // Top
		graphics.fillRect(0, this.game.getHeight() - Pong.BORDER_CORRECTION, this.game.getWidth(), 3); // Bottom
		graphics.fillRect(0, 0, 3, this.game.getHeight()); // Left
		graphics.fillRect(this.game.getWidth() - 9, 0, 3, this.game.getHeight()); // Right

		if (this.game.getGameState()) {
			final Stroke original = ((Graphics2D) graphics).getStroke();
			((Graphics2D) graphics).setStroke(dashed);
			graphics.drawLine(this.game.getWidth() / 2 - 4, 0, this.game.getWidth() / 2 - 4, this.game.getHeight());
			((Graphics2D) graphics).setStroke(original);
		}
	}

	/**
	 * Paint the UI.
	 *
	 * @param graphics Graphics Object
	 */
	public void paintUi(final Graphics graphics) {
		// Draw scorebox
		graphics.setColor(new Color(236, 236, 236));
		graphics.fillRect(this.game.getWidth() / 2 - Pong.BORDER_CORRECTION - 38, 3, 100, 47);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(this.game.getWidth() / 2 - Pong.BORDER_CORRECTION - 38, 2, 100, 48);

		// Enable anti aliasing
		((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

		// Draw score
		graphics.setFont(scoreFont);
		graphics.setColor(new Color(0, 145, 206));
		graphics.drawString(this.game.getScore(1) + "", this.game.getWidth() / 2 - Pong.BORDER_CORRECTION - 28, 39);
		graphics.drawString(this.game.getScore(2) + "", this.game.getWidth() / 2 - Pong.BORDER_CORRECTION + 33, 39);

		// Draw VS
		graphics.setFont(vsFont);
		graphics.setColor(new Color(206, 24, 0));
		graphics.drawString("vs", this.game.getWidth() / 2 - Pong.BORDER_CORRECTION + 3, 39);
	}

	/**
	 * Draw game elements.
	 *
	 * @param graphics Graphics Object
	 */
	public void paintGameElements(final Graphics graphics) {
		// Draw players
		this.game.getPlayer(1).paint(graphics);
		this.game.getPlayer(2).paint(graphics);

		// Draw state
		if (!this.game.getGameState()) {
			graphics.setFont(scoreFont);
			graphics.setColor(new Color(0, 145, 206));
			int won;
			if (this.game.getScore(1) > this.game.getScore(2)) {
				won = 1;
			} else {
				won = 2;
			}
			graphics.drawString("Player " + won + " WON!", this.game.getWidth() / 2 - Pong.BORDER_CORRECTION - 95,
					this.game.getHeight() / 2);
			graphics.setFont(vsFont);
			graphics.drawString("press 'r' to reset", this.game.getWidth() / 2 - Pong.BORDER_CORRECTION - 51,
					this.game.getHeight() / 2 + 25);
		} else {
			// Draw ball
			graphics.setColor(Color.BLACK);
			this.game.getBall().paint(graphics);
		}
	}
}