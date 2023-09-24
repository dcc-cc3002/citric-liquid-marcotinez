package cl.uchile.dcc.citric
package model.panels
import model.{AbstractPanels, Panel}

import cl.uchile.dcc.citric.model.entities.GameCharacter
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import cl.uchile.dcc.citric.model.entities.wildunit.{Chicken, RoboBall, Seagull, WildUnit}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** The "EncounterPanel" is responsible for engaging the player in combat with a random Wild Hunt.
 *
 * @param nextPanels An array of panels that are directly connected to this one.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class EncounterPanel(nextPanels: ArrayBuffer[Panel]) extends AbstractPanels(nextPanels) {

  /** The type of panel. */
  val panelType: String = "Encounter"

  /** ArrayBuffer containing the 3 available WildUnits.
   *
   * this variable contains the 3 available WildUnits that can be encountered by the player.
   * */
  private val wildunits: ArrayBuffer[WildUnit] = ArrayBuffer(new Chicken(), new RoboBall(), new Seagull())

  /** Auxiliary constructor in case no arguments are provided when creating the panel.
   *
   * @return A new BonusPanel instance with no connections.
   *
   * */
  def this() {
    this(ArrayBuffer.empty[Panel])
  }

  /** Method responsible for choosing a random WildUnit that will engage in combat with the player
   * on the panel.
   *
   * @return A random WildUnit
   *
   * */
  def obtenerWildUnit(): WildUnit = {
    val index = Random.nextInt(wildunits.length)
    wildunits(index)
  }

  //override def activatePanel(player: PlayerCharacter): Unit = ???
}
