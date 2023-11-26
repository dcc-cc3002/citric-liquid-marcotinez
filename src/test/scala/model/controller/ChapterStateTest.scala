package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class ChapterStateTest extends munit.FunSuite {
  private val gameController = new GameController
  private val chapterState = new ChapterState(gameController)
  private val combatState = new CombatState(gameController)
  private val landingPanelState = new LandingPanelState(gameController)
  private val movingState = new MovingState(gameController)
  private val waitState = new WaitState(gameController)

  test("ChapterState isChapter") {
    assert(chapterState.isChapter)
  }

  // Possible transitions from ChapterState
  test("A chapterState can transition to chapterState") {
    chapterState.newChapter()
    assert(gameController.state.isInstanceOf[ChapterState])
  }
  test("A chapterState can transition to endGameState") {
    chapterState.normaSixReached()
    assert(gameController.state.isInstanceOf[EndGameState])
  }
  test("A chapterState can transition to recoveryState") {
    chapterState.outOfCombat()
    assert(gameController.state.isInstanceOf[RecoveryState])
  }
  test("A chapterState can transition to playerTurnState") {
    chapterState.playTurn()
    assert(gameController.state.isInstanceOf[PlayerTurnState])
  }

  // Impossible transitions from ChapterState
  test("A chapterState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      chapterState.endCombat()
    }
  }
  test("A chapterState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      chapterState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      chapterState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      chapterState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      chapterState.defend()
    }
  }
  test("A chapterState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      chapterState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      chapterState.choosePath()
    }
  }
  test("A chapterState can't transition to WaitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${chapterState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      chapterState.attack()
    }
  }

}
