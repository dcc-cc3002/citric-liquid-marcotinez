package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

import cl.uchile.dcc.citric.model.panels.GameBoard

/**
 * Represents a game state indicating that the player is in a moving state.
 *
 * @param context The game controller managing the game state.
 */
class MovingState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the player in a moving state.
   *
   * @return True, as this is the moving state.
   */
  override def isMoving: Boolean = true

  /**
   * Transitions to the LandingPanelState when the player reaches a landing panel.
   */
  override def goLandingPanel(): Unit = {
    context.setState(new LandingPanelState(context))
  }

  /**
   * Transitions to the CombatState when the player encounters an enemy.
   */
  override def goCombat(): Unit = {
    context.setState(new CombatState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Moves the player in the game controller.
   * - If an enemy is encountered, transitions to the CombatState.
   * - If no enemy is encountered, transitions to the LandingPanelState.
   */
  override def doAction(): Unit = {
    context.movePlayer()
    if(context.getEnemy.isDefined){
      goCombat()
    }
    else{
      goLandingPanel()
    }
  }

}
