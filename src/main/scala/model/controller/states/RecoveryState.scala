package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class RecoveryState(context: GameController) extends GameState(context) {
  override def isRecovery: Boolean = true

  override def insuficientRoll(): Unit = {
    this.setState(new ChapterState(context))
  }
  override def suficientRoll(): Unit = {
    this.setState(new PlayerTurnState(context))
  }
}
