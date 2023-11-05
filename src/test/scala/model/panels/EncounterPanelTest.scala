package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import cl.uchile.dcc.citric.model.entities.wildunit.WildUnit
import munit.FunSuite

import javax.lang.model.element.NestingKind
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class EncounterPanelTest extends FunSuite {

  private val testPlayer1 = new PlayerCharacter("testPlayer1", 10, 1, 1, 1, new Random(11))
  private val testPlayer2 = new PlayerCharacter("testPlayer2", 10, 1, 1, 1, new Random(11))

  private var encounterPanel: EncounterPanel = _

  override def beforeEach(context: BeforeEach): Unit = {
    encounterPanel = new EncounterPanel()
  }

  test("Each panel has a type") {
    assertEquals(encounterPanel.panelType, "Encounter")
  }

  test("Each panel can be occupied by one or more players") {
    assertEquals(encounterPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    encounterPanel.addCharacter(testPlayer1)
    val actualCharacters = encounterPanel.getCharacters
    assertEquals(actualCharacters, List(testPlayer1))
    encounterPanel.addCharacter(testPlayer2)
    val actualCharacters2 = encounterPanel.getCharacters
    assertEquals(actualCharacters2, List(testPlayer1, testPlayer2))
  }

  test("A player can be removed from a panel") {
    assertEquals(encounterPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    encounterPanel.addCharacter(testPlayer1)
    val actualCharacters = encounterPanel.getCharacters
    assertEquals(actualCharacters, List(testPlayer1))
    encounterPanel.removeCharacter(testPlayer1)
    val actualCharacters2 = encounterPanel.getCharacters
    assertEquals(actualCharacters2, List.empty[PlayerCharacter])
  }

  test("Each panel can be occupied by one or more players, but not the same player twice") {
    assertEquals(encounterPanel.characters, ArrayBuffer.empty[PlayerCharacter])
    encounterPanel.addCharacter(testPlayer1)
    val onlyPlayer = encounterPanel.getCharacters
    assertEquals(onlyPlayer, List(testPlayer1))
    encounterPanel.addCharacter(testPlayer1)
    assertEquals(onlyPlayer, List(testPlayer1))
  }

  test("Each panel has one or more following panels") {
    val bonusPanel: Panel = new BonusPanel()
    val dropPanel: Panel = new DropPanel()
    val encounterPanel: Panel = new EncounterPanel()
    val homePanel: Panel = new HomePanel()
    val neutralPanel: Panel = new NeutralPanel()

    //caso 1: panel solo un panel adyacente
    val testPanel: EncounterPanel = new EncounterPanel()
    testPanel.addNextPanel(bonusPanel)
    assertEquals(testPanel.getNextPanels, List[Panel](bonusPanel))

    //caso 2: panel con varios paneles adyacentes
    val testPanel2: EncounterPanel = new EncounterPanel()
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
    val testPanel: EncounterPanel = new EncounterPanel()
    testPanel.addNextPanel(encounterPanel)
    assertEquals(testPanel.getNextPanels, List[Panel](encounterPanel))
    testPanel.removeNextPanel(encounterPanel)
    assertEquals(testPanel.getNextPanels, List.empty[Panel])
  }

  test("Each panel has a random WildUnit") {
    for (_ <- 1 to 10) {
      assert(encounterPanel.obtenerWildUnit().isInstanceOf[WildUnit])
    }
  }

  test("the panel can apply something"){
    val testPanel: EncounterPanel = new EncounterPanel()
    val other: PlayerCharacter = new PlayerCharacter("other", 10, 1, 1, 1, new Random(11))
    val texto: String = "Encounter activated"
    assertEquals(testPanel.apply(other), print("Encounter activated"))
  }
}
