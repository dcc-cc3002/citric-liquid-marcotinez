package cl.uchile.dcc.citric
package model.entities

trait GameCharacter {

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

  /** Method responsible for initiating the combat between the current player and a given Game Character
   *
   * //@param character Enemy GameCharacter to be fought against
   * */
  //def battle(character: GameCharacter): Unit

}
