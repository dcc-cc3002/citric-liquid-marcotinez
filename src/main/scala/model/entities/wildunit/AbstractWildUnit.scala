package cl.uchile.dcc.citric
package model.entities.wildunit
import scala.util.Random
import model.entities.GameCharacter

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
/** This abstract class is responsible for encapsulating all common aspects of WildUnits.
 * The inherited methods of GameCharacter are defined, primarily getters and setters;
 * furthermore, the variables that each of the WildUnits will have are also defined.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
abstract class AbstractWildUnit extends GameCharacter{
  /** The maximum health points a WildUnit can have. It represents the WildUnit's endurance. */
  protected val maxHp: Int
  /** The WildUnit's capability to deal damage to opponents. */
  protected val attack: Int
  /** The WildUnit's capability to resist or mitigate damage from opponents. */
  protected val defense: Int
  /** The WildUnit's skill to completely avoid certain attacks. */
  protected val evasion: Int
  /** The name of a WildUnit */
  protected val name: String
  /** WildUnit's hit points that will change throughout the match. */
  protected var hp: Int
  /** The number of stars the WildUnit has collected. */
  protected var starsAmount: Int

  //------GETTERS----------
  def getName: String = name

  def getMaxHp: Int = maxHp

  def getAttack: Int = attack

  def getDefense: Int = defense

  def getEvasion: Int = evasion

  def getHp: Int = hp

  def getStarsAmount: Int = starsAmount


  //------SETTERS AND MORE.----------

  //------HIT POINTS------------
  /** Set the hit points of the character on a specific amount.
   *
   * @param hp the new amount of hit points to establish.
   * */
  protected def setHp(hp: Int): Unit = {
    if (hp > maxHp) {
      this.hp = maxHp
    }
    if (hp == 0) {
      this.hp = 0
      enCombate = false
    }
    else
      this.hp = hp
  }

  def damage(hp: Int): Unit = {
    val newHp = Math.max(this.hp - hp, 0)
    setHp(newHp)
    if (newHp == 0) {
      enCombate = false
    }
  }

  //------------STARS------------

  /** Set the amount of stars the player has.
   *
   * @param starsAmount the new amount of stars to establish.
   * */
  protected def setStars(starsAmount: Int): Unit = {
    this.starsAmount = starsAmount
  }

  /** Increases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the WildUnit has.
   * */
  def starBonus(amount: Int): Unit = {
    setStars(getStarsAmount + amount)
  }

  /** Decreases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the WildUnit has.
   * */
  def starDrop(amount: Int): Unit = {
    if (starsAmount - amount < 0) {
      setStars(0)
    }
    else
      setStars(getStarsAmount - amount)
  }

  /** Rolls a dice and returns a value between 1 to 6.
   *
   * @return a random number between 1 and 6.
   * */
  def rollDice(): Int = {
    val randomNumberGenerator: Random = new Random()
    randomNumberGenerator.nextInt(6) + 1
  }

  def ataque(enemy: GameCharacter): Int = {
    if (this.enCombate && enemy.enCombate){
      val atk_vs = this.rollDice() + this.getAttack
      atk_vs
    }
    else {
      0
    }
  }

  def defend(atk_vs: Int): Unit = {
    val expresion: Int = this.rollDice() + this.getAttack - (this.rollDice() + this.getDefense)
    if (1 > expresion) {
      this.setHp(this.getHp - 1)
    }
    else {
      this.setHp(this.getHp - expresion)
    }
  }

  def evade(atk_vs: Int): Unit = {
    val expresion1: Int = this.rollDice() + this.getEvasion
    val expresion2: Int = this.rollDice() + this.getAttack
    if (expresion1 > expresion2) {
      this.setHp(this.getHp)
    }
    else {
      this.setHp(this.getHp - atk_vs)
    }
  }
}
