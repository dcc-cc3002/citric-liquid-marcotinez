package cl.uchile.dcc.citric
package model.norma

import model.norma.objective.Objective
import model.entities.character.PlayerCharacter

/** This represents any Norma present in the game (Norma level 1, Norma level 2, etc).
 * Normas share common attributes (number of wins, number of stars, an objective, a next rule).
 * In addition, the possibility of performing a NormaCheck is defined to see if
 * the player is able to increase their rule level.
 *
 * @author [[https://github.com/marcotinez Marco Martínez S.]]
 * */
trait Norma {
  /** The norma level */
  protected val level: Int
  /** Returns the norma level */
  def getLevel: Int
  /** The number of stars required to advance to the next norma level. */
  protected val stars: Int
  /** Returns the number of stars required to advance to the next norma level. */
  def getStars: Int
  /** The number of victories required to advance to the next norma level. */
  protected val wins: Int
  /** Returns the number of victories required to advance to the next norma level. */
  def getWins: Int
  /** The objective selected by the player to increase their Norma level. */
  protected var objective: Objective

  /** The value of nextNorma is unique for each Norma since it indicates
   *  the next rule level. */
  val nextNormaLevel: Norma
  /** This method is responsible for performing the levelUp check
   * based on the objective selected by the player
   *
   * @param player the player to check.
   * */
  def normaCheck(player: PlayerCharacter): Unit

  /** I know is not a good design to define method on traits, but I needed this
   * to test the different states */
  def setObjetive(obj: Objective): Unit = {
    this.objective = obj
  }
}
