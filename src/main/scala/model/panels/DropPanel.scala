package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** The "Drop Panel" class represents one of the special panels in the game. This panel
 * is responsible for penalizing the player and remove stars from the players who fall into it.
 * When a player lands on one of this panels, they must roll a 6-sided die and will lose a number of stars
 * equal to (roll * Norma).
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class DropPanel extends AbstractPanels {

  /** The type of panel. */
  val panelType: String = "Drop"

  //Debemos hacer un llamado a getCharacters y luego aplicar el descuento a todos los que se encuentren en el panel.
  //override def activatePanel(player: PlayerCharacter): Unit = ???
  def apply(player: PlayerCharacter): Unit = {
    val roll = player.rollDice()
    val stars = math.min(roll * player.getNormaLevel, roll * 3)
    player.starDrop(stars)
  }

}