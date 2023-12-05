package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class EndGameStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val playerTurnState = new PlayerTurnState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val endTurnState = new EndTurnState(gameController)

  test("EndGameState isEndGame"){
    assert(endGameState.isEndGame)
  }

  // A EndGameState can't transition to any other state
  test("A EndGameState can't transition to chapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      endGameState.goChapter()
    }
  }
  test("A EndGameState can't transition to combatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endGameState.goCombat()
    }
  }
  test("A EndGameState can't transition to endGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      endGameState.goEndGame()
    }
  }
  test("A endGameState can't transition to landingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      endGameState.goLandingPanel()
    }
  }
  test("A endGameState can't transition to movingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      endGameState.goMoving()
    }
  }
  test("A endGameState can't transition to playerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      endGameState.goPlayTurn()
    }
  }
  test("A endGameState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      endGameState.goRecovery()
    }
  }
  test("A endGameState can't transition to EndTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${endTurnState.getClass.getSimpleName}"
    ) {
      endGameState.goEndTurn()
    }
  }
}
