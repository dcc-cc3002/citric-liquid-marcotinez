package cl.uchile.dcc.citric
package model.norma.objective
import model.entities.character.PlayerCharacter
import model.norma.Norma

/** This class is responsible for checking if the player can level up
 * their Norma based on the stars he has.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class Stars extends Objective {
  /** Method responsible for checking if the player meets the necessary conditions
   * to advance to the next Norma.
   *
   * @param player   the player to check.
   * @param normaLvl the current norma level of the player.
   * */
  def levelUp(player: PlayerCharacter, normaLvl: Norma): Unit = {
    val playerStars: Int = player.getStarsAmount
    val normaStars: Int = normaLvl.getStars
    //The player can increase the level of their Norma using Stars
    if (playerStars >= normaStars) {
      if (player.getNorma.nextNormaLevel.getLevel == 6) {
        player.normaClear()
        player.notifyObserver()

      }
      else {
        player.normaClear()
      }
    }
  }
}
