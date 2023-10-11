package cl.uchile.dcc.citric
package model.entities

trait GameCharacter {
  /** The maximum health points a character can have. It represents the character's endurance. */
  var enCombate: Boolean = true

  /** Returns the character's current hit points. */
  def getHp: Int

  /** Returns the character's attack points. */
  def getAttack: Int

  /** Returns the character's defense points. */
  def getDefense: Int

  /** Returns the character's evasion points. */
  def getEvasion: Int

  /** Returns the character's name. */
  def getName: String

  /** Returns the character's maximum hit points. */
  def getMaxHp: Int

  /** Returns the character's stars count. */
  def getStarsAmount: Int

  /** Set the hit points of the character on a specific amount.
   *
   * @param hp the new amount of hit points to establish.
   * */
  protected def setHp(hp: Int): Unit

  /** Decrease the hit point of the character on a specific amount.
   *
   * @param hp the amount of hit points to subtract.
   * */
  protected def damage(hp: Int): Unit

  /** Method that calculates and return the attack caused by a player.
   *
   *
   * */
  def ataque(enemy: GameCharacter): Int

  def defend(atk_vs: Int): Unit

  def evade(atk_vs: Int): Unit

  def rollDice(): Int
}
