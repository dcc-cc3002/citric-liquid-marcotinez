package cl.uchile.dcc.citric
package model.entities.wildunit

class RoboBallTest extends munit.FunSuite {

  class TestRoboBall extends RoboBall {
    def publicSetHp(hp: Int): Unit = setHp(hp)
    def publicDamage(hp: Int): Unit = damage(hp)
    def publicStarBonus(amount: Int): Unit = starBonus(amount)
    def publicStarDrop (amount: Int): Unit = starDrop(amount)
  }
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
    val roboBall = new TestRoboBall()
    roboBall.publicStarBonus(1)
    assertEquals(roboBall.getStarsAmount, 1)
  }

  test("A roboBall can decrease their stars amount") {
    val roboBall = new TestRoboBall()
    roboBall.publicStarBonus(1)
    roboBall.publicStarDrop(1)
    assertEquals(roboBall.getStarsAmount, 0)
  }

  test("A chicken can't have negative stars") {
    val roboBall = new TestRoboBall()
    roboBall.publicStarDrop(1)
    assertEquals(roboBall.getStarsAmount, 0)
  }

  test("A roboBall can set their hp") {
    val roboBall = new TestRoboBall()
    roboBall.publicSetHp(2)
    assertEquals(roboBall.getHp, 2)
  }

  test("A roboBall can damage their hp") {
    val roboBall = new TestRoboBall()
    roboBall.publicSetHp(2)
    roboBall.publicDamage(1)
    assertEquals(roboBall.getHp, 1)
  }

  test("A WildUnit can't have negative hp") {
    val roboBall = new TestRoboBall()
    roboBall.publicSetHp(2)
    roboBall.publicDamage(3)
    assertEquals(roboBall.getHp, 0)
  }

}
