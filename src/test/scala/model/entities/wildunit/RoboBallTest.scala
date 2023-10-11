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
  }

  test("A roboBall should have correctly set their name") {
    assertEquals(roboBall.getName, "RoboBall")
  }

  test("A roboBall should have correctly set their stars amount") {
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

  test("A roboBall can attack"){
    val roboBall = new RoboBall()
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    for (_ <- 1 to 10) {
      val ataque_c = roboBall.ataque(other)
      assert(0 <= ataque_c && ataque_c <= 5)
    }
  }

  test("A roboBall can defend") {
    val roboBall = new RoboBall()
    val atk = 1
    for (_ <- 1 to 10) {
      val defensa_c = roboBall.defend(atk)
      assert(roboBall.getHp < 3)
    }
  }

  test("A roboBall can evade") {
    val roboBall = new RoboBall()
    val atk = 1
    for (_ <- 1 to 10) {
      val evasion_c = roboBall.evade(atk)
      assert(roboBall.getHp <= 3)
    }
  }
}
