package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.GameCharacter

import cl.uchile.dcc.citric.model.entities.character.PlayerCharacter


class ChickenTest extends munit.FunSuite {

  /* This is the object under test. */
  private val chicken = new Chicken()

  test("A chicken should have correctly set their attributes") {
    assertEquals(chicken.getMaxHp, 3)
    assertEquals(chicken.getAttack, -1)
    assertEquals(chicken.getDefense, -1)
    assertEquals(chicken.getEvasion, 1)
    assertEquals(chicken.getExtraStars, 3)
    assertEquals(chicken.getExtraVictories, 1)
    assertEquals(chicken.getStarsAmount, 0)
  }

  test("A chicken can increase their stars amount") {
    val chicken = new Chicken()
    chicken.starBonus(1)
    assertEquals(chicken.getStarsAmount, 1)
  }

  test("A chicken can decrease their stars amount") {
    val chicken = new Chicken()
    chicken.starBonus(1)
    chicken.starDrop(1)
    assertEquals(chicken.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val chicken = new Chicken()
    chicken.starDrop(1)
    assertEquals(chicken.getStarsAmount, 0)
  }

  test("A chicken can damage their hp") {
    val chicken = new Chicken()
    chicken.damage(1)
    assertEquals(chicken.getHp, 2)
  }

  test("A WildUnit can't have negative hp") {
    val chicken = new Chicken()
    chicken.damage(3)
    assertEquals(chicken.getHp, 0)
  }

  test("A chicken can be attacked, and it will defend or evade randomly") {
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    val chicken = new Chicken()
    for (_ <- 1 to 50) {
      other.attacked(chicken)
      assert( chicken.getHp <= 3 || chicken.getHp >= 0)
    }
  }

  test("A chicken can defeat a RoboBall") {
    val chicken = new Chicken()
    val roboBall = new RoboBall()
    chicken.defeatWildUnit(roboBall)
    assertEquals(chicken.getStarsAmount, 2)
  }

  test("A chicken can defeat a Seagull") {
    val chicken = new Chicken()
    val seagull = new Seagull()
    chicken.defeatWildUnit(seagull)
    assertEquals(chicken.getStarsAmount, 2)
  }

  test("A chicken can defeat a PlayerCharacter") {
    val chicken = new Chicken()
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    player.starBonus(10)
    chicken.defeatPlayerCharacter(player)
    assertEquals(chicken.getStarsAmount, 5)
    assertEquals(player.getStarsAmount, 5)
  }

}
