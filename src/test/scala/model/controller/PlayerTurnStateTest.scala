package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class PlayerTurnStateTest extends munit.FunSuite {
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

  test("PlayerTurnState isPlayerTurn"){
    assert(playerTurnState.isPlayerTurn)
  }

  // Possible transitions from PlayerTurnState
  test("A playerTurnState can transition to movingState") {
    playerTurnState.rollDice()
    assert(gameController.state.isInstanceOf[MovingState])
  }

  // Impossible transitions from PlayerTurnState
  test("A playerTurnState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      playerTurnState.endCombat()
    }
  }
  test("A playerTurnState can't transition to CombatState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      playerTurnState.stopMovement()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      playerTurnState.outOfMovements()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      playerTurnState.evade()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${combatState.getClass.getSimpleName}"
    ) {
      playerTurnState.defend()
    }
  }
  test("A playerTurnState can't transition to WaitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      playerTurnState.attack()
    }
  }
  test("A playerTurnState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      playerTurnState.newChapter()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      playerTurnState.startGame()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      playerTurnState.doEffect()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      playerTurnState.insuficientRoll()
    }
  }
  test("A playerTurnState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      playerTurnState.normaSixReached()
    }
  }
  test("A playerTurnState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      playerTurnState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      playerTurnState.suficientRoll()
    }
  }
  test("A playerTurnState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${playerTurnState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      playerTurnState.outOfCombat()
    }
  }
}
