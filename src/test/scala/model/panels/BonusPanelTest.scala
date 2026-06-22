package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class BonusPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11), 0, 1, 10)
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11), 0, 1, 10)

  // This is the object under test.
  private var bonusPanel: BonusPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    bonusPanel = new BonusPanel()
  }

  test("Each panel has a type") {
    assertEquals(bonusPanel.panelType, "Bonus")
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(bonusPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    bonusPanel.addCharacter(testPlayer1)
    assertEquals(bonusPanel.characters, ArrayBuffer(testPlayer1))
    bonusPanel.addCharacter(testPlayer2)
    assertEquals(bonusPanel.characters, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel(testPlayer1)
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: BonusPanel = new BonusPanel(ArrayBuffer(bonusPanel))
    assertEquals(testPanel.nextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: BonusPanel = new BonusPanel(ArrayBuffer(bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
    assertEquals(testPanel2.nextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }

  test("If there is a player on the panel, they will gain a amount of stars equal to min(roll*norma, roll*3)") {
    //caso 1: roll*norma < roll*3
    val newPlayer = new PlayerCharacter("newPlayer", 10, 1, 1, 1, new Random(11), 10, 1, 10)
    bonusPanel.addCharacter(newPlayer)
    //como el random esta fijo en 11, el primer roll siempre será 1
    bonusPanel.StarBonus(newPlayer)
    //roll*norma = 1, por lo tanto las estrellas finales deberían ser 11
    assertEquals(newPlayer.stars, 11)

    //caso 2: roll*norma > roll*3
    val newPlayer2 = new PlayerCharacter("newPlayer2", 10, 1, 1, 1, new Random(11), 10, 10, 10)
    bonusPanel.addCharacter(newPlayer2)
    //como el random esta fijo en 11, el primer roll siempre será 1
    bonusPanel.StarBonus(newPlayer2)
    //roll*3= 3, por lo tanto las estrellas finales deberían ser 13
    assertEquals(newPlayer2.stars, 13)
  }
}
