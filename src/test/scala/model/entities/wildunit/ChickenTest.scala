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
  }

  test("A chicken should have correctly set their name") {
    assertEquals(chicken.getName, "Chicken")
  }

  test("A chicken should have correctly set their stars amount") {
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

  test("A chicken can attack"){
    val chicken = new Chicken()
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    for (_ <- 1 to 10) {
      val ataque_c = chicken.ataque(other)
      assert( 0 <= ataque_c && ataque_c <= 5)
    }
  }

  test("A chicken can defend"){
    val chicken = new Chicken()
    val atk = 1
    for (_ <- 1 to 10) {
      val defensa_c = chicken.defend(atk)
      assert( chicken.getHp < 3)
    }
  }

  test("A chicken can evade"){
    val chicken = new Chicken()
    val atk = 1
    for (_ <- 1 to 10) {
      val evasion_c = chicken.evade(atk)
      assert( chicken.getHp <= 3)
    }
  }
}
