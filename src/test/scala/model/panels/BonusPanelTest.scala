package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class BonusPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11))
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11))

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
    val actualCharacters = bonusPanel.getCharacters
    assertEquals(actualCharacters, ArrayBuffer(testPlayer1))
    bonusPanel.addCharacter(testPlayer2)
    val actualCharacters2 = bonusPanel.getCharacters
    assertEquals(actualCharacters2, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("A player can be removed from a panel") {
    assertEquals(bonusPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    bonusPanel.addCharacter(testPlayer1)
    val actualCharacters = bonusPanel.getCharacters
    assertEquals(actualCharacters, ArrayBuffer(testPlayer1))
    bonusPanel.removeCharacter(testPlayer1)
    val actualCharacters2 = bonusPanel.getCharacters
    assertEquals(actualCharacters2, ArrayBuffer.empty[PlayerCharacter])
  }

  test("Each panel can be occupied by one or more players, but not the same player twice") {
    assertEquals(bonusPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    bonusPanel.addCharacter(testPlayer1)
    val onlyPlayer = bonusPanel.getCharacters
    assertEquals(onlyPlayer, ArrayBuffer(testPlayer1))
    bonusPanel.addCharacter(testPlayer1)
    assertEquals(onlyPlayer, ArrayBuffer(testPlayer1))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel()
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: BonusPanel = new BonusPanel()
    testPanel.addNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: BonusPanel = new BonusPanel()
    testPanel2.addNextPanel(bonusPanel)
    assertEquals(testPanel2.getNextPanels, ArrayBuffer[Panel](bonusPanel))
    testPanel2.addNextPanel(dropPanel)
    assertEquals(testPanel2.getNextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel))
    testPanel2.addNextPanel(encounterPanel)
    assertEquals(testPanel2.getNextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel))
    testPanel2.addNextPanel(homePanel)
    assertEquals(testPanel2.getNextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel))
    testPanel2.addNextPanel(neutralPanel)
    assertEquals(testPanel2.getNextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }

  test("A Nextpanel can be removed of a panel.") {
    val testPanel: BonusPanel = new BonusPanel()
    testPanel.addNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, ArrayBuffer[Panel](bonusPanel))
    testPanel.removeNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, ArrayBuffer.empty[Panel])
  }
}
