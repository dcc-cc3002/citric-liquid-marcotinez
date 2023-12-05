package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state related to a specific chapter.
 *
 * @param context The game controller managing the game state.
 */
class ChapterState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state is a chapter state.
   *
   * @return True, as this is a chapter state.
   */
  override def isChapter: Boolean = true

  /**
   * Transitions to the PlayerTurnState when the turn is played.
   */
  override def goPlayTurn(): Unit = {
    context.setState(new PlayerTurnState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - Increments the chapter count in the game controller and add stars to the players.
   * - Transitions to the PlayerTurnState to start playing the turn.
   */
  override def doAction(): Unit = {
    context.addChapter()
    context.goPlayTurn()
  }

}
