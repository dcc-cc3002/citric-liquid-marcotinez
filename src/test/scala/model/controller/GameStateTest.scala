package cl.uchile.dcc.citric
package model.controller

class GameStateTest extends munit.FunSuite {
  private val gameController = new GameController()
  private val gameState = new GameState(gameController)

  test("All states are false by default") {
    assert(!gameState.isChapter)
    assert(!gameState.isCombat)
    assert(!gameState.isEndGame)
    assert(!gameState.isLandingPanel)
    assert(!gameState.isMoving)
    assert(!gameState.isPlayerTurn)
    assert(!gameState.isPregame)
    assert(!gameState.isRecovery)
    assert(!gameState.isWait)
  }

}
