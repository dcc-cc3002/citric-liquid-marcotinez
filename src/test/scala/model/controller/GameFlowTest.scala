package cl.uchile.dcc.citric
package model.controller

import model.controller.states.{ChapterState, CombatState, EndGameState, EndTurnState, LandingPanelState, MovingState, PlayerTurnState, PregameState, RecoveryState}

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import cl.uchile.dcc.citric.model.norma.Norma
import cl.uchile.dcc.citric.model.norma.normalevels.{Norma5, Norma6}
import cl.uchile.dcc.citric.model.norma.objective.{Stars, Wins}

class GameFlowTest extends munit.FunSuite {
  val gameController = new GameController
  val gameController2 = new GameController
  val gameController3 = new GameController
  val chapterState = new ChapterState(gameController)
  val combatState = new CombatState(gameController)
  val endGameState = new EndGameState(gameController)
  val landingPanelState = new LandingPanelState(gameController)
  val movingState = new MovingState(gameController)
  val playerTurnState = new PlayerTurnState(gameController)
  val pregameState = new PregameState(gameController)
  val recoveryState = new RecoveryState(gameController)
  val endTurnState = new EndTurnState(gameController)


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

  test("If a player reach norma 6 the game ends (with Stars)"){
    //en el primer doAction se inicializa el juego (Pregame doAction)
    gameController2.getState.doAction()
    //Este doAction corresponde al ChapterState
    gameController2.getState.doAction()
    //Este doAction corresponde al PlayerTurnState
    gameController2.getState.doAction()
    //Este doAction corresponde al MovingState
    gameController2.getState.doAction()
    //Este doAction corresponde al LandingPanelState
    //ponemos las condiciones para que suba a norma 6
    val norma: Norma = new Norma5
    norma.setObjetive(new Stars)
    gameController2.getPlayerTurn.setNorma(norma)
    gameController2.getPlayerTurn.starBonus(200)
    gameController2.getState.doAction()
    //forzamos el normaCheck, ya que el panel donde cae el jugador no es conocido.
    gameController2.getPlayerTurn.normaCheck()
    assert(gameController2.getWinner.isInstanceOf[PlayerCharacter])
  }

  test("If a player reach norma 6 the game ends (with victories)") {
    //PregameState
    gameController3.getState.doAction()
    //Este doAction corresponde al ChapterState
    gameController3.getState.doAction()
    //Este doAction corresponde al PlayerTurnState
    gameController3.getState.doAction()
    //Este doAction corresponde al MovingState
    gameController3.getState.doAction()
    //Este doAction corresponde al LandingPanelState
    //ponemos las condiciones para que suba a norma 6
    val norma: Norma = new Norma5
    norma.setObjetive(new Wins)
    gameController3.getPlayerTurn.setNorma(norma)
    gameController3.getPlayerTurn.setVictories(15)
    gameController3.getState.doAction()
    //forzamos el normaCheck, ya que el panel donde cae el jugador no es conocido.
    gameController3.getPlayerTurn.normaCheck()
    assert(gameController3.getWinner.isInstanceOf[PlayerCharacter])
  }
}