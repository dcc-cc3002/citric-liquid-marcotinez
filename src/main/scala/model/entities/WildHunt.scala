package cl.uchile.dcc.citric
package model.entities


/** Class representing a "Wild Hunt," it is not yet initialized.
 *  It was created solely to give meaning to the "EncounterPanel."
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class WildHunt {

  /** Method responsible for initiating a battle between a Wild Hunt and the player on the panel. */
  def Combat(player: PlayerCharacter): Unit = {
    println(s"${player.name} is fighting a wild hunt!")
  }

}
