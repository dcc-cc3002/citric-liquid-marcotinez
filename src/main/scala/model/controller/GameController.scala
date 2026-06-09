package cl.uchile.dcc.citric
package model.controller

import model.controller.states.PregameState

class GameController {

  var state: GameState = new PregameState(this)

  def setState(newState: GameState): Unit = {
    state = newState
    newState.setContext(this)
  }

  def startGame(): Unit = state.startGame()
  def newChapter(): Unit = state.newChapter()
  def rollDice(): Unit = state.rollDice()
  def choosePath(): Unit = state.choosePath()
  def doEffect(): Unit = state.doEffect()
  def playTurn(): Unit = state.playTurn()
  def normaSixReached(): Unit = state.normaSixReached()
  def outOfCombat(): Unit = state.outOfCombat()
  def insuficientRoll(): Unit = state.insuficientRoll()
  def suficientRoll(): Unit = state.suficientRoll()
  def stopMovement(): Unit = state.stopMovement()
  def outOfMovements(): Unit = state.outOfMovements()
  def endCombat(): Unit = state.endCombat()
  def attack(): Unit = state.attack()
  def evade(): Unit = state.evade()
  def defend(): Unit = state.defend()

  //States
  def isChapter: Boolean = state.isChapter
  def isCombat: Boolean = state.isCombat
  def isEndGame: Boolean = state.isEndGame
  def isLandingPanel: Boolean = state.isLandingPanel
  def isMoving: Boolean = state.isMoving
  def isPlayerTurn: Boolean = state.isPlayerTurn
  def isPregame: Boolean = state.isPregame
  def isRecovery: Boolean = state.isRecovery
  def isWait: Boolean = state.isWait


}
