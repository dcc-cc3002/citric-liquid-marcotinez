package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, EndTurnState}

class PregameStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val playerTurnState = new PlayerTurnState(gameController)
  private val pregameState = new PregameState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val waitState = new EndTurnState(gameController)

  test("PregameState isPregame"){
    assert(pregameState.isPregame)
  }

  // Possible transitions from PregameState
  test("PregameState can transition to ChapterState"){
      pregameState.goChapter()
      assert(gameController.getState.isInstanceOf[ChapterState])
  }

  // Impossible Transitions from PregameState
  test("A pregameState can't transition to EndGameState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ){
      pregameState.goEndGame()
    }
  }

test("A pregameState can't transition to combatState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ){
      pregameState.goCombat()
    }
  }

test("A pregameState can't transition to landingPanelState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ){
      pregameState.goLandingPanel()
    }
  }

test("A pregameState can't transition to movingState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ){
      pregameState.goMoving()
    }
  }

test("A pregameState can't transition to PlayerTurnState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ){
      pregameState.goPlayTurn()
    }
  }

test("A pregameState can't transition to RecoveryState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ){
      pregameState.goRecovery()
    }
  }

test("A pregameState can't transition to EndTurnState"){
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ){
      pregameState.goEndTurn()
    }
  }

}
