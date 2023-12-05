package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, EndTurnState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState}

import cl.uchile.dcc.citric.model.norma.normalevels.{Norma5, Norma6}

class GameFlowTest extends munit.FunSuite {
  val gameController = new GameController
  val chapterState = new ChapterState(gameController)
  val combatState = new CombatState(gameController)
  val endGameState = new EndGameState(gameController)
  val landingPanelState = new LandingPanelState(gameController)
  val movingState = new MovingState(gameController)
  val playerTurnState = new PlayerTurnState(gameController)
  val pregameState = new PregameState(gameController)
  val recoveryState = new RecoveryState(gameController)
  val endTurnState = new EndTurnState(gameController)

  override def beforeEach(context: BeforeEach): Unit = {
    val gameController = new GameController
  }

  test("From pregame we initialize the game"){
    //en el primer doAction se inicializa el juego (Pregame doAction)
    gameController.getState.doAction()
    test("The game is in chapter 1 "){
      assert(gameController.getChapter == 1)
    }
    test("The game is in ChapterState"){
      assert(gameController.getState.isInstanceOf[ChapterState])
    }
    test("The game is in turn 0"){
      assert(gameController.getTurn == 0)
    }
    test("The game has a GameBoard"){
      assert(gameController.getGameBoard != null)
    }
    test("The game has a list of players (4 players default)"){
      assert(gameController.getPlayers.size == 4)
    }
    //Este doAction corresponde al ChapterState
    gameController.getState.doAction()
    test("The game is in ChapterState"){
      assert(gameController.getState.isInstanceOf[ChapterState])
    }
    test("The game chapter is 2"){
      assert(gameController.getChapter == 2)
    }
    test("Each player has chapter/5 + 1 stars now"){
      gameController.getPlayers.foreach(player => assert(player.getStarsAmount == 1))
    }

    //Este doAction corresponde al PlayerTurnState
    gameController.getState.doAction()
    test("The playerTurn is defined"){
      assert(gameController.getPlayerTurn != null)
    }
    test("The first player is the playerTurn"){
      assert(gameController.getPlayerTurn == gameController.getPlayersTurnOrder.head)
    }
    test("The actual enemy is not defined"){
      assert(gameController.getEnemy.isEmpty)
    }
    test("The game is in turn 1"){
      assert(gameController.getTurn == 1)
    }

    //Este doAction corresponde al MovingState
    gameController.getState.doAction()
    test("The game is in MovingState") {
      assert(gameController.getState.isInstanceOf[MovingState])
    }
    test("The first landing panel is not the same as the GameBoard first panel") {
      assert(gameController.getGameBoard.getFirstPanel != gameController.getPlayerTurn.getPanel)
    }
    //TEST CUANDO NO HAY ENEMIGO EN EL PANEL
    //Este doAction corresponde al LandingPanelState
    gameController.getState.doAction()
    test("The game is in LandingPanelState") {
      assert(gameController.getState.isInstanceOf[LandingPanelState])
    }

    //Este doAction corresponde al EndTurnState
    gameController.getState.doAction()
    test("The game is in EndTurnState") {
      assert(gameController.getState.isInstanceOf[EndTurnState])
    }

    //En este caso existen más jugadores, por lo que pasamos a playerTurnState
    gameController.getState.doAction()
    test("The game is in PlayerTurnState") {
      assert(gameController.getState.isInstanceOf[PlayerTurnState])
    }
  }

  test("If a player reach norma 6 the game ends"){
    //en el primer doAction se inicializa el juego (Pregame doAction)
    gameController.getState.doAction()
    //Este doAction corresponde al ChapterState
    gameController.getState.doAction()
    //Este doAction corresponde al PlayerTurnState
    gameController.getState.doAction()
    //Este doAction corresponde al MovingState
    gameController.getState.doAction()
    //Este doAction corresponde al LandingPanelState
    //ponemos las condiciones para que suba a norma 6
    gameController.getPlayerTurn.setNorma(new Norma5)
    gameController.getPlayerTurn.starBonus(200)

    println(gameController.getState)
    test("The game is in EndGameState") {
      assert(gameController.getState.isEndGame)
    }

  }
}