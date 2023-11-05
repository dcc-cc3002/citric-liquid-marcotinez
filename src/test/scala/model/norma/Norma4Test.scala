package cl.uchile.dcc.citric
package model.norma

class Norma4Test extends munit.FunSuite{

  private val norma = new Norma4()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 4)
    assertEquals(norma.getStars, 120)
    assertEquals(norma.getWins, 10)
    assertEquals(norma.nextNorma.getLevel, 5)
  }
}
