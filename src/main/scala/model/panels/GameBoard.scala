package cl.uchile.dcc.citric
package model.panels
import model.entities.character.PlayerCharacter
import scala.collection.mutable.ArrayBuffer

/**
 * The `GameBoard` class is responsible for generating a static game board that incorporates various types of panels.
 *
 * @param players An `ArrayBuffer` containing instances of `PlayerCharacter` representing the players in the game.
 */
class GameBoard(players: ArrayBuffer[PlayerCharacter]) {

  /** The first panel of the board. */
  private var firstPanel: Panel = _
  /** The actual panel of the board. */
  private var actualPanel: Panel = _
  /** The last panel of the board. */
  private var lastPanel: Panel = _

  def getFirstPanel: Panel = firstPanel
  def getActualPanel: Panel = actualPanel
  def getLastPanel: Panel = lastPanel

  /** We add a HomePanel for each player */
  for (i <- players.indices) {
    lastPanel = new HomePanel(players(i))
    players(i).setPanel(lastPanel)
    if (i == 0) {
      firstPanel = lastPanel
      actualPanel = lastPanel
    }
    else {
      actualPanel.addNextPanel(lastPanel)
      actualPanel = lastPanel
    }
  }
  /** We add some neutral panels */
  for(i <- 0 to 2){
    lastPanel = new NeutralPanel()
    actualPanel.addNextPanel(lastPanel)
    actualPanel = lastPanel
  }
  /** We add a encounter panel */
  lastPanel = new EncounterPanel()
  lastPanel.apply(players(0))
  actualPanel.addNextPanel(lastPanel)
  actualPanel = lastPanel

  /** We add a neutral panel */
  lastPanel = new NeutralPanel()
  actualPanel.addNextPanel(lastPanel)
  actualPanel = lastPanel

  /** We add a bonus panel */
  lastPanel = new BonusPanel()
  actualPanel.addNextPanel(lastPanel)
  actualPanel = lastPanel

  /** We add some neutral panels */
  for (i <- 0 to 1) {
    lastPanel = new NeutralPanel()
    actualPanel.addNextPanel(lastPanel)
    actualPanel = lastPanel
  }

  /** We add a encounter panel */
  lastPanel = new EncounterPanel()
  lastPanel.apply(players(0))
  actualPanel.addNextPanel(lastPanel)
  actualPanel = lastPanel

  /** We add a drop panel */
  lastPanel = new DropPanel()
  actualPanel.addNextPanel(lastPanel)
  actualPanel = lastPanel

  /** We add some neutral panels */
  for (i <- 0 to 3) {
    lastPanel = new NeutralPanel()
    actualPanel.addNextPanel(lastPanel)
    actualPanel = lastPanel
  }

  /** We close the board */
  actualPanel.addNextPanel(firstPanel)
}
