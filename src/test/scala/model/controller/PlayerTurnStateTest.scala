package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class PlayerTurnStateTest extends munit.FunSuite {
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

  test("PlayerTurnState isPlayerTurn"){
    assert(playerTurnState.isPlayerTurn)
  }

  // Possible transitions from PlayerTurnState
  test("A playerTurnState can transition to MovingState") {
    playerTurnState.goMoving()
    assert(gameController.getState.isInstanceOf[MovingState])
  }
  test("A playerTurnState can transition to RecoveryState") {
    playerTurnState.goRecovery()
    assert(gameController.getState.isInstanceOf[RecoveryState])
  }

  // Impossible transitions from PlayerTurnState
  test("A playerTurnState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      playerTurnState.goChapter()
    }
  }
  test("A playerTurnState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      playerTurnState.goCombat()
    }
  }
  test("A playerTurnState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      playerTurnState.goEndGame()
    }
  }
  test("A playerTurnState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      playerTurnState.goLandingPanel()
    }
  }
  test("A playerTurnState can't transition to EndTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      playerTurnState.goEndTurn()
    }
  }

}
