package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}
import cl.uchile.dcc.citric.model.entities.PlayerCharacter

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
  * @param nextPanels An array of panels that are directly connected to this one.
  * @param owner The player who owns this panel, this does not mean it is activated by the player.
  *
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  */

class HomePanel(nextPanels: ArrayBuffer[Panel],
                val owner: PlayerCharacter) extends AbstractPanels(nextPanels) {

  val panelType: String = "Home"

  /** Constructor auxiliar en el caso de que creemos un HomePanel sin paneles adyacentes. */
  def this(owner: PlayerCharacter) {
    this(ArrayBuffer.empty[Panel], owner)
  }

  /** Method responsible for reward the player after the activation */
  def reward(player: PlayerCharacter): Unit = {
    if(characters.contains(player) && player == owner) {
      player.hp += 1
      player.NormaCheck(player.normaLevel)
      }
    }

}
