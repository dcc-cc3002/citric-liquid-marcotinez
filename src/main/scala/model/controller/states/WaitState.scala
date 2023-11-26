package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class WaitState(context: GameController) extends GameState(context) {
  override def isWait: Boolean = true

  override def evade(): Unit = {
    context.setState(new CombatState(context))
  }
  override def defend(): Unit = {
    context.setState(new CombatState(context))
  }
}
