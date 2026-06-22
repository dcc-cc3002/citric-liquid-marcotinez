package cl.uchile.dcc.citric
package model.entities.wildunit

class SeagullTest extends munit.FunSuite {

  class TestSeagull extends Seagull {
    def publicSetHp(hp: Int): Unit = setHp(hp)
    def publicDamage(hp: Int): Unit = damage(hp)
    def publicStarBonus(amount: Int): Unit = starBonus(amount)
    def publicStarDrop (amount: Int): Unit = starDrop(amount)
  }
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
    val seagull = new TestSeagull()
    seagull.publicStarBonus(1)
    assertEquals(seagull.getStarsAmount, 1)
  }

  test("A seagull can decrease their stars amount") {
    val seagull = new TestSeagull()
    seagull.publicStarBonus(1)
    seagull.publicStarDrop(1)
    assertEquals(seagull.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val seagull = new TestSeagull()
    seagull.publicStarDrop(1)
    assertEquals(seagull.getStarsAmount, 0)
  }

  test("A seagull can set their hp") {
    val seagull = new TestSeagull()
    seagull.publicSetHp(2)
    assertEquals(seagull.getHp, 2)
  }

  test("A seagull can damage their hp") {
    val seagull = new TestSeagull()
    seagull.publicSetHp(2)
    seagull.publicDamage(1)
    assertEquals(seagull.getHp, 1)
  }

  test("A WildUnit can't have negative hp") {
    val seagull = new TestSeagull()
    seagull.publicSetHp(2)
    seagull.publicDamage(3)
    assertEquals(seagull.getHp, 0)
  }
}
