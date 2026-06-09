package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class RecoveryStateTest extends munit.FunSuite {
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

  test("RecoveryState isRecovery"){
    assert(recoveryState.isRecovery)
  }

  // Possible transitions from RecoveryState
  test("A recoveryState can transition to playerTurnState") {
    recoveryState.suficientRoll()
    assert(gameController.state.isInstanceOf[PlayerTurnState])
  }
  test("A recoveryState can transition to ChapterState") {
    recoveryState.insuficientRoll()
    assert(gameController.state.isInstanceOf[ChapterState])
  }

  // Impossible transitions from RecoveryState
  test("A recoveryState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      recoveryState.normaSixReached()
    }
  }
  test("A recoveryState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      recoveryState.outOfCombat()
    }
  }
  test("A recoveryState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      recoveryState.endCombat()
    }
  }
  test("A recoveryState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      recoveryState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      recoveryState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      recoveryState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      recoveryState.defend()
    }
  }
  test("A recoveryState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      recoveryState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      recoveryState.choosePath()
    }
  }
  test("A recoveryState can't transition to waitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${recoveryState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      recoveryState.attack()
    }
  }

}
