package cl.uchile.dcc.citric
package model.norma

class Norma2Test extends munit.FunSuite{

  private val norma = new Norma2()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 2)
    assertEquals(norma.getStars, 30)
    assertEquals(norma.getWins, 3)
    assertEquals(norma.nextNormaLevel.getLevel, 3)
  }
}
