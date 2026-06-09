package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class CombatState(context: GameController) extends GameState(context) {
  override def isCombat: Boolean = true

  override def endCombat(): Unit = {
    this.setState(new LandingPanelState(context))
  }
  override def attack(): Unit = {
    this.setState(new WaitState(context))
  }
}
