package cl.uchile.dcc.citric
package model.controller

import model.controller.states.PregameState


class GameControllerTest extends munit.FunSuite {
  private val gameController = new GameController()

  override def beforeEach(context: BeforeEach): Unit = {
    gameController.setState(new PregameState(gameController))
  }

  test("GameController starts in PregameState") {
    assert(gameController.getState.isInstanceOf[PregameState])
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
    assert(!gameController.isEndTurn)
  }

  test("We have initial values") {
    assert(gameController.getTurn == 0)
    assert(gameController.getChapter == 1)
    assert(gameController.getPlayers.isEmpty)
    assert(gameController.getPlayersTurnOrder.isEmpty)
    assert(gameController.getEnemy.isEmpty)
    assert(gameController.getWinner == null)
    assert(gameController.getGameBoard == null)
  }

  test("We can set recoveryState") {
    gameController.setRecoveryPlayer(true)
    assert(gameController.getRecoveryPlayer)
  }
}
