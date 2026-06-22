package cl.uchile.dcc.citric
package model.norma

import model.norma.normalevels.Norma4

class Norma4Test extends munit.FunSuite{

  private val norma = new Norma4()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 4)
    assertEquals(norma.getStars, 120)
    assertEquals(norma.getWins, 10)
    assertEquals(norma.nextNormaLevel.getLevel, 5)
  }
}
