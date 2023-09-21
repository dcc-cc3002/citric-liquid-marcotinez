package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.PlayerCharacter

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

  val panelType: String = "Drop"

  /** Constructor auxiliar en el caso de que creemos un BonusPanel sin paneles adyacentes. */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  /** Method responsible for calculating and remove stars to the player.
   *
   *  - Este metodo probablemente se usará en la clase PlayerCharacter, y desde este panel
   *   solo haremos un llamado para que se ejecute.
   *
   *   */
  def StarDrop(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      val starsDrop: Int = player.rollDice()
      val norma: Int = player.normaLevel

      player.stars -= (norma * starsDrop)
    }
  }
}