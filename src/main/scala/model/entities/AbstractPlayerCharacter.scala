package cl.uchile.dcc.citric
package model.entities
import scala.util.Random

abstract class AbstractPlayerCharacter(protected val name: String,
                                       protected val maxHp: Int,
                                       protected val attack: Int,
                                       protected val defense: Int,
                                       protected val evasion: Int,
                                       protected val randomNumberGenerator: Random = new Random()) extends GameCharacter {

  protected var hp: Int
  protected var normaLevel: Int
  protected var starsAmount: Int

  //------------GETTERS---------------
  def getName: String = name
  def getMaxHp: Int = maxHp
  def getAttack: Int = attack
  def getDefense: Int = defense
  def getEvasion: Int = evasion

  /** Returns the player's current hit points. */
  def getHp: Int = hp

  /** Returns the player's norma level.
   *
   * @return the player's norma level.
   * */
  def getNormaLevel: Int = normaLevel

  /** Returns the player's stars count
   *
   * @return the amount of stars the player has.
   * */
  def getStarsAmount: Int = starsAmount


  //------------SETTERS---------------
  /** Set the hit points of the character on a specific amount.
   *
   * @param hp the new amount of hit points to establish.
   * */
  protected def setHp(hp: Int): Unit = {
    if (hp > maxHp) {
      this.hp = maxHp
    }
    else
      this.hp = hp
  }

  /** Increase the hit point of the character on a specific amount.
   *
   * @param hp the amount of hit points to add.
   * */
  protected def heal(hp: Int): Unit = {
    if (this.hp + hp > maxHp) {
      this.hp = maxHp
    }
    else
      this.hp += hp
  }

  protected def damage(hp: Int): Unit = {
    if (this.hp - hp < 0) {
      this.hp = 0
    }
    else
      this.hp -= hp
  }

  /** Increases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the player has.
   * */
  protected def starBonus(amount: Int): Unit = {
    starsAmount += amount
  }

  /** Decreases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the player has.
   * */
  protected def starDrop(amount: Int): Unit = {
    if (starsAmount - amount < 0) {
      starsAmount = 0
    }
    else
      starsAmount -= amount
  }

  /** Increases the player's norma level by one.
   *
   * @return the player's norma level.
   * */
  protected def normaClear(): Unit = {
    normaLevel += 1
  }

}
