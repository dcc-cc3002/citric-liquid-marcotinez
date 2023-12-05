package cl.uchile.dcc.citric
package model.controller

import model.controller.states.PregameState
import model.entities.character.PlayerCharacter
import model.panels.{GameBoard, Panel}
import model.entities.GameCharacter

import scala.collection.immutable.Queue
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameController {
  /** The current state of the game. */
  private var state: GameState = new PregameState(this)
  /** The player whose turn it is. */
  private var playerTurn: PlayerCharacter = _
  /** The winner of the game. */
  private var winner: PlayerCharacter = _
  /** The game board. */
  private var gameBoard: GameBoard = _
  /** The panel where the player is. */
  private var actualPanel: Panel = _
  /** The enemy in the game, it can be a WildUnit or a PlayerCharacter. */
  private var enemy: Option[GameCharacter] = None
  /** The players in the game. */
  private var players: ArrayBuffer[PlayerCharacter] = ArrayBuffer.empty[PlayerCharacter]
  /** The turn order of the players. */
  private var playersTurnOrder: List[PlayerCharacter] = List.empty[PlayerCharacter]
  /** The current turn of the game. */
  private var turn: Int = 0
  /** The current chapter of the game. */
  private var chapter: Int = 1
  /** "Variable indicating whether the current player has returned from recovery." */
  private var recoveryPlayer: Boolean = false

  /**
   * Gets the current player whose turn it is.
   *
   * @return The current player character.
   */
  def getPlayerTurn: PlayerCharacter = playerTurn

  /**
   * Checks whether a player was in the recovery phase.
   *
   * @return True if a player was in recovery, false otherwise.
   */
  def getRecoveryPlayer: Boolean = recoveryPlayer

  /**
   * Gets the current turn number in the game.
   *
   * @return The current turn number.
   */
  def getTurn: Int = turn

  /**
   * Gets the current state of the game.
   *
   * @return The current game state.
   */
  def getState: GameState = state

  /**
   * Gets the current chapter number in the game.
   *
   * @return The current chapter number.
   */
  def getChapter: Int = chapter

  /**
   * Gets the game board representing the layout of the game.
   *
   * @return The game board instance.
   */
  def getGameBoard: GameBoard = gameBoard

  /**
   * Gets the list of all players in the game.
   *
   * @return An array buffer containing all player characters.
   */
  def getPlayers: ArrayBuffer[PlayerCharacter] = players

  /**
   * Gets the list of players in their turn order.
   *
   * @return A list containing player characters in their turn order.
   */
  def getPlayersTurnOrder: List[PlayerCharacter] = playersTurnOrder

  /**
   * Gets the current enemy.
   *
   * @return An optional game character representing the enemy, or None if there is no enemy.
   */
  def getEnemy: Option[GameCharacter] = enemy

  /**
   * Gets the player character who is the winner of the game.
   *
   * @return The winning player character.
   */
  def getWinner: PlayerCharacter = winner

  /**
   * Sets the flag indicating whether a player has completed the recovery phase.
   *
   * @param value The boolean value to set for the recovery completion flag.
   */
  def setRecoveryPlayer(value: Boolean): Unit = recoveryPlayer = value

  /** Method responsible for changing the game state. */
  def setState(newState: GameState): Unit = {
    state = newState
    newState.setContext(this)
  }

  /** Method related to the observer, responsible for updating the winner and ending the game. */
  def update(player: PlayerCharacter): Unit = {
    winner = player
    goEndGame()
  }

  /** Method responsible for initializing the game, creating the desired number of players,
   * initializing the board, and generating a random turn order.
   *
   * @param playersAmount the number of players in the game.
   * */
  def startGame(playersAmount: Int): Unit = {
    //we create the players, max 4 players
    val range: Int = math.min(playersAmount, 4)
    for (i <- 1 to range) {
      //generic player
      val player = new PlayerCharacter(s"Player$i", 4, 1, 1, 1)
      player.register(this)
      players += player
    }
    //we create the game board
    gameBoard = new GameBoard(players)

    //we set a random turn order for the players
    players = Random.shuffle(players)
    playersTurnOrder = players.toList
  }

  /**
   * Adds a new chapter to the game, updating the chapter count and applying bonuses to players.
   */
  def addChapter(): Unit = {
    chapter += 1
    for (player <- playersTurnOrder) {
      player.starBonus((chapter/5)+1)
    }
  }

  /**
   * Initiates a new turn in the game, updating the player turn, resetting the enemy,
   * and advancing the turn counter.
   */
  def newTurn(): Unit = {
    if(turn > 4) turn = 0
    playerTurn = playersTurnOrder(turn)
    enemy = None
    turn += 1
  }

  /**
   * Attempts the recovery phase for the current player's turn,
   * calling the recovery method on the player.
   */
  def tryRecovery(): Unit = {
    playerTurn.recovery()
  }

  /**
   * Moves the current player's character on the game board based on a dice roll.
   * Updates the player's position, encounters enemies or other players on the new panel.
   */
  def movePlayer(): Unit = {
    val roll: Int = playerTurn.rollDice()
    var actualPanel = playerTurn.getPanel
    for(i <- 1 to roll){
      if(actualPanel == gameBoard.getLastPanel){
        actualPanel = gameBoard.getFirstPanel
      }
      else {
        actualPanel = actualPanel.getNextPanels.head
      }
    }
    //there is no one in the panel
    if(actualPanel.characters.nonEmpty){
      playerTurn.setPanel(actualPanel)
      enemy = Some(actualPanel.characters(0))
    }
    //Encounter Panel (there is a wild unit)
    if(actualPanel.getWildUnitEnemy.isDefined){
      playerTurn.setPanel(actualPanel)
      enemy = actualPanel.getWildUnitEnemy
    }
    //There is a player in the panel
    else{
      playerTurn.setPanel(actualPanel)
    }
  }

  /**
   * Initiates combat between the current player and the encountered enemy.
   * Continues the combat loop until either the player or the enemy is defeated.
   * During each iteration, the player and enemy take turns attacking each other.
   */
  def combat(): Unit = {
    //combate entre player y enemy con opciones default
    while(playerTurn.enCombate && enemy.get.enCombate){
      enemy.get.attacked(playerTurn)
      playerTurn.attacked(enemy.get)
    }
  }

  /**
   * Handles the actions that occur when the current player lands on a panel.
   * Retrieves the current panel of the player and applies the panel's effects to the player.
   */
  def landingPanel(): Unit = {
    actualPanel = playerTurn.getPanel
    actualPanel.apply(playerTurn)
  }

  //Transition

  /**
   * Transitions to the Chapter state.
   */
  def goChapter(): Unit = state.goChapter()

  /**
   * Transitions to the EndGame state.
   */
  def goEndGame(): Unit = state.goEndGame()

  /**
   * Transitions to the LandingPanel state.
   */
  def goLandingPanel(): Unit = state.goLandingPanel()

  /**
   * Transitions to the PlayTurn state.
   */
  def goPlayTurn(): Unit = state.goPlayTurn()

  /**
   * Transitions to the Recovery state.
   */
  def goRecovery(): Unit = state.goRecovery()

  /**
   * Transitions to the Moving state.
   */
  def goMoving(): Unit = state.goMoving()

  /**
   * Transitions to the Combat state.
   */
  def goCombat(): Unit = state.goCombat()

  /**
   * Transitions to the EndTurn state.
   */
  def goEndTurn(): Unit = state.goEndTurn()


  //States

  /**
   * Checks if the current state is a Chapter state.
   *
   * @return True if the current state is a Chapter state, false otherwise.
   */
  def isChapter: Boolean = state.isChapter

  /**
   * Checks if the current state is a Combat state.
   *
   * @return True if the current state is a Combat state, false otherwise.
   */
  def isCombat: Boolean = state.isCombat

  /**
   * Checks if the current state is an EndGame state.
   *
   * @return True if the current state is an EndGame state, false otherwise.
   */
  def isEndGame: Boolean = state.isEndGame

  /**
   * Checks if the current state is a LandingPanel state.
   *
   * @return True if the current state is a LandingPanel state, false otherwise.
   */
  def isLandingPanel: Boolean = state.isLandingPanel

  /**
   * Checks if the current state is a Moving state.
   *
   * @return True if the current state is a Moving state, false otherwise.
   */
  def isMoving: Boolean = state.isMoving

  /**
   * Checks if the current state is a PlayerTurn state.
   *
   * @return True if the current state is a PlayerTurn state, false otherwise.
   */
  def isPlayerTurn: Boolean = state.isPlayerTurn

  /**
   * Checks if the current state is a Pregame state.
   *
   * @return True if the current state is a Pregame state, false otherwise.
   */
  def isPregame: Boolean = state.isPregame

  /**
   * Checks if the current state is a Recovery state.
   *
   * @return True if the current state is a Recovery state, false otherwise.
   */
  def isRecovery: Boolean = state.isRecovery

  /**
   * Checks if the current state is an EndTurn state.
   *
   * @return True if the current state is an EndTurn state, false otherwise.
   */
  def isEndTurn: Boolean = state.isEndTurn


}