package cl.uchile.dcc.citric
package model.panels
import model.entities.character.PlayerCharacter
import model.norma.objective.{Stars, Wins}

import munit.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends FunSuite {

  private val dueño_supremo = new PlayerCharacter("goku", 10, 1, 1, 1, new Random(11))
  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11))
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11))

  // This is the object under test.
  private var homePanel: HomePanel = _
  private var homePanel2: HomePanel = _
  private var homePanel3: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    homePanel = new HomePanel(dueño_supremo)
    homePanel2 = new HomePanel(testPlayer1)
    homePanel3 = new HomePanel(testPlayer2)
  }

  test("Each panel has a type") {
    assertEquals(homePanel.panelType, "Home")
  }

  test("Each panel has a owner") {
    assertEquals(homePanel.getOwner, dueño_supremo)
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
    homePanel.addCharacter(testPlayer1)
    val actualCharacters = homePanel.getCharacters
    assertEquals(actualCharacters, List(testPlayer1))
    homePanel.addCharacter(testPlayer2)
    val actualCharacters2 = homePanel.getCharacters
    assertEquals(actualCharacters2, List(testPlayer1, testPlayer2))
  }

  test("A player can be removed from a panel") {
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
    homePanel.addCharacter(testPlayer1)
    val actualCharacters = homePanel.getCharacters
    assertEquals(actualCharacters, List(testPlayer1))
    homePanel.removeCharacter(testPlayer1)
    val actualCharacters2 = homePanel.getCharacters
    assertEquals(actualCharacters2, List.empty[PlayerCharacter])
  }

  test("Each panel can be occupied by one or more players, but not the same player twice") {
    assertEquals(homePanel.characters, ArrayBuffer.empty[PlayerCharacter])
    homePanel.addCharacter(testPlayer1)
    val onlyPlayer = homePanel.getCharacters
    assertEquals(onlyPlayer, List(testPlayer1))
    homePanel.addCharacter(testPlayer1)
    assertEquals(onlyPlayer, List(testPlayer1))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel(testPlayer1)
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: HomePanel = new HomePanel(testPlayer1)
    testPanel.addNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, List[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: HomePanel = new HomePanel(testPlayer1)
    testPanel2.addNextPanel(bonusPanel)
    assertEquals(testPanel2.getNextPanels, List[Panel](bonusPanel))
    testPanel2.addNextPanel(dropPanel)
    assertEquals(testPanel2.getNextPanels, List[Panel](bonusPanel, dropPanel))
    testPanel2.addNextPanel(encounterPanel)
    assertEquals(testPanel2.getNextPanels, List[Panel](bonusPanel, dropPanel, encounterPanel))
    testPanel2.addNextPanel(homePanel)
    assertEquals(testPanel2.getNextPanels, List[Panel](bonusPanel, dropPanel, encounterPanel, homePanel))
    testPanel2.addNextPanel(neutralPanel)
    assertEquals(testPanel2.getNextPanels, List[Panel](bonusPanel, dropPanel, encounterPanel, homePanel, neutralPanel))
  }

  test("A Next panel can be removed of a panel.") {
    val testPanel: HomePanel = new HomePanel(testPlayer1)
    testPanel.addNextPanel(homePanel)
    assertEquals(testPanel.getNextPanels, List[Panel](homePanel))
    testPanel.removeNextPanel(homePanel)
    assertEquals(testPanel.getNextPanels, List.empty[Panel])
  }

  test("A player can active the panel and be heal") {
    dueño_supremo.damage(5)
    assertEquals(dueño_supremo.getHp, 5)
    homePanel.apply(dueño_supremo)
    assertEquals(dueño_supremo.getHp, 6)
  }

  test("A player can increase their norma level with stars") {
    homePanel2(testPlayer1)
    testPlayer1.setStars(10)
    testPlayer1.getNorma.setObjetive(new Stars)
    homePanel2.apply(testPlayer1)
    assertEquals(testPlayer1.getNormaLevel, 2)
  }

  test("A player can increase their norma level with wins") {
    homePanel3(testPlayer2)
    testPlayer2.setVictories(1)
    testPlayer2.getNorma.setObjetive(new Wins)
    homePanel3.apply(testPlayer2)
    assertEquals(testPlayer2.getNormaLevel, 2)
  }

  test("A player may not have a sufficient number of wins to increase their Norma.") {
    val player = new PlayerCharacter("player", 10, 1, 1, 1, new Random(11))
    player.setVictories(0)
    player.getNorma.setObjetive(new Wins)
    homePanel.apply(player)
    assertEquals(player.getNormaLevel, 1)
  }
}
