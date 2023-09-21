package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.Panel
import cl.uchile.dcc.citric.model.entities.PlayerCharacter
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends FunSuite {

  private val owner = new PlayerCharacter("owner", 10, 1, 1, 1, new Random(11), 0, 1, 10)
  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11), 0, 1, 10)
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11), 0, 1, 10)

  // This is the object under test.
  private var homePanel: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    homePanel = new HomePanel(owner)
  }

  test("Each panel has a type") {
    assertEquals(homePanel.panelType, "Home")
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
    homePanel.addCharacter(testPlayer1)
    assertEquals(homePanel.characters, ArrayBuffer(testPlayer1))
    homePanel.addCharacter(testPlayer2)
    assertEquals(homePanel.characters, ArrayBuffer(testPlayer1, testPlayer2))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel(owner)
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: HomePanel = new HomePanel(ArrayBuffer(bonusPanel), owner)
    assertEquals(testPanel.nextPanels, ArrayBuffer[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: HomePanel = new HomePanel(ArrayBuffer(bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel), owner)
    assertEquals(testPanel2.nextPanels, ArrayBuffer[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }

  test("Each panel has an owner") {
    val homePanel: HomePanel = new HomePanel(owner)
    assertEquals(homePanel.owner, owner)
  }

  test("If a player activates a panel, they will be rewarded") {
    val homePanel: HomePanel = new HomePanel(owner)
    homePanel.addCharacter(owner)
    homePanel.reward(owner)
    assertEquals(owner.hp, 11)
  }
}
