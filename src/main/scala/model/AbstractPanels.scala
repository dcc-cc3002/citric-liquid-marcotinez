package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/** This abstract class is responsible for encapsulating all the common aspects of panels.
  * nextPanels is not implemented in this class as it will be the argument received
  * by the constructor of each panel. This approach is due the possibility that a panel may
  * not have next panels.
  *
  * @param nextPanels An array of panels that are directly connected to this one.
  *
  * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
  * */
abstract class AbstractPanels(var nextPanels: ArrayBuffer[Panel]) extends Panel {

  /** An array of characters that are currently on this panel. */
  override val characters: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]

  /** Method responsible for adding a character to the panel. */
  override def addCharacter(player: PlayerCharacter): Unit = {
    if (!characters.contains(player)) {
      characters.append(player)
    }
  }

  /** Method responsible for removing a character from the panel. */
  override def removeCharacter(player: PlayerCharacter): Unit = {
    if(characters.contains(player)) {
      characters -= player
    }
  }

  /** Method responsible for returning the characters that are currently on the panel. */
  override def getCharacters(): ArrayBuffer[PlayerCharacter] = {
    characters
  }

  /** Method responsible for returning the next panels that are directly connected to this one. */
  override def getNextPanels(): ArrayBuffer[Panel] = {
    nextPanels
  }
}
