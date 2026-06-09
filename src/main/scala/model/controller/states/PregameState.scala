package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class PregameState(context: GameController) extends GameState(context) {
  override def startGame(): Unit = {
    this.setState(new ChapterState(context))
  }

  override def isPregame: Boolean = true
}
