package cl.uchile.dcc.citric
package model.entities
import model.entities.wildunit.WildUnit

import scala.util.Random

/** This abstract class is responsible for encapsulating all the common aspects of Characters.
 *
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
abstract class AbstractCharacter extends GameCharacter  {
  protected val maxHp: Int
  protected val attack: Int
  protected val defense: Int
  protected val evasion: Int
  protected val randomNumberGenerator: Random = new Random()

  protected var hp: Int
  protected var starsAmount: Int

  //------------GETTERS---------------
  def getMaxHp: Int = maxHp

  def getAttack: Int = attack

  def getDefense: Int = defense

  def getEvasion: Int = evasion

  /** Returns the character's current hit points.
   *
   * @return the hp the character has.
   * */
  def getHp: Int = hp

  /** Returns the character's stars count
   *
   * @return the amount of stars the character has.
   * */
  def getStarsAmount: Int = starsAmount

  //------------HIT POINTS------------

  /** Set the hit points of the character on a specific amount.
   * @param hp the new amount of hit points to establish.
   */
  protected def setHp(hp: Int): Unit = {
    if (hp == 0) {
      this.hp = 0
      enCombate = false
    } else
      this.hp = hp
  }

  def damage(hp: Int): Unit = {
    val newHp = Math.max(this.hp - hp, 0)
    setHp(newHp)
    if (newHp == 0) {
      enCombate = false
    }
  }
  //-----------------STARS-----------------
  /** Set the amount of stars the player has.
   *
   * @param starsAmount the new amount of stars to establish.
   * */
  protected def setStars(starsAmount: Int): Unit = {
      this.starsAmount = starsAmount
  }

  //-----------------STARS-----------------

  /** Increases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the player has.
   * */
  def starBonus(amount: Int): Unit = {
    setStars(getStarsAmount + amount)
  }

  /** Decreases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the player has.
   * */
  def starDrop(amount: Int): Unit = {
    if (starsAmount - amount < 0) {
      setStars(0)
    }
    else
      setStars(getStarsAmount - amount)
  }

  //------------------COMBAT-------------------
  def rollDice(): Int = {
    randomNumberGenerator.nextInt(6) + 1
  }

  def ataque(enemy: GameCharacter): Int = {
    if (this.enCombate && enemy.enCombate) {
      val atk_vs = this.rollDice() + this.getAttack
      atk_vs
    }
    else {
      0
    }
  }

  def defend(atk_vs: Int): Unit = {
    val expresion = this.rollDice() + this.getAttack - (this.rollDice() + this.getDefense)
    if (1 > expresion) {
      this.damage(1)
    }
    else {
      this.damage(expresion)
    }
  }

  def evade(atk_vs: Int): Unit = {
    val expresion1 = this.rollDice() + this.getEvasion
    val expresion2 = this.rollDice() + this.getAttack
    if (expresion1 <= expresion2) {
      this.damage(atk_vs)
    }
  }
}
