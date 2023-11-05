package cl.uchile.dcc.citric
package model.norma

class Norma3Test extends munit.FunSuite{

  private val norma = new Norma3()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 3)
    assertEquals(norma.getStars, 70)
    assertEquals(norma.getWins, 6)
    assertEquals(norma.nextNormaLevel.getLevel, 4)
  }
}
