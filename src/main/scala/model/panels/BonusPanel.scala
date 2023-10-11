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
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]] */
class BonusPanel extends AbstractPanels {

  /** The type of panel. */
  val panelType: String = "Bonus"

  def apply(player: PlayerCharacter): Unit = {
    val roll = player.rollDice()
    val stars = math.min(roll * player.getNormaLevel, roll * 3)
    player.starBonus(stars)
  }
}
