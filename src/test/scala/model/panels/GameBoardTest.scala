package cl.uchile.dcc.citric
package model.panels

import model.entities.character.PlayerCharacter

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class GameBoardTest extends FunSuite {
  // Define some PlayerCharacters for testing
  val player1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1)
  val player2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1)
  val players: ArrayBuffer[PlayerCharacter] = ArrayBuffer(player1, player2)

  // Create a GameBoard instance for testing
  val board = new GameBoard(players)

  test("GameBoard initializes panels correctly") {
    assert(board.getFirstPanel.isInstanceOf[HomePanel])
    assert(board.getActualPanel.isInstanceOf[Panel])
  }

  test("GameBoard links panels correctly") {
    assert(board.getFirstPanel.getNextPanels.head.isInstanceOf[HomePanel])
    assert(board.getActualPanel.getNextPanels.head.isInstanceOf[Panel])
  }

  test("GameBoard applies panel effects correctly") {
    val initialPlayer1Panel = player1.getPanel
    board.getFirstPanel.apply(player1)
    assert(player1.getPanel == initialPlayer1Panel)
  }
}
