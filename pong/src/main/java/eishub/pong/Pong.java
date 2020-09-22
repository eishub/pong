package eishub.pong;

/**
 * Pong in JAVA
 * Base source: http://codereview.stackexchange.com/questions/27197/pong-game-in-java
 * Modified for example eis project.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong extends JFrame implements ActionListener {
	private static final long serialVersionUID = 3504739896597314995L;
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	public static final int BORDER_CORRECTION = 16;
	private boolean playingState = false;
	private final PongPanel panel;
	private Timer timer;

	private Ball ball;
	private Racket player1; // RIGHT side
	private Racket player2; // LEFT side.
	private int score1;
	private int score2;

	/**
	 * Pong constructor.
	 */
	public Pong() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setTitle("Pong");
		setBackground(Color.WHITE);
		setResizable(false);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.panel = new PongPanel(this);
		add(this.panel);

		setVisible(true);

		resetGame();
	}

	/**
	 * Reset the game completely.
	 */
	public void resetGame() {

		// Create game elements
		this.ball = new Ball(this);
		this.player1 = new Racket(this, getWidth() - BORDER_CORRECTION - 5);
		this.player2 = new Racket(this, 5);

		// Set playing state + start timer/game
		if (this.timer != null) {
			this.timer.stop();
		}
		this.score1 = 0;
		this.score2 = 0;
		this.playingState = true;
		this.timer = new Timer(5, this);
		this.timer.start();
	}

	/**
	 * Returns a player instance.
	 *
	 * @param playerNo The player number.
	 * @return the player with that num
	 */
	public Racket getPlayer(final int playerNo) {
		if (playerNo == 1) {
			return this.player1;
		} else {
			return this.player2;
		}
	}

	/**
	 * Returns the ball instance.
	 *
	 * @return the ball instance.
	 */
	public Ball getBall() {
		return this.ball;
	}

	/**
	 * Increase the score for a given player number.
	 *
	 * @param playerNo The player number.
	 */
	public void increaseScore(final int playerNo) {
		if (playerNo == 1) {
			this.score1++;
		} else {
			this.score2++;
		}
	}

	/**
	 * Returns the score for a given player number.
	 *
	 * @param playerNo The player number.
	 * @return the score of the player.
	 */
	public int getScore(final int playerNo) {
		if (playerNo == 1) {
			return this.score1;
		} else {
			return this.score2;
		}
	}

	/**
	 * Set the current game state.
	 *
	 * @param newState the state to set the game to
	 */
	public void setGameState(final boolean newState) {
		this.playingState = newState;

		if (newState == false) {
			this.timer.stop();
		}
	}

	/**
	 * Return the current game state.
	 *
	 * @return current game state
	 */
	public boolean getGameState() {
		return this.playingState;
	}

	/**
	 * Gets called to update game mechanisms by timer.
	 */
	private void update() {
		getBall().update();
		getPlayer(1).update();
		getPlayer(2).update();
	}

	/**
	 * Callback for java on an action (timer).
	 */
	@Override
	public void actionPerformed(final ActionEvent arg0) {
		update();
		this.panel.repaint();
	}

	/**
	 * Close the game
	 */
	public void close() {
		if (this.timer != null) {
			this.timer.stop();
			this.timer = null;
		}
		setVisible(false);
	}

	/**
	 * Main Program Launcher.
	 *
	 * @param args the arguments specified for the application launch
	 */
	public static void main(final String[] args) {
		new Pong();
	}
}