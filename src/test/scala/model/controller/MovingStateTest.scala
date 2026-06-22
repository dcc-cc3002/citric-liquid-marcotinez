package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class MovingStateTest extends munit.FunSuite{
  private val gameController = new GameController
  private val gameState = new GameState(gameController)
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val playerTurnState = new PlayerTurnState(gameController)
  private val pregameState = new PregameState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val waitState = new EndTurnState(gameController)

  test("MovingState isMoving"){
    assert(movingState.isMoving)
  }

  // Possible transitions from MovingState
  test("A movingState can transition to CombatState") {
    movingState.goCombat()
    assert(gameController.getState.isInstanceOf[CombatState])
  }
  test("A movingState can transition to LandingPanelState") {
    movingState.goLandingPanel()
    assert(gameController.getState.isInstanceOf[LandingPanelState])
  }

  // Impossible transitions from MovingState
  test("A movingState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      movingState.goChapter()
    }
  }
  test("A movingState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      movingState.goEndGame()
    }
  }
  test("A movingState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      movingState.goPlayTurn()
    }
  }
  test("A movingState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      movingState.goRecovery()
    }
  }
  test("A movingState can't transition to EndTurnState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      movingState.goEndTurn()
    }
  }
}
