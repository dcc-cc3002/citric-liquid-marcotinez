package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

class LandingPanelState(context: GameController) extends GameState(context) {
  override def isLandingPanel: Boolean = true

  override def doEffect(): Unit = {
    this.setState(new ChapterState(context))
  }
}
