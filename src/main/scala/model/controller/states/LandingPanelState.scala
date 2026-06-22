package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state related to the landing panel.
 *
 * @param context The game controller managing the game state.
 */
class LandingPanelState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the landing panel.
   *
   * @return True, as this is the landing panel state.
   */
  override def isLandingPanel: Boolean = true

  /**
   * Transitions to the EndTurnState to signal the end of the current turn.
   */
  override def goEndTurn(): Unit = {
    this.setState(new EndTurnState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Activates the landing panel in the game controller.
   * - Transitions to the EndTurnState to signify the end of the current turn.
   */
  override def doAction(): Unit = {
    context.landingPanel()
    context.goEndTurn()
  }
}
