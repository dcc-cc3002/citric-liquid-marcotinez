package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}
import scala.collection.mutable.ArrayBuffer

/** This panel has no effect on the player. If a player lands here, their turn
 * will end without any consequences.
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class NeutralPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  val panelType: String = "Neutral"

  /** Constructor auxiliar en el caso de que creemos un EncounterPanel sin paneles adyacentes. */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }
}
