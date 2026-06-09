package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class EndGameStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val endGameState = new EndGameState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val playerTurnState = new PlayerTurnState(gameController)
  private val recoveryState = new RecoveryState(gameController)
  private val waitState = new WaitState(gameController)

  test("EndGameState isEndGame"){
    assert(endGameState.isEndGame)
  }

  // A EndGameState can't transition to any other state
  test("A endGameState can't transition to chapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      endGameState.newChapter()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      endGameState.startGame()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      endGameState.doEffect()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      endGameState.insuficientRoll()
    }
  }
  test("A endGameState can't transition to combatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endGameState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endGameState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endGameState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      endGameState.defend()
    }
  }
  test("A endGameState can't transition to endGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      endGameState.normaSixReached()
    }
  }
  test("A endGameState can't transition to landingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      endGameState.endCombat()
    }
  }
  test("A endGameState can't transition to movingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      endGameState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      endGameState.choosePath()
    }
  }
  test("A endGameState can't transition to playerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      endGameState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      endGameState.suficientRoll()
    }
  }
  test("A endGameState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      endGameState.outOfCombat()
    }
  }
  test("A endGameState can't transition to waitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${endGameState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      endGameState.attack()
    }
  }
}
