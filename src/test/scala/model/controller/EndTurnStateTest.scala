package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class EndTurnStateTest extends munit.FunSuite {
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
  private val endTurn = new EndTurnState(gameController)

  test("endTurn isWait"){
    assert(endTurn.isEndTurn)
  }

  // Possible transitions from endTurn
  test("A endTurn can transition to ChapterState") {
    endTurn.goChapter()
    assert(gameController.getState.isInstanceOf[ChapterState])
  }
  test("A endTurn can transition to PlayTurnState") {
    endTurn.goPlayTurn()
    assert(gameController.getState.isInstanceOf[PlayerTurnState])
  }
  test("A endTurn can transition to EndGameState") {
    endTurn.goEndGame()
    assert(gameController.getState.isInstanceOf[EndGameState])
  }
  // Impossible transitions from endTurn
  test("A endTurn can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endTurn.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endTurn.goCombat()
    }
  }
  test("A endTurn can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endTurn.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      endTurn.goLandingPanel()
    }
  }
  test("A endTurn can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endTurn.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      endTurn.goMoving()
    }
  }
  test("A endTurn can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endTurn.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      endTurn.goRecovery()
    }
  }
}
