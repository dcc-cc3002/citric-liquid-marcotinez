package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState, WaitState}


class GameControllerTest extends munit.FunSuite {
  private val gameController = new GameController()

  override def beforeEach(context: BeforeEach): Unit = {
    gameController.state = new PregameState(gameController)
  }

  test("GameController starts in PregameState") {
    assert(gameController.state.isInstanceOf[PregameState])
  }

  test("GameController can change states") {
    gameController.startGame()
    assert(gameController.state.isInstanceOf[ChapterState])
    gameController.newChapter()
    assert(gameController.state.isInstanceOf[ChapterState])
    gameController.playTurn()
    assert(gameController.state.isInstanceOf[PlayerTurnState])
    gameController.rollDice()
    assert(gameController.state.isInstanceOf[MovingState])
    gameController.choosePath()
    assert(gameController.state.isInstanceOf[MovingState])
    gameController.stopMovement()
    assert(gameController.state.isInstanceOf[CombatState])
    gameController.attack()
    assert(gameController.state.isInstanceOf[WaitState])
    gameController.evade()
    assert(gameController.state.isInstanceOf[CombatState])
    gameController.attack()
    assert(gameController.state.isInstanceOf[WaitState])
    gameController.defend()
    assert(gameController.state.isInstanceOf[CombatState])
    gameController.endCombat()
    assert(gameController.state.isInstanceOf[LandingPanelState])
    gameController.doEffect()
    assert(gameController.state.isInstanceOf[ChapterState])
    gameController.outOfCombat()
    assert(gameController.state.isInstanceOf[RecoveryState])
    gameController.insuficientRoll()
    assert(gameController.state.isInstanceOf[ChapterState])
    gameController.normaSixReached()
    assert(gameController.state.isInstanceOf[EndGameState])
  }
  test("GameController can change states: 2") {
    gameController.startGame()
    assert(gameController.state.isInstanceOf[ChapterState])
    gameController.outOfCombat()
    assert(gameController.state.isInstanceOf[RecoveryState])
    gameController.suficientRoll()
    assert(gameController.state.isInstanceOf[PlayerTurnState])
    gameController.rollDice()
    assert(gameController.state.isInstanceOf[MovingState])
    gameController.outOfMovements()
    assert(gameController.state.isInstanceOf[CombatState])
  }

  test("We know the states") {
    assert(!gameController.isChapter)
    assert(!gameController.isCombat)
    assert(!gameController.isEndGame)
    assert(!gameController.isLandingPanel)
    assert(!gameController.isMoving)
    assert(!gameController.isPlayerTurn)
    assert(gameController.isPregame)
    assert(!gameController.isRecovery)
    assert(!gameController.isWait)
  }
}
