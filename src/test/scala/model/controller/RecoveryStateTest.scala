package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class RecoveryStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val gameState = new GameState(gameController)
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val waitState = new EndTurnState(gameController)

  test("RecoveryState isRecovery"){
    assert(recoveryState.isRecovery)
  }

  // Possible transitions from RecoveryState
  test("A recoveryState can transition to PlayerTurnState") {
    recoveryState.goPlayTurn()
    assert(gameController.getState.isInstanceOf[PlayerTurnState])
  }

  // Impossible transitions from RecoveryState
  test("A recoveryState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      recoveryState.goChapter()
    }
  }
  test("A recoveryState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      recoveryState.goCombat()
    }
  }
  test("A recoveryState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      recoveryState.goEndGame()
    }
  }
  test("A recoveryState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      recoveryState.goLandingPanel()
    }
  }
  test("A recoveryState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      recoveryState.goMoving()
    }
  }
  test("A recoveryState can't transition to EndTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      recoveryState.goEndTurn()
    }
  }

}
