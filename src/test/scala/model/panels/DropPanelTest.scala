package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class DropPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11), 0, 1, 10)
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11), 0, 1, 10)

  // This is the object under test.
  private var dropPanel: DropPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    dropPanel = new DropPanel()
  }

  test("Each panel has a type") {
    assertEquals(dropPanel.panelType, "Drop")
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(dropPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    dropPanel.addCharacter(testPlayer1)
    assertEquals(dropPanel.characters, ArrayBuffer(testPlayer1))
    dropPanel.addCharacter(testPlayer2)
    assertEquals(dropPanel.characters, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel(testPlayer1)
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: DropPanel = new DropPanel(ArrayBuffer(bonusPanel))
    assertEquals(testPanel.nextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: DropPanel = new DropPanel(ArrayBuffer(bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
    assertEquals(testPanel2.nextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }

  test("If there is a player on the panel, they will lose an amount of stars equal to roll*norma") {
    val newPlayer = new PlayerCharacter("newPlayer", 10, 1, 1, 1, new Random(11), 10, 1, 10)
    dropPanel.addCharacter(newPlayer)
    //como el random esta fijo en 11, el primer roll siempre será 1
    dropPanel.StarDrop(newPlayer)
    //por lo tanto las estrellas restantes deberían ser 9
    assertEquals(newPlayer.stars, 9)
  }
}
