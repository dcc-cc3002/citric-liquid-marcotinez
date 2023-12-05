package cl.uchile.dcc.citric
package model.controller.states
import model.controller.{GameController, GameState}

/**
 * Represents a game state indicating the end of a turn.
 *
 * @param context The game controller managing the game state.
 */
class EndTurnState(context: GameController) extends GameState(context) {
  /**
   * Indicates whether this state represents the end of a turn.
   *
   * @return True, as this is the end turn state.
   */
  override def isEndTurn: Boolean = true

  /**
   * Transitions to the ChapterState to start a new chapter.
   */
  override def goChapter(): Unit = {
    context.setState(new ChapterState(context))
  }

  /**
   * Transitions to the PlayerTurnState to start the next turn.
   */
  override def goPlayTurn(): Unit = {
    context.setState(new PlayerTurnState(context))
  }

  /**
   * Transitions to the EndGameState to signify the end of the game.
   */
  override def goEndGame(): Unit = {
    context.setState(new EndGameState(context))
  }

  /**
   * Performs the necessary actions when the state's action is triggered.
   * - If the current turn is less than or equal to 4, transition to the PlayerTurnState.
   * - If the current turn is greater than 4, transition to the ChapterState.
   */
  override def doAction(): Unit = {
    if(context.getTurn <= 4){
      goPlayTurn()
    }
    else{
      goChapter()
    }
  }

}
