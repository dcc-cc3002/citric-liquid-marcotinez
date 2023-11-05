package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.GameCharacter

/** Represents a Wild Unit on the game
 *
 * There are three specific Wild Units on the game
 *
 * @author [[https://github.com/marcotinez Marco Martínez S.]]
 * */
trait WildUnit extends GameCharacter {
  /** The extra number of stars that a wild unit delivers when defeated */
  protected val extraStars: Int
  /** Returns the extra number of stars that a wild unit delivers when defeated */
  def getExtraStars: Int
  /** The number of wins that a wild unit delivers when defeated */
  protected val extraVictories: Int
  /** Returns the number of wins that a wild unit delivers when defeated */
  def getExtraVictories: Int
}
