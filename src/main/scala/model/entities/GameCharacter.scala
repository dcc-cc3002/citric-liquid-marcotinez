package cl.uchile.dcc.citric
package model.entities


/** Represents any unit present in the game (WildUnits and playerCharacters).
 *  These units shares common attributes for which we define access
 *  methods (getHp, getAttack, etc.),
 *  Additionally, we define methods that allow the units to combat
 *  with each other (ataque, defend, etc.).
 *
 *  @author [[https://github.com/marcotinez Marco Martínez S.]]
 */
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
   * @param enemy the enemy that will receive the attack.
   * */
  def ataque(enemy: GameCharacter): Int

  /** Method that calculates the damage the defending Wild Unit will receive.
   *
   * @param atk_vs Base damage sent by the attacker.
   * */
  def defend(atk_vs: Int): Unit

  /** Method that allows the WildUnit to evade an attack; based on the rollDice,
   * it may receive either all the damage or none.
   *
   * @param atk_vs Base damage sent by the attacker.
   * */
  def evade(atk_vs: Int): Unit

  /** RollDice method defined for the GameCharacter */
  def rollDice(): Int
}
