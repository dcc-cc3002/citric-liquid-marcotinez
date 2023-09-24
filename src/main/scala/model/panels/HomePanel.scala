package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** Each player has a personal "HomePanel," which is determined at the beginning
  * of the game and can be activated in two different ways:
  *
  * 1. If the player owns the panel, they can choose to stop on it when passing over it,
  *    even if they have remaining moves.
  *
  * 2. If the player does not own the panel, it will activate only if they land directly on it.
  *
  * In both cases, the turn ends after activating the panel. Upon activation, the player
  * regains one health point, and the panel will perform a Norma check.
  *
  * @param nextPanels An array of panels that are directly connected to this one.
  *
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  */

class HomePanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  /** The type of panel. */
  val panelType: String = "Home"

  /** Auxiliary constructor in case no arguments are provided when creating the panel.
   *
   * @return A new BonusPanel instance with no connections.
   *
   * */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  //override def activatePanel(player: PlayerCharacter): Unit = ???

}
