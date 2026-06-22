package cl.uchile.dcc.citric
package model.entities.wildunit

import model.entities.character.PlayerCharacter

class SeagullTest extends munit.FunSuite {

  /* This is the object under test. */
  private val seagull = new Seagull()

  test("A seagull should have correctly set their attributes") {
    assertEquals(seagull.getMaxHp, 3)
    assertEquals(seagull.getAttack, 1)
    assertEquals(seagull.getDefense, -1)
    assertEquals(seagull.getEvasion, -1)
  }

  test("A seagull should have correctly set their name") {
    assertEquals(seagull.getName, "Seagull")
  }

  test("A seagull should have correctly set their stars amount") {
    assertEquals(seagull.getStarsAmount, 0)
  }

  test("A seagull can increase their stars amount") {
    val seagull = new Seagull()
    seagull.starBonus(1)
    assertEquals(seagull.getStarsAmount, 1)
  }

  test("A seagull can decrease their stars amount") {
    val seagull = new Seagull()
    seagull.starBonus(1)
    seagull.starDrop(1)
    assertEquals(seagull.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val seagull = new Seagull()
    seagull.starDrop(1)
    assertEquals(seagull.getStarsAmount, 0)
  }

  test("A seagull can damage their hp") {
    val seagull = new Seagull()
    seagull.damage(1)
    assertEquals(seagull.getHp, 2)
  }

  test("A seagull can't have negative hp") {
    val seagull = new Seagull()
    seagull.damage(3)
    assertEquals(seagull.getHp, 0)
  }
  test("A seagull can attack") {
    val seagull = new Seagull()
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    for (_ <- 1 to 10) {
      val ataque_c = seagull.ataque(other)
      assert(2 <= ataque_c && ataque_c <= 7)
    }
  }

  test("A seagull can defend") {
    val seagull = new Seagull()
    val atk = 1
    for (_ <- 1 to 10) {
      val defensa_c = seagull.defend(atk)
      assert(seagull.getHp < 3)
    }
  }

  test("A seagull can evade") {
    val seagull = new Seagull()
    val atk = 1
    for (_ <- 1 to 10) {
      val evasion_c = seagull.evade(atk)
      assert(seagull.getHp <= 3)
    }
  }
}
