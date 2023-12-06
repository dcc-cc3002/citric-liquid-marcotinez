package cl.uchile.dcc.citric
package model.panels

import cl.uchile.dcc.citric.model.entities.GameCharacter
import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter
import cl.uchile.dcc.citric.model.entities.wildunit.{Chicken, RoboBall, Seagull, WildUnit}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/** The "EncounterPanel" is responsible for engaging the player in combat with a random Wild Hunt.
 *
 * @author [[https://github.com/marcotinez/ Marco Martínez S.]]
 * */
class EncounterPanel extends AbstractPanels {

  /** The type of panel. */
  val panelType: String = "Encounter"
  private var wildUnitEnemy: Option[WildUnit] = None

  /** ArrayBuffer containing the 3 available WildUnits.
   *
   * this variable contains the 3 available WildUnits that can be encountered by the player.
   * */
  private val wildunits: ArrayBuffer[WildUnit] = ArrayBuffer(new Chicken(), new RoboBall(), new Seagull())

  override def getWildUnitEnemy: Option[WildUnit] = wildUnitEnemy

  /** Method responsible for choosing a random WildUnit that will engage in combat with the player
   * on the panel.
   *
   * @return A random WildUnit
   *
   * */
  def getRandomWildUnit: WildUnit = {
    val index = Random.nextInt(wildunits.length)
    wildunits(index)
  }

  def apply(player: PlayerCharacter): Unit = {
    if (wildUnitEnemy.isEmpty || !wildUnitEnemy.get.enCombate) {
      wildUnitEnemy = Some(getRandomWildUnit)
    }
  }
}
