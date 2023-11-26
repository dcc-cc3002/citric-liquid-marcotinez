package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class ChapterState(context: GameController) extends GameState(context) {
  override def isChapter: Boolean = true

  override def newChapter(): Unit = {
    this.setState(new ChapterState(context))
  }
  override def normaSixReached(): Unit = {
    this.setState(new EndGameState(context))
  }
  override def outOfCombat(): Unit = {
    this.setState(new RecoveryState(context))
  }
  override def playTurn(): Unit = {
    this.setState(new PlayerTurnState(context))
  }
}
