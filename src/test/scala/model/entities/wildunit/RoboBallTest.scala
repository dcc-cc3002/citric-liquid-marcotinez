package cl.uchile.dcc.citric
package model.entities.wildunit

class RoboBallTest extends munit.FunSuite {

  private val roboBall = new RoboBall()

  test("A roboBall should have correctly set their attributes") {
    assertEquals(roboBall.maxHp, 3)
    assertEquals(roboBall.attack, -1)
    assertEquals(roboBall.defense, 1)
    assertEquals(roboBall.evasion, -1)
  }
}
