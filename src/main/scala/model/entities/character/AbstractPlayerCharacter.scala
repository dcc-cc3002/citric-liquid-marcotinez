package cl.uchile.dcc.citric
package model.entities.character
import model.entities.AbstractCharacter
import model.entities.wildunit.WildUnit

/** This abstract class is responsible for encapsulating all the common aspects of PlayerCharacters.
 *
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
abstract class AbstractPlayerCharacter extends AbstractCharacter {
  /** The name of the player. */
  protected val name: String
  /** Return the player’s name */
  def getName: String = name
  /** The number of victories the player has. */
  protected var victories: Int
  /** Return the player’s win count */
  def getVictories: Int = victories

  //------------------HP-------------------
  /** Increase the hit point of the character on a specific amount.
   *
   * @param hp the amount of hit points to add.
   */
  def heal(hp: Int): Unit = {
    if (this.hp == 0 && hp > 0) {
      setHp(Math.min(hp, maxHp))
      enCombate = true
    } else
      setHp(Math.min(this.hp + hp, maxHp))
  }


  //-----------------COMBAT---------------------
  //En estos metodos, this corresponde a un playerCharacter.

  /** Method responsible for distributing the number of stars and wins to the
   * character who defeats a Wild Unit
   *
   * @param wildUnit the Wild Unit that is defeated.
   * */
  def wildUnitKO(wildUnit: WildUnit): Unit = {
    wildUnit.enCombate = false
    this.setStars(this.starsAmount + wildUnit.getStarsAmount + wildUnit.getExtraStars)
    this.victories += wildUnit.getVictories
  }

  /** Method responsible for distributing the number of stars and wins to the
   * character who defeats a Player Character
   *
   * @param playerCharacter the Player Character that is defeated.
   * */
  def playerCharacterKO(playerCharacter: PlayerCharacter): Unit = {
    //We give the playerCharacter half of the stars the defeated playerCharacter had and 2 victories
    this.setStars(this.starsAmount + playerCharacter.getStarsAmount/2)
    this.victories += 2
    //We take half of the stars the defeated playerCharacter had
    playerCharacter.enCombate = false
    playerCharacter.setStars(playerCharacter.getStarsAmount - (playerCharacter.getStarsAmount/2))
  }
}
