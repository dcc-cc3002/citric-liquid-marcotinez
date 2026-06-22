package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, EndTurnState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState}

class ChapterStateTest extends munit.FunSuite {
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
  private val endTurnState = new EndTurnState(gameController)

  test("ChapterState isChapter") {
    assert(chapterState.isChapter)
  }

  // Possible transitions from ChapterState
  test("ChapterState can change to PlayerTurnState") {
    chapterState.goPlayTurn()
    assert(gameController.getState.isInstanceOf[PlayerTurnState])
  }

  // Impossible Transitions from ChapterState
  test("A chapterState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      chapterState.goEndGame()
    }
  }

  test("A chapterState can't transition to combatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      chapterState.goCombat()
    }
  }

  test("A chapterState can't transition to landingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      chapterState.goLandingPanel()
    }
  }
  test("A chapterState can't transition to movingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      chapterState.goMoving()
    }
  }
  test("A chapterState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      chapterState.goRecovery()
    }
  }
  test("A chapterState can't transition to EndTurnState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${endTurnState.getClass.getSimpleName}"
    ) {
      chapterState.goEndTurn()
    }
  }
}
