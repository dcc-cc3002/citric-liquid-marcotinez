package cl.uchile.dcc.citric
package model.entities.character

import model.entities.GameCharacter
import model.norma.Norma
import model.norma.normalevels.Norma1

import cl.uchile.dcc.citric.model.controller.GameController
import cl.uchile.dcc.citric.model.panels.{HomePanel, Panel}

import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
 *
  * - Increase or decrease their star count.
 *
  * - Roll a dice, a common action in many board games.
 *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  */
class PlayerCharacter(override protected val name: String,
                      override protected val maxHp: Int,
                      override protected val attack: Int,
                      override protected val defense: Int,
                      override protected val evasion: Int,
                      override protected val randomNumberGenerator: Random = new Random()) extends AbstractPlayerCharacter {


  /** Health points that can be modified throughout the game. */
  override protected var hp: Int = maxHp

  /** The player's current norma level. */
  private var normaLevel: Norma = new Norma1

  /** The number of stars the player has collected. */
  override protected var starsAmount: Int = 0

  /** The number of victories the player has. */
  override protected var victories: Int = 0

  /** The player's current position on the board. */
  private var panel: Panel = _

  /** The required amount that the player needs to roll on the die to recover.*/
  private var recoveryAmount: Int = 6

  /** The player's observer */
  private var observer: Option[GameController] = None

  //-----------------NORMA-----------------

  /** Returns the player's norma level.
   *
   * @return the player's norma level.
   * */
  def getNormaLevel: Int = normaLevel.getLevel

  /** Returns the player's norma.
   *
   * @return the player's norma.
   * */
  def getNorma: Norma = normaLevel

  /** Method responsible for setting the player's norma.
   *
   * @param norma the norma to set.
   * */
  def setNorma(norma: Norma): Unit = {
    this.normaLevel = norma
  }

  /** Method responsible for starting the normaCheck. */
  def normaCheck(): Unit = {
    normaLevel.normaCheck(this)
  }

  /** Method responsible for increasing the norma level. */
  def normaClear(): Unit = {
    this.normaLevel = this.normaLevel.nextNormaLevel
  }

  /** Method created specifically for testing. */
  def setVictories(amount: Int): Unit = {
    this.victories = amount
  }

  /** Method responsible for set the HomePanel */
  def setPanel(panel: Panel): Unit = {
    this.panel = panel
  }

  /** Method responsible for returning the panel where the player is.
   *
   * @return the panel where the player is.
   * */
  def getPanel: Panel = panel

  /** Method responsible for handling the receipt of an attack. */
  def attacked(character: GameCharacter): Unit = {
    val atk: Int = character.getAttack
    val ranNum: Int = Random.nextInt(2)+1
    //We generate a random number to determine the PlayerCharacter's response
    if(ranNum == 1) {
      this.defend(atk)
    }
    else {
      this.evade(atk)
    }
    if (this.getHp == 0) {
      character.playerCharacterKO(this)
    }
  }

  /** Method responsible of handling the KO of the player. */
  def recovery(): Unit = {
    if(!this.enCombate) {
      val roll: Int = rollDice()
      if (roll >= recoveryAmount) {
        this.heal(this.maxHp)
        recoveryAmount = 6
      }
      else {
        recoveryAmount -= 1
      }
    }
  }

  //OBSERVER
  /** Returns the player's observer
   *
   * @return the player's observer.
   * */
  def getObserver: Option[GameController] = observer

  /** Method responsible for registering the observer.
   *
   * @param controller the observer to register.
   * */
  def register(controller: GameController): Unit = {
    observer = Some(controller)
  }

  /** Method responsible for notifying the observer of changes in the player's Norma. */
  def notifyObserver(): Unit = {
    if(observer.isDefined) {
      observer.get.update(this)
    }
  }

}
