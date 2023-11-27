package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

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
  private val waitState = new WaitState(gameController)

  test("LandingPanelState isLandingPanel"){
    assert(landingPanelState.isLandingPanel)
  }

  // Possible transitions from LandingPanelState
  test("A landingPanelState can transition to ChapterState") {
    landingPanelState.doEffect()
    assert(gameController.state.isInstanceOf[ChapterState])
  }

  // Impossible transitions from LandingPanelState
  test("A landingPanelState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      landingPanelState.endCombat()
    }
  }
  test("A landingPanelState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      landingPanelState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      landingPanelState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      landingPanelState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      landingPanelState.defend()
    }
  }
  test("A landingPanelState can't transition to WaitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      landingPanelState.attack()
    }
  }
  test("A landingPanelState can't transition to MovingState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      landingPanelState.rollDice()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${movingState.getClass.getSimpleName}"
    ) {
      landingPanelState.choosePath()
    }
  }
  test("A landingPanelState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      landingPanelState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      landingPanelState.suficientRoll()
    }
  }
  test("A landingPanelState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      landingPanelState.normaSixReached()
    }
  }
  test("A landingPanelState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${landingPanelState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      landingPanelState.outOfCombat()
    }
  }
}
