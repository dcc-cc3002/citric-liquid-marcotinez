package cl.uchile.dcc.citric
package model.norma.objective
import model.entities.character.PlayerCharacter
import model.norma.Norma

/** This class is responsible for checking if the player can level up
 * their Norma based on the wins he has.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class Wins extends State {
  def levelUp(player: PlayerCharacter, normaLvl: Norma): Boolean = {
    if (player.getVictories >= normaLvl.getWins) {
      true
    }
    else false
  }
}
