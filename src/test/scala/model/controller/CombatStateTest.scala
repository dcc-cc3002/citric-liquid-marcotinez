package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class CombatStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val movingState = new MovingState(gameController)
  private val playerTurnState = new PlayerTurnState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val EndTurn = new EndTurnState(gameController)

  test("CombatState isCombat"){
    assert(combatState.isCombat)
  }

  // Possible transitions from CombatState
  test("A combatState can transition to LandingPanelState") {
    combatState.goLandingPanel()
    assert(gameController.getState.isInstanceOf[LandingPanelState])
  }

  // Impossible transitions from CombatState
  test("A combatState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      combatState.goChapter()
    }
  }
  test("A combatState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      combatState.goEndGame()
    }
  }
  test("A combatState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      combatState.goMoving()
    }
  }
  test("A combatState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      combatState.goPlayTurn()
    }
  }
  test("A combatState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      combatState.goRecovery()
    }
  }
  test("A combatState can't transition to EndTurnState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${EndTurn.getClass.getSimpleName}"
    ) {
      combatState.goEndTurn()
    }
  }
}
