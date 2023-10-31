package cl.uchile.dcc.citric
package model.entities.character

import cl.uchile.dcc.citric.model.entities.{AbstractPlayerCharacter, GameCharacter}

import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  */
class PlayerCharacter(override protected val name: String,
                      override protected val maxHp: Int,
                      override protected val attack: Int,
                      override protected val defense: Int,
                      override protected val evasion: Int,
                      override protected val randomNumberGenerator: Random = new Random()) extends
  AbstractPlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator) {

  /** Health points that can be modified throughout the game. */
  protected var hp: Int = maxHp

  /** The player's current norma level. */
  protected var normaLevel: Int = 1

  /** The number of stars the player has collected. */
  protected var starsAmount: Int = 0

  /** The number of victories the player has. */
  protected var victories: Int = 0

  /** Rolls a dice and returns a value between 1 to 6.
   *
   * @return a random number between 1 and 6.
   * */
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  def ataque(enemy: GameCharacter): Int = {
    if (this.enCombate && enemy.enCombate){
      val atk_vs = this.rollDice() + this.getAttack
      return atk_vs
    }
    else {
      return 0
    }
  }

  def defend(atk_vs: Int): Unit = {
    val expresion = this.rollDice() + this.getAttack - (this.rollDice() + this.getDefense)
    if (1 > expresion) {
      this.setHp(this.getHp - 1)
    }
    else {
      this.setHp(this.getHp - expresion)
    }
  }

  def evade(atk_vs: Int): Unit = {
    val expresion1 = this.rollDice() + this.getEvasion
    val expresion2 = this.rollDice() + this.getAttack
    if (expresion1 > expresion2) {
      this.setHp(this.getHp)
    }
    else {
      this.setHp(this.getHp - atk_vs)
    }
  }

}
