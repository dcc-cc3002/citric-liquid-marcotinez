package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter

import scala.collection.mutable.ArrayBuffer

/** This panel has no effect on the player. If a player lands here, their turn
 * will end without any consequences.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class NeutralPanel extends AbstractPanels {

  /** The type of panel. */
  val panelType: String = "Neutral"

  /** Method responsible for applying the effects of each panel on the player.
   *
   * The method call depends on the panel.
   * @param player The player character who will receive the effects of the panel
   * */
  def apply(player: PlayerCharacter): Unit = {
    //Do nothing
  }
}
