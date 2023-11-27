package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class MovingState(context: GameController) extends GameState(context) {
  override def isMoving: Boolean = true

  override def choosePath(): Unit = {
    this.setState(new MovingState(context))
  }
  override def stopMovement(): Unit = {
    this.setState(new CombatState(context))
  }
  override def outOfMovements(): Unit = {
    this.setState(new CombatState(context))
  }

}
