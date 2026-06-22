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
    assertEquals(seagull.getExtraStars, 2)
    assertEquals(seagull.getVictories, 1)
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

  test("A seagull can be attacked, and it will defend or evade randomly") {
    val other = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    val seagull = new Seagull()
    for (_ <- 1 to 10) {
      seagull.attacked(other)
      assert( seagull.getHp <= 3 || seagull.getHp >= 0)
    }
  }

  test("A seagull can defeat a chicken") {
    val chicken = new Chicken()
    val seagull = new Seagull()
    seagull.wildUnitKO(chicken)
    assertEquals(seagull.getStarsAmount, 3)
  }

  test("A seagull can defeat a RoboBall") {
    val roboBall = new RoboBall()
    val seagull = new Seagull()
    seagull.wildUnitKO(roboBall)
    assertEquals(seagull.getStarsAmount, 2)
  }

  test("A seagull can defeat a PlayerCharacter") {
    val seagull = new Seagull()
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    player.starBonus(10)
    seagull.playerCharacterKO(player)
    assertEquals(seagull.getStarsAmount, 5)
    assertEquals(player.getStarsAmount, 5)
  }
}
