package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** This abstract class is responsible for encapsulating all the common aspects of panels.
  *
  *
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  * */
abstract class AbstractPanels extends Panel {

  /** An array of characters that are currently on this panel. */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]

  /** An array with the Next panels.*/
  var nextPanels: ArrayBuffer[Panel] = ArrayBuffer.empty[Panel]

  /** Method responsible for adding a character to the panel. */
  override def addCharacter(player: PlayerCharacter): Unit = {
    if (!characters.contains(player)) {
      characters += player
    }
  }

  /** Method responsible for removing a character from the panel. */
  override def removeCharacter(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      characters -= player
    }
  }

  /** Method responsible for adding a next panel to the panel. */
  override def addNextPanel(nextPanel: Panel): Unit = {
    if (!nextPanels.contains(nextPanel)) {
      nextPanels += nextPanel
    }
  }

  /** Method responsible for removing a next panel from the panel. */
  override def removeNextPanel(nextPanel: Panel): Unit = {
    if(nextPanels.contains(nextPanel)) {
      nextPanels -= nextPanel
    }
  }

  /** Method responsible for returning the characters that are currently on the panel. */
  override def getCharacters: ArrayBuffer[PlayerCharacter] = {
    characters
  }

  /** Method responsible for returning the next panels that are directly connected to this one. */
  override def getNextPanels: ArrayBuffer[Panel] = {
    nextPanels
  }
}
