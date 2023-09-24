package cl.uchile.dcc.citric
package model.entities.wildunit

class SeagullTest extends munit.FunSuite {

  private val seagull = new Seagull()

  test("A seagull should have correctly set their attributes") {
    assertEquals(seagull.maxHp, 3)
    assertEquals(seagull.attack, 1)
    assertEquals(seagull.defense, -1)
    assertEquals(seagull.evasion, -1)
  }
}
