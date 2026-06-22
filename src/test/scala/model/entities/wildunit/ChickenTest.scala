package cl.uchile.dcc.citric
package model.entities.wildunit


class ChickenTest extends munit.FunSuite {

  /* This is the object under test. */
  private val chicken = new Chicken()

  test("A chicken should have correctly set their attributes") {
    assertEquals(chicken.maxHp, 3)
    assertEquals(chicken.attack, -1)
    assertEquals(chicken.defense, -1)
    assertEquals(chicken.evasion, 1)
  }

}
