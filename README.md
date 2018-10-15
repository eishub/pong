<img align="right" src="pong.png" width="50%"/>

# pong
The pong world is a world where two players play pong. In pong, players have to bounce a ball to keep it in the field.

##Player controls
The left racket is controlled with the letters "w" and "s" on the keyboard. The right racket is controlled with the up and down arrows on the keyboard. 

##Actions
There are two possible actions in pong:

* **move(Direction)**: Moves the bat of the computer racket. The parameter can be "UP" or "DOWN". The racket keeps moving in this direction until stop is called.

* **stop**: Stops the player's movement: the bat is stopped from further movement in a previously set direction.

## Percepts
The following percepta are available:
* **ball(Horizontal,Vertical)**: the ball currently is at given Horizontal, Vertical position (both values are integers).

* **score(Left, Right)** The current amount of 'winned rounds' for Right and Left side. A side wins one round if the other side fails to return the ball.

* **paddle(height)** The current position of the racket of the right player.
