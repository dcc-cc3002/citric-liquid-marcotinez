package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents the pregame state before the start of the game.
 *
 * @param context The game controller managing the game state.
 */
class PregameState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the pregame state.
   *
   * @return True, as this is the pregame state.
   */
  override def isPregame: Boolean = true

  /**
   * Transitions to the ChapterState to start the first chapter of the game.
   */
  override def goChapter(): Unit = {
    this.setState(new ChapterState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Initiates the game with a specified number of players.
   * - Transitions to the ChapterState to start the first chapter.
   */
  override def doAction(): Unit = {
    context.startGame(4)
    context.goChapter()
  }
}
