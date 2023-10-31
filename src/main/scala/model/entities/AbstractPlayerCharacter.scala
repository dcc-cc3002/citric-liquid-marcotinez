package cl.uchile.dcc.citric
package model.entities
import scala.util.Random
import scala.math._

abstract class AbstractPlayerCharacter(protected val name: String,
                                       protected val maxHp: Int,
                                       protected val attack: Int,
                                       protected val defense: Int,
                                       protected val evasion: Int,
                                       protected val randomNumberGenerator: Random = new Random()) extends GameCharacter {

  protected var hp: Int
  protected var normaLevel: Int
  protected var starsAmount: Int
  protected var victories: Int

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

  /** Return the player’s win count */
  def getVictories: Int = victories

  //------------SETTERS---------------

  //------------HIT POINTS------------
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
    } else
      this.hp = hp
  }

  /** Increase the hit point of the character on a specific amount.
   *
   * @param hp the amount of hit points to add.
   * */
  def heal(hp: Int): Unit = {
    if (this.hp == 0 && hp > 0) {
      setHp(Math.min(hp, maxHp))
      enCombate = true
    } else
      setHp(Math.min(this.hp + hp, maxHp))
  }

  def damage(hp: Int): Unit = {
    val newHp = Math.max(this.hp - hp, 0)
    setHp(newHp)
    if(newHp == 0) {
      enCombate = false
    }
    else{
      enCombate = true
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

  //-----------------NORMA-----------------

  /** Set the player's norma level.
   *
   * @param newNorma the new norma level to establish.
   * */
   private def setNormaLevel(newNorma: Int): Unit = {
    this.normaLevel = newNorma
  }

  /** Increases the player's norma level by one.
   *
   * @return the player's norma level.
   * */
   def normaClear(): Unit = {
    setNormaLevel(normaLevel + 1)
  }

}
