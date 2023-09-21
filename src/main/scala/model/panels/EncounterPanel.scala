package cl.uchile.dcc.citric
package model.panels
import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.{PlayerCharacter, WildHunt}

import scala.collection.mutable.ArrayBuffer

/** The "EncounterPanel" is responsible for engaging the player in combat with a random Wild Hunt.
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class EncounterPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  val panelType: String = "Encounter"

  /** Constructor auxiliar en el caso de que creemos un EncounterPanel sin paneles adyacentes. */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }
}
