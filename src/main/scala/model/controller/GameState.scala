package cl.uchile.dcc.citric
package model.controller

class GameState (var context: GameController) {

  def setContext(newContext: GameController): Unit = {
    context = newContext
  }
  def setState(aState: GameState): Unit = {
    context.setState(aState)
  }

  private def transitionError(targetState: String): Unit = throw new AssertionError(s"Cannot transition from ${this.getClass.getSimpleName} to $targetState")

  //Target State = Chapter
  def startGame(): Unit = transitionError("ChapterState")
  def newChapter(): Unit = transitionError("ChapterState")
  def doEffect(): Unit = transitionError("ChapterState")
  def insuficientRoll(): Unit = transitionError("ChapterState")

  //Target State = EndGame
  def normaSixReached(): Unit = transitionError("EndGameState")

  //Target State = LandingPanel
  def endCombat(): Unit = transitionError("LandingPanelState")

  //Target State = PlayerTurn
  def playTurn(): Unit = transitionError("PlayerTurnState")
  def suficientRoll(): Unit = transitionError("PlayerTurnState")

  //Target State = Recovery
  def outOfCombat(): Unit = transitionError("RecoveryState")

  //Target State = Moving
  def rollDice(): Unit = transitionError("MovingState")
  def choosePath(): Unit = transitionError("MovingState")

  //Target State = Combat
  def stopMovement(): Unit = transitionError("CombatState")
  def outOfMovements(): Unit = transitionError("CombatState")
  def evade(): Unit = transitionError("CombatState")
  def defend(): Unit = transitionError("CombatState")

  //Target State = Wait
  def attack(): Unit = transitionError("WaitState")

  //States
  def isChapter: Boolean = false
  def isCombat: Boolean = false
  def isEndGame: Boolean = false
  def isLandingPanel: Boolean = false
  def isMoving: Boolean = false
  def isPlayerTurn: Boolean = false
  def isPregame: Boolean = false
  def isRecovery: Boolean = false
  def isWait: Boolean = false






}
