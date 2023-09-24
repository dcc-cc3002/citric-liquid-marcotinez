package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class DropPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11))
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11))

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
    val actualCharacters = dropPanel.getCharacters()
    assertEquals(actualCharacters, ArrayBuffer(testPlayer1))
    dropPanel.addCharacter(testPlayer2)
    val actualCharacters2 = dropPanel.getCharacters()
    assertEquals(actualCharacters2, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("A player can be removed from a panel") {
    assertEquals(dropPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    dropPanel.addCharacter(testPlayer1)
    val actualCharacters = dropPanel.getCharacters()
    assertEquals(actualCharacters, ArrayBuffer(testPlayer1))
    dropPanel.removeCharacter(testPlayer1)
    val actualCharacters2 = dropPanel.getCharacters()
    assertEquals(actualCharacters2, ArrayBuffer.empty[PlayerCharacter])
  }

  test("Each panel can be occupied by one or more players, but not the same player twice") {
    assertEquals(dropPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    dropPanel.addCharacter(testPlayer1)
    val onlyPlayer = dropPanel.getCharacters()
    assertEquals(onlyPlayer, ArrayBuffer(testPlayer1))
    dropPanel.addCharacter(testPlayer1)
    assertEquals(onlyPlayer, ArrayBuffer(testPlayer1))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel()
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: DropPanel = new DropPanel(ArrayBuffer(bonusPanel))
    assertEquals(testPanel.nextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: DropPanel = new DropPanel(ArrayBuffer(bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
    assertEquals(testPanel2.nextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }
}
