package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.GameCharacter

/** A class representing the WildUnit Seagull.
 *
 * This class represents the WildUnit Seagull, which is a type of enemy that can be found
 * in the game. It has a maximum health of 3, an attack of 1, a defense of -1 and an evasion
 * of -1.
 *
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 *
 * */

class Seagull extends WildUnit with GameCharacter {
  val maxHp: Int = 3
  val attack: Int = 1
  val defense: Int = -1
  val evasion: Int = -1
}
