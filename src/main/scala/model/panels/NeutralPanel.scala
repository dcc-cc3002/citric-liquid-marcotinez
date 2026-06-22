package cl.uchile.dcc.citric
package model.panels

import model.{AbstractPanels, Panel}

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

  def apply(player: PlayerCharacter): Unit = {
    print("NeutralPanel activated")
  }
}
