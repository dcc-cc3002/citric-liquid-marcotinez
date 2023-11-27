package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class PlayerTurnState(context: GameController) extends GameState(context) {
  override def isPlayerTurn: Boolean = true

  override def rollDice(): Unit = {
    this.setState(new MovingState(context))
  }
}
