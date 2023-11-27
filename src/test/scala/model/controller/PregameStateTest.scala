package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class PregameStateTest extends munit.FunSuite {
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
  private val waitState = new WaitState(gameController)

  test("PregameState isPregame"){
    assert(pregameState.isPregame)
  }

  // Possible transitions from PregameState
  test("A pregameState can transition to ChapterState") {
    pregameState.startGame()
    assert(gameController.state.isInstanceOf[ChapterState])
  }

  // Impossible transitions from PregameState
  test("A pregameState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      pregameState.normaSixReached()
    }
  }
  test("A pregameState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      pregameState.outOfCombat()
    }
  }
  test("A pregameState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      pregameState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      pregameState.suficientRoll()
    }
  }
  test("A pregameState can't transition to landingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      pregameState.endCombat()
    }
  }
  test("A pregameState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      pregameState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      pregameState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      pregameState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      pregameState.defend()
    }
  }
  test("A pregameState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      pregameState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      pregameState.choosePath()
    }
  }
  test("A pregameState can't transition to WaitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${pregameState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      pregameState.attack()
    }
  }
}
