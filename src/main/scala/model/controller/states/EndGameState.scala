package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents the end game state in the game.
 *
 * @param context The game controller managing the game state.
 */
class EndGameState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the end game.
   *
   * @return True, as this is the end game state.
   */
  override def isEndGame: Boolean = true
}
