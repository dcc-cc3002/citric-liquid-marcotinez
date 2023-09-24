package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** This panel has no effect on the player. If a player lands here, their turn
 * will end without any consequences.
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class NeutralPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  /** The type of panel. */
  val panelType: String = "Neutral"

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
