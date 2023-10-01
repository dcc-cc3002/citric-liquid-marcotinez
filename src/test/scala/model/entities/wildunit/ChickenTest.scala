package cl.uchile.dcc.citric
package model.entities.wildunit


class ChickenTest extends munit.FunSuite {

  class TestChicken extends Chicken {
    def publicSetHp(hp: Int): Unit = setHp(hp)
    def publicDamage(hp: Int): Unit = damage(hp)
    def publicStarBonus(amount: Int): Unit = starBonus(amount)
    def publicStarDrop (amount: Int): Unit = starDrop(amount)
}
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
    val chicken = new TestChicken()
    chicken.publicStarBonus(1)
    assertEquals(chicken.getStarsAmount, 1)
  }
  test("A chicken can decrease their stars amount") {
    val chicken = new TestChicken()
    chicken.publicStarBonus(1)
    chicken.publicStarDrop(1)
    assertEquals(chicken.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val chicken = new TestChicken()
    chicken.publicStarDrop(1)
    assertEquals(chicken.getStarsAmount, 0)
  }

  test("A chicken can set their hp") {
    val chicken = new TestChicken()
    chicken.publicSetHp(2)
    assertEquals(chicken.getHp, 2)
  }

  test("A chicken can damage their hp") {
    val chicken = new TestChicken()
    chicken.publicSetHp(2)
    chicken.publicDamage(1)
    assertEquals(chicken.getHp, 1)
  }

  test("A WildUnit can't have negative hp") {
    val chicken = new TestChicken()
    chicken.publicSetHp(2)
    chicken.publicDamage(3)
    assertEquals(chicken.getHp, 0)
  }
}
