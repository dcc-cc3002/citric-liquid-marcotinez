package cl.uchile.dcc.citric
package model.panels
import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.PlayerCharacter

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

  val panelType: String = "Bonus"

  /** Constructor auxiliar en el caso de que creemos un BonusPanel sin paneles adyacentes. */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  /** Method responsible for calculating and adding stars to the player.
   *
   *  - Este metodo probablemente se usará en la clase PlayerCharacter, y desde este panel
   *    solo haremos un llamado para que se ejecute.
   * */
  def StarBonus(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      val starsBonus: Int = player.rollDice()
      val norma: Int = player.normaLevel
      if (norma * starsBonus < 3 * starsBonus) {
        player.stars += norma * starsBonus
      }
      else player.stars += (3 * starsBonus)
    }
  }
}
