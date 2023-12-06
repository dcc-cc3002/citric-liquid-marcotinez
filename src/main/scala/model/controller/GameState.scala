package cl.uchile.dcc.citric
package model.controller

/**
 * Represents a generic game state with methods for transitioning between states and checking the current state type.
 *
 * @param context The game controller managing the game state.
 */
class GameState(var context: GameController) {

  /**
   * Sets the context of the game state.
   *
   * @param newContext The new game controller context.
   */
  def setContext(newContext: GameController): Unit = {
    context = newContext
  }

  /**
   * Sets the state of the game.
   *
   * @param aState The target game state to transition to.
   */
  def setState(aState: GameState): Unit = {
    context.setState(aState)
  }

  /**
   * Private method for handling transition errors.
   *
   * @param targetState The target state that was attempted to transition to.
   */
  private def transitionError(targetState: String): Unit = throw new AssertionError(s"Cannot transition from ${this.getClass.getSimpleName} to $targetState")

  /**
   * Transition to the Chapter state.
   */
  def goChapter(): Unit = transitionError("ChapterState")

  /**
   * Transition to the EndGame state.
   */
  def goEndGame(): Unit = transitionError("EndGameState")

  /**
   * Transition to the LandingPanel state.
   */
  def goLandingPanel(): Unit = transitionError("LandingPanelState")

  /**
   * Transition to the PlayerTurn state.
   */
  def goPlayTurn(): Unit = transitionError("PlayerTurnState")

  /**
   * Transition to the Recovery state.
   */
  def goRecovery(): Unit = transitionError("RecoveryState")

  /**
   * Transition to the Moving state.
   */
  def goMoving(): Unit = transitionError("MovingState")

  /**
   * Transition to the Combat state.
   */
  def goCombat(): Unit = transitionError("CombatState")

  /**
   * Transition to the EndTurn state.
   */
  def goEndTurn(): Unit = transitionError("EndTurnState")

  /**
   * Checks if the current state is a Chapter state.
   *
   * @return True if the current state is a Chapter state, false otherwise.
   */
  def isChapter: Boolean = false

  /**
   * Checks if the current state is a Combat state.
   *
   * @return True if the current state is a Combat state, false otherwise.
   */
  def isCombat: Boolean = false

  /**
   * Checks if the current state is an EndGame state.
   *
   * @return True if the current state is an EndGame state, false otherwise.
   */
  def isEndGame: Boolean = false

  /**
   * Checks if the current state is a LandingPanel state.
   *
   * @return True if the current state is a LandingPanel state, false otherwise.
   */
  def isLandingPanel: Boolean = false

  /**
   * Checks if the current state is a Moving state.
   *
   * @return True if the current state is a Moving state, false otherwise.
   */
  def isMoving: Boolean = false

  /**
   * Checks if the current state is a PlayerTurn state.
   *
   * @return True if the current state is a PlayerTurn state, false otherwise.
   */
  def isPlayerTurn: Boolean = false

  /**
   * Checks if the current state is a Pregame state.
   *
   * @return True if the current state is a Pregame state, false otherwise.
   */
  def isPregame: Boolean = false

  /**
   * Checks if the current state is a Recovery state.
   *
   * @return True if the current state is a Recovery state, false otherwise.
   */
  def isRecovery: Boolean = false

  /**
   * Checks if the current state is an EndTurn state.
   *
   * @return True if the current state is an EndTurn state, false otherwise.
   */
  def isEndTurn: Boolean = false

  /**
   * Performs a generic action for the current state.
   */
  def doAction(): Unit = throw new AssertionError(s"Cannot do action from ${this.getClass.getSimpleName}")
}


