package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.character.PlayerCharacter

class RoboBallTest extends munit.FunSuite {

  /* This is the object under test. */
  private val roboBall = new RoboBall()

  test("A roboBall should have correctly set their attributes") {
    assertEquals(roboBall.getMaxHp, 3)
    assertEquals(roboBall.getAttack, -1)
    assertEquals(roboBall.getDefense, 1)
    assertEquals(roboBall.getEvasion, -1)
    assertEquals(roboBall.getExtraStars, 2)
    assertEquals(roboBall.getVictories, 1)
    assertEquals(roboBall.getStarsAmount, 0)
  }

  test("A roboBall can increase their stars amount") {
    val roboBall = new RoboBall()
    roboBall.starBonus(1)
    assertEquals(roboBall.getStarsAmount, 1)
  }

  test("A roboBall can decrease their stars amount") {
    val roboBall = new RoboBall()
    roboBall.starBonus(1)
    roboBall.starDrop(1)
    assertEquals(roboBall.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val roboBall = new RoboBall()
    roboBall.starDrop(1)
    assertEquals(roboBall.getStarsAmount, 0)
  }

  test("A roboBall can damage their hp") {
    val roboBall = new RoboBall()
    roboBall.damage(1)
    assertEquals(roboBall.getHp, 2)
  }

  test("A WildUnit can't have negative hp") {
    val roboBall = new RoboBall()
    roboBall.damage(3)
    assertEquals(roboBall.getHp, 0)
  }

  test("A roboBall can be attacked, and it will defend or evade randomly") {
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    val roboBall = new RoboBall()
    for (_ <- 1 to 10) {
      roboBall.attacked(other)
      assert( roboBall.getHp <= 3 || roboBall.getHp >= 0)
    }
  }

  test("A RoboBall can defeat a Chicken") {
    val chicken = new Chicken()
    val roboBall = new RoboBall()
    roboBall.defeatWildUnit(chicken)
    assertEquals(roboBall.getStarsAmount, 3)
  }

  test("A RoboBall can defeat a Seagull") {
    val roboBall = new RoboBall()
    val seagull = new Seagull()
    roboBall.defeatWildUnit(seagull)
    assertEquals(roboBall.getStarsAmount, 2)
  }

  test("A RoboBall can defeat a PlayerCharacter") {
    val roboBall = new RoboBall()
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    player.starBonus(10)
    roboBall.defeatPlayerCharacter(player)
    assertEquals(roboBall.getStarsAmount, 5)
    assertEquals(player.getStarsAmount, 5)
  }
}
