package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.GameCharacter
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
    else
      this.hp = hp
  }

  protected def damage(hp: Int): Unit = {
    if (this.hp - hp < 0) {
      setHp(0)
    }
    else
      setHp(this.hp - hp)
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
  protected def starBonus(amount: Int): Unit = {
    setStars(getStarsAmount + amount)
  }

  /** Decreases the number of stars by a given amount.
   *
   * @param amount the amount of stars to add.
   * @return the amount of stars the WildUnit has.
   * */
  protected def starDrop(amount: Int): Unit = {
    if (starsAmount - amount < 0) {
      setStars(0)
    }
    else
      setStars(getStarsAmount - amount)
  }
}
