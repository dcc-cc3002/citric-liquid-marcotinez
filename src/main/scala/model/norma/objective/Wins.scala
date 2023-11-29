package cl.uchile.dcc.citric
package model.norma.objective
import model.entities.character.PlayerCharacter
import model.norma.Norma

/** This class is responsible for checking if the player can level up
 * their Norma based on the wins he has.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class Wins extends Objective {
  /** Method responsible for checking if the player meets the necessary conditions
   * to advance to the next Norma.
   *
   * @param player   the player to check.
   * @param normaLvl the current norma level of the player.
   * */
  def levelUp(player: PlayerCharacter, normaLvl: Norma): Unit = {
    val playerWins: Int = player.getVictories
    val normaWins: Int = normaLvl.getWins
    //The player can increase the level of their Norma using Wins
    if (playerWins >= normaWins) {
      player.normaClear()
    }
  }
}
