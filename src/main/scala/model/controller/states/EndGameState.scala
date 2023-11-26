package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class EndGameState(context: GameController) extends GameState(context) {
  override def isEndGame: Boolean = true
}
