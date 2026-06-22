package cl.uchile.dcc.citric
package model.norma

import model.norma.normalevels.Norma5

class Norma5Test extends munit.FunSuite{

  private val norma = new Norma5()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 5)
    assertEquals(norma.getStars, 200)
    assertEquals(norma.getWins, 14)
    assertEquals(norma.nextNormaLevel.getLevel, 6)
  }
}
