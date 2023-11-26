package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class CombatStateTest extends munit.FunSuite {
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

  test("CombatState isCombat"){
    assert(combatState.isCombat)
  }

  // Possible transitions from CombatState
  test("A combatState can transition to WaitState") {
    combatState.attack()
    assert(gameController.state.isInstanceOf[WaitState])
  }
  test("A combatState can transition to LandingPanelState") {
    combatState.endCombat()
    assert(gameController.state.isInstanceOf[LandingPanelState])
  }

  // Impossible transitions from CombatState
  test("A combatState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      combatState.newChapter()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      combatState.doEffect()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      combatState.insuficientRoll()
    }
  }
  test("A combatState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      combatState.normaSixReached()
    }
  }
  test("A combatState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      combatState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      combatState.choosePath()
    }
  }
  test("A combatState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      combatState.suficientRoll()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      combatState.playTurn()
    }
  }
  test("A combatState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${combatState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      combatState.outOfCombat()
    }
  }

}
