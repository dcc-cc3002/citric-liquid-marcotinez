package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}

class MovingStateTest extends munit.FunSuite{
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

  test("MovingState isMoving"){
    assert(movingState.isMoving)
  }

  // Possible transitions from MovingState
  test("A movingState can transition to CombatState") {
    movingState.stopMovement()
    assert(gameController.state.isInstanceOf[CombatState])
  }
  test("A movingState can transition to CombatState") {
    movingState.outOfMovements()
    assert(gameController.state.isInstanceOf[CombatState])
  }
  test("A movingState can transition to MovingState") {
    movingState.choosePath()
    assert(gameController.state.isInstanceOf[MovingState])
  }

  // Impossible transitions from MovingState

  test("A movingState can't transition to LandingPanelState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${landingPanelState.getClass.getSimpleName}"
    ) {
      movingState.endCombat()
    }
  }
  test("A movingState can't transition to waitState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${waitState.getClass.getSimpleName}"
    ) {
      movingState.attack()
    }
  }
  test("A movingState can't transition to ChapterState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      movingState.newChapter()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      movingState.startGame()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      movingState.doEffect()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${chapterState.getClass.getSimpleName}"
    ) {
      movingState.insuficientRoll()
    }
  }
  test("A movingState can't transition to EndGameState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${endGameState.getClass.getSimpleName}"
    ) {
      movingState.normaSixReached()
    }
  }
  test("A movingState can't transition to PlayerTurnState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      movingState.playTurn()
    }
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${playerTurnState.getClass.getSimpleName}"
    ) {
      movingState.suficientRoll()
    }
  }
  test("A movingState can't transition to RecoveryState") {
    interceptMessage[AssertionError](
      s"Cannot transition from ${movingState.getClass.getSimpleName} to ${recoveryState.getClass.getSimpleName}"
    ) {
      movingState.outOfCombat()
    }
  }
}
