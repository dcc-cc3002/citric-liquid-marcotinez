package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state during a player's turn.
 *
 * @param context The game controller managing the game state.
 */
class PlayerTurnState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents a player's turn.
   *
   * @return True, as this is a player turn state.
   */
  override def isPlayerTurn: Boolean = true

  /**
   * Transitions to the MovingState to allow the player to move.
   */
  override def goMoving(): Unit = {
    context.setState(new MovingState(context))
  }

  /**
   * Transitions to the RecoveryState for the player's recovery phase.
   */
  override def goRecovery(): Unit = {
    context.setState(new RecoveryState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - If it's the turn of a player returning from recovery, transitions to the MovingState.
   * - If it's the turn of the next player, initiates a new turn:
   *   - If the player enCombate = True, transitions to the MovingState.
   *   - Else transitions to the RecoveryState.
   */
  override def doAction(): Unit = {
    //caso cuando es el turno de un jugador que vuelve del recovery
    if(context.getRecoveryPlayer) {
      context.setRecoveryPlayer(false)
      context.goMoving()
    }
    //caso cuando le toca al siguiente player
    else{
      context.newTurn()
      if (context.getPlayerTurn.enCombate) {
        context.goMoving()
      }
      else {
        context.goRecovery()
      }
    }
  }
}
