package nl.tudelft.contextproject.eis;

import java.util.Map;

import eis.eis2java.environment.AbstractEnvironment;
import eis.exceptions.EntityException;
import eis.exceptions.ManagementException;
import eis.iilang.Action;
import eis.iilang.EnvironmentState;
import eis.iilang.Parameter;
import eishub.pong.Pong;

public class PongInterface extends AbstractEnvironment {
	private static final long serialVersionUID = 1L;
	private Pong controller = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see eis.EIDefaultImpl#isSupportedByEnvironment(eis.iilang.Action)
	 */
	@Override
	protected boolean isSupportedByEnvironment(final Action action) {
		return (action.getName().equals("move") && action.getParameters().size() == 1)
				|| (action.getName().equals("stop") && action.getParameters().size() == 0);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eis.EIDefaultImpl#isSupportedByType(eis.iilang.Action, java.lang.String)
	 */
	@Override
	protected boolean isSupportedByType(final Action action, final String type) {
		return isSupportedByEnvironment(action);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eis.EIDefaultImpl#init(java.util.Map)
	 */
	@Override
	public void init(final Map<String, Parameter> parameters) throws ManagementException {
		// Prepare the game.
		reset(parameters);

		// Try creating and registering an entity.
		try {
			registerEntity("pongpaddle", new PongPaddle(this.controller));
		} catch (final EntityException e) {
			throw new ManagementException("Could not create an entity", e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eis.eis2java.environment.AbstractEnvironment#reset(java.util.Map)
	 */
	@Override
	public void reset(final Map<String, Parameter> parameters) throws ManagementException {
		if (this.controller == null) {
			this.controller = new Pong();
		}
		setState(EnvironmentState.PAUSED);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eis.EIDefaultImpl#kill()
	 */
	@Override
	public void kill() throws ManagementException {
		this.controller.close();
		setState(EnvironmentState.KILLED);
	}

	public static void main(final String[] args) {
		Pong.main(args);
	}
}
