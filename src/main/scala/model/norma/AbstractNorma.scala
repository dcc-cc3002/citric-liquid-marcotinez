package cl.uchile.dcc.citric
package model.norma

import model.entities.character.PlayerCharacter
import model.norma.objective.{Objective, Wins, Stars}

/** This abstract class is responsible for encapsulating all the common aspects of Normas.
 *
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
abstract class AbstractNorma extends Norma {
  /** The norma level */
  def getLevel: Int = level
  /** Returns the number of stars required to advance to the next norma level. */
  def getStars: Int = stars
  /** Returns the number of victories required to advance to the next norma level. */
  def getWins: Int = wins
  /** Returns the objective selected by the player to increase their Norma level. */
  def getObjective: Objective = objective

  /** We define a default value to objective */
  var objective: Objective = new Stars

  /** This method is responsible for performing the levelUp check
   * based on the objective selected by the player
   *
   * @param player the player to check.
   * */
  def normaCheck(player: PlayerCharacter): Boolean = {
    getObjective.levelUp(player, this)
  }

}
