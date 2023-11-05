package cl.uchile.dcc.citric
package model.panels
import model.Panel
import model.entities.character.PlayerCharacter
import model.norma.objective.Wins
import munit.FunSuite
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class HomePanelTest extends FunSuite {

  private val dueño_supremo = new PlayerCharacter("goku", 10, 1, 1, 1, new Random(11))
  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11))
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11))

  // This is the object under test.
  private var homePanel: HomePanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    homePanel = new HomePanel()
  }

  test("Each panel has a type") {
    assertEquals(homePanel.panelType, "Home")
  }

  test("Each panel has a owner") {
    assertEquals(homePanel.getOwner, null)
    homePanel.setOwner(dueño_supremo)
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
    val homePanel: Panel = new HomePanel()
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: HomePanel = new HomePanel()
    testPanel.addNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, List[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: HomePanel = new HomePanel()
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
    val testPanel: HomePanel = new HomePanel()
    testPanel.addNextPanel(homePanel)
    assertEquals(testPanel.getNextPanels, List[Panel](homePanel))
    testPanel.removeNextPanel(homePanel)
    assertEquals(testPanel.getNextPanels, List.empty[Panel])
  }

  test("the panel can apply something") {
    val testPanel: HomePanel = new HomePanel()
    val other: PlayerCharacter = new PlayerCharacter("other", 10, 1, 1, 1, new Random(11))
    val texto: String = "Encounter activated"
    assertEquals(testPanel.apply(other), print("HomePanel activated"))
  }

  test("A player can active the panel and be heal") {
    val player = new PlayerCharacter("player", 10, 1, 1, 1, new Random(11))
    player.damage(5)
    assertEquals(player.getHp, 5)
    homePanel.apply(player)
    assertEquals(player.getHp, 6)
  }

  test("A player can increase their norma level with stars") {
    val player = new PlayerCharacter("player", 10, 1, 1, 1, new Random(11))
    player.starBonus(10)
    homePanel.apply(player)
    assertEquals(player.getNormaLevel, 2)
  }

  test("A player can increase their norma level with wins") {
    val player = new PlayerCharacter("player", 10, 1, 1, 1, new Random(11))
    player.setVictories(1)
    player.getNorma.setObjetive(new Wins)
    homePanel.apply(player)
    assertEquals(player.getNormaLevel, 2)
  }

  test("A player may not have a sufficient number of wins to increase their Norma.") {
    val player = new PlayerCharacter("player", 10, 1, 1, 1, new Random(11))
    player.setVictories(0)
    player.getNorma.setObjetive(new Wins)
    homePanel.apply(player)
    assertEquals(player.getNormaLevel, 1)
  }
}
