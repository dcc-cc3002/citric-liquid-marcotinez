package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state related to combat.
 *
 * @param context The game controller managing the game state.
 */
class CombatState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state is a combat state.
   *
   * @return True, as this is a combat state.
   */
  override def isCombat: Boolean = true

  /**
   * Transitions to the LandingPanelState when exiting combat.
   */
  override def goLandingPanel(): Unit = {
    context.setState(new LandingPanelState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Initiates combat in the game controller.
   * - Transitions to the LandingPanelState after combat.
   */
  override def doAction(): Unit = {
    context.combat()
    context.goLandingPanel()
  }


}
