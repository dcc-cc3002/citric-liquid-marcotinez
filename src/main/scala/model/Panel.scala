package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** Represents a single cell on a board, known as a Panel.
  *
  * Each panel has its own effect, which can be applied to a character.
  * In the context of the board game, a panel represents a tile or space that a character lands on
  * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
  * Panels can also be connected to other panels, allowing for the formation of complex board
  * structures.
  *
  * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
  * @author [[https://github.com/marcotinez Marco Martínez S.]]
  */
trait Panel {

  /** Array of the characters currently positioned on this panel.
    *
    * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
    * land on the same space.
    */
  val characters: ArrayBuffer[PlayerCharacter] //This could be a Map, using name and panel

  /** An array of panels that are directly connected to this one.
   *
   * In the context of the game, multiple routes or paths may exist, this could represent the
   * possible next steps a player might take after being on this panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  var nextPanels: ArrayBuffer[Panel]

  /** Adds a character to the list of characters currently on this panel.
    *
    * This might be invoked when a player moves to this panel or starts their turn on it.
    *
    * @param player The player character to add to this panel.
    */
  def addCharacter(player: PlayerCharacter): Unit

  /** Removes a character from the list of characters currently on this panel.
    *
    * This might be invoked when a player moves off this panel.
    *
    * @param player The player character to remove from this panel.
    */
  def removeCharacter(player: PlayerCharacter): Unit

  /** Returns the characters that are currently on the panel.
   *
   * This may be used to check if a player is on the panel, or to apply an effect to all the players
   * on the panel.
   *
   * @return an array of PlayerCharacter instances that are currently on this panel.
   *
   * */
  def getCharacters: ArrayBuffer[PlayerCharacter]

  /** Returns the next panels that are directly connected to this one.
   *
   * This can be invoked to check the possible next steps a player might take after being on this
   * panel.
   *
   * @return a List of Panel instances that are adjacent or connected to this panel.
   */
  def getNextPanels: ArrayBuffer[Panel]

  /** add a NextPanel to the list of nextPanels of this panel.
   *
   * This might be invoked add panels and create a board.
   *
   * @param nextPanel The panel to add to the list of nextPanels of this panel.
   * */

  def addNextPanel(nextPanel: Panel): Unit

  /** Removes a NextPanel from the list of nextPanels of this panel.
   *
   * This might be invoked to remove panels and create a board.
   *
   * @param nextPanel The panel to remove from the list of nextPanels of this panel.
   * */

  def removeNextPanel(nextPanel: Panel): Unit

  /** Method responsible for applying the effects of each panel on the player.
   *
   * The method call depends on the panel.
   *
   * //@param player The player character who will receive the effects of the panel */
  def apply(player: PlayerCharacter): Unit


}
