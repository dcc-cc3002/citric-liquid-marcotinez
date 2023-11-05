package cl.uchile.dcc.citric
package model.entities.wildunit
import model.entities.{AbstractCharacter, GameCharacter}
import model.entities.character.PlayerCharacter
import scala.util.Random

abstract class AbstractWildUnit extends AbstractCharacter with WildUnit {

  /** The extra number of stars that a wild unit delivers when defeated */
  def getExtraStars: Int = extraStars
  /** The number of wins that a wild unit delivers when defeated */
  def getExtraVictories: Int = extraVictories

  /** Method responsible for handling the case when a WildUnit is attacked.
   * Here, we generate the attack value and use a random number to
   * determine the WildUnit's response (defend or evade).
   *
   * @param character the character that is attacking the WildUnit.
   * */
  def attacked(character: GameCharacter): Unit = {
    val atk: Int = character.getAttack
    val random: Random = new Random()
    val response: Int = random.nextInt(2) + 1
    if (response == 1) {
      this.defend(atk)
    } else {
      this.evade(atk)
    }
    if (this.getHp == 0) {
      character.defeatWildUnit(this)
    }
  }

  //Para los siguientes metodos, this es una wildUnit.
  /** Method responsible for distributing the number of stars and wins to the
   * character who defeats a Wild Unit.
   *
   * @param wildUnit the Wild Unit that is defeated.
   * */
  def defeatWildUnit(wildUnit: WildUnit): Unit = {
    //We give stars to the Character
    wildUnit.enCombate = false
    this.starsAmount += wildUnit.getStarsAmount + wildUnit.getExtraStars
  }

  /** Method responsible for distributing the number of stars and wins to the
   * character who defeats a Player Character
   *
   * @param playerCharacter the Player Character that is defeated.
   * */
  def defeatPlayerCharacter(playerCharacter: PlayerCharacter): Unit = {
    //We give stars to the Character
    this.starsAmount += playerCharacter.getStarsAmount/2
    //We take stars from the playerCharacter
    playerCharacter.enCombate = false
    playerCharacter.starDrop(playerCharacter.getStarsAmount/2)
  }
}
