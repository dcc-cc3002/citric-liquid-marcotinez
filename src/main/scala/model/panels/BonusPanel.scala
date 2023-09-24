package cl.uchile.dcc.citric
package model.panels
import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** The "Bonus Panel" class represents one of the special panels in the game. This panel
 * is responsible for awarding a star bonus to the players who lands on it. When a player lands
 * on one of this panels, they must roll a 6-sided die and will earn a number of stars
 * equal to min(roll * Norma, roll * 3).
 *
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]] */
class BonusPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  /** The type of panel. */
  val panelType: String = "Bonus"

  /** Auxiliary constructor in case no arguments are provided when creating the panel.
   *
   * @return A new BonusPanel instance with no connections.
   *
   * */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  //Debemos hacer un llamado a la funcion getCharacters, luego aplicar el bonus a todos los personajes en la lista.
  //override def activatePanel(player: PlayerCharacter): Unit = ???
}
