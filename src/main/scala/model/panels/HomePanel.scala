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
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  */
class HomePanel extends AbstractPanels {

  /** The type of panel. */
  val panelType: String = "Home"
  protected var owner: PlayerCharacter = _

  /** Method that sets the owner of the panel. */
  def setOwner(player: PlayerCharacter): Unit = {
    owner = player
  }

  /** Method that returns the owner of the panel. */
  def getOwner: PlayerCharacter = {
    owner
  }

  /** Method responsible for applying the effects of each panel on the player.
   *
   * The method call depends on the panel.
   * @param player The player character who will receive the effects of the panel
   * */
  def apply(player: PlayerCharacter): Unit = {
    //We heal the player 1 hp
    if(player.getHp < player.getMaxHp) {
      player.heal(1)
    }
    //We check if the player can level up
    if(player.normaCheck()) {
      player.normaClear()
    }

  }


}
