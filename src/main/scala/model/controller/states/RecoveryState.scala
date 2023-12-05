package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state during the recovery phase.
 *
 * @param context The game controller managing the game state.
 */
class RecoveryState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the recovery phase.
   *
   * @return True, as this is the recovery state.
   */
  override def isRecovery: Boolean = true

  /**
   * Transitions to the PlayerTurnState to resume normal gameplay after recovery.
   */
  override def goPlayTurn(): Unit = {
    context.setState(new PlayerTurnState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Initiates the recovery process in the game controller.
   * - If the player is still in combat after recovery, sets the recovery flag and transitions to the PlayerTurnState.
   */
  override def doAction(): Unit = {
    context.tryRecovery()
    if(context.getPlayerTurn.enCombate) {
      context.setRecoveryPlayer(true)
      context.goPlayTurn()
    }
  }
}
