package cl.uchile.dcc.citric
package model.norma.objective

import model.entities.character.PlayerCharacter
import model.norma.Norma

/** This trait is solely responsible for defining the method to check if the player
 * can level up. Then, depending on the selected objective,
 * a more specific method will be used.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
trait State {
  /** Method responsible for checking if the player meets the necessary conditions
   * to advance to the next Norma.
   *
   * @param player the player to check.
   * @param normaLvl the current norma level of the player.
   * */
  def levelUp(player: PlayerCharacter, normaLvl: Norma): Boolean
}
