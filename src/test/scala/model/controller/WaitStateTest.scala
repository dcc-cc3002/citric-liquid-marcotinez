package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class WaitStateTest extends munit.FunSuite {
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

  test("WaitState isWait"){
    assert(waitState.isWait)
  }

  // Possible transitions from WaitState
  test("A waitState can transition to CombatState") {
    waitState.evade()
    assert(gameController.state.isInstanceOf[CombatState])
  }
  test("A waitState can transition to CombatState") {
    waitState.defend()
    assert(gameController.state.isInstanceOf[CombatState])
  }

  // Impossible transitions from WaitState
  test("A waitState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      waitState.choosePath()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      waitState.rollDice()
    }
  }
  test("A waitState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      waitState.endCombat()
    }
  }
  test("A waitState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      waitState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      waitState.suficientRoll()
    }
  }
  test("A waitState can't transition RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      waitState.outOfCombat()
    }
  }
  test("A waitState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      waitState.newChapter()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      waitState.startGame()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      waitState.doEffect()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      waitState.insuficientRoll()
    }
  }
  test("A waitState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      waitState.normaSixReached()
    }
  }
  test("A waitState can't transition to WaitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${waitState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      waitState.attack()
    }
  }
}