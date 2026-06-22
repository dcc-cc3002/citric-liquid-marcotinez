package cl.uchile.dcc.citric
package model.entities.wildunit

/** A class representing the WildUnit Seagull.
 *
 * This class represents the WildUnit Seagull, which is a type of enemy that can be found
 * in the game. It has a maximum health of 3, an attack of 1, a defense of -1 and an evasion
 * of -1.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */

class Seagull extends AbstractWildUnit {
  //Combat Stats
  protected val maxHp: Int = 3
  protected val attack: Int = 1
  protected val defense: Int = -1
  protected val evasion: Int = -1

  protected val extraStars: Int = 2
  protected val extraVictories: Int = 1

  //Updatable statistics
  protected var hp: Int = maxHp
  protected var starsAmount: Int = 0
  protected var victories: Int = 0

}
