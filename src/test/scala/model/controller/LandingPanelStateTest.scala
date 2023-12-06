package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class LandingPanelStateTest extends munit.FunSuite {
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

  test("LandingPanelState isLandingPanel"){
    assert(landingPanelState.isLandingPanel)
  }

  // Possible transitions from LandingPanelState
  test("A landingPanelState can transition to EndTurnState") {
    landingPanelState.goEndTurn()
    assert(gameController.getState.isInstanceOf[EndTurnState])
  }

  // Impossible transitions from LandingPanelState
  test("A landingPanelState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      landingPanelState.goChapter()
    }
  }
  test("A landingPanelState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      landingPanelState.goCombat()
    }
  }
  test("A landingPanelState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      landingPanelState.goEndGame()
    }
  }
  test("A landingPanelState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      landingPanelState.goMoving()
    }
  }
  test("A landingPanelState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      landingPanelState.goPlayTurn()
    }
  }
  test("A landingPanelState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      landingPanelState.goRecovery()
    }
  }
}
