package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeutralPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11), 0, 1, 10)
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11), 0, 1, 10)

  // This is the object under test.
  private var neutralPanel: NeutralPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    neutralPanel = new NeutralPanel()
  }

  test("Each panel has a type") {
    assertEquals(neutralPanel.panelType, "Neutral")
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(neutralPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    neutralPanel.addCharacter(testPlayer1)
    assertEquals(neutralPanel.characters, ArrayBuffer(testPlayer1))
    neutralPanel.addCharacter(testPlayer2)
    assertEquals(neutralPanel.characters, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel(testPlayer1)
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: NeutralPanel = new NeutralPanel(ArrayBuffer(bonusPanel))
    assertEquals(testPanel.nextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: NeutralPanel = new NeutralPanel(ArrayBuffer(bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
    assertEquals(testPanel2.nextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }
}
