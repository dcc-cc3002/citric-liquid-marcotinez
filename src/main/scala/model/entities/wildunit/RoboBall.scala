package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.GameCharacter

/** A class representing the WildUnit RoboBall.
 *
 * This class represents the WildUnit RoboBall, which is a type of enemy that can be found
 * in the game. It has a maximum health of 3, an attack of -1, a defense of 1 and an evasion
 * of -1.
 *
 * @param maxHp The maximum health points a player can have. It represents the player's endurance.
 * @param attack The player's capability to deal damage to opponents.
 * @param defense The player's capability to resist or mitigate damage from opponents.
 * @param evasion The player's skill to completely avoid certain attacks.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 *
 * */
class RoboBall(val maxHp: Int = 3,
              val attack: Int = -1,
              val defense: Int = 1,
              val evasion: Int = -1) extends WildUnit with GameCharacter {}
