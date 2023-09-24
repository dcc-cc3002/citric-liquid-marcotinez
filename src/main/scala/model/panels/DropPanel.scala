package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** The "Drop Panel" class represents one of the special panels in the game. This panel
 * is responsible for penalizing the player and remove stars from the players who fall into it.
 * When a player lands on one of this panels, they must roll a 6-sided die and will lose a number of stars
 * equal to (roll * Norma).
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class DropPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  /** The type of panel. */
  val panelType: String = "Drop"

  /** Auxiliary constructor in case no arguments are provided when creating the panel.
   *
   * @return A new BonusPanel instance with no connections.
   *
   * */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  //Debemos hacer un llamado a getCharacters y luego aplicar el descuento a todos los que se encuentren en el panel.
  //override def activatePanel(player: PlayerCharacter): Unit = ???


}