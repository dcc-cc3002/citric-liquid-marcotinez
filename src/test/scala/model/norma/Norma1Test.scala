package cl.uchile.dcc.citric
package model.norma

import model.norma.normalevels.Norma1

class Norma1Test extends munit.FunSuite{

  private val norma = new Norma1()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 1)
    assertEquals(norma.getStars, 10)
    assertEquals(norma.getWins, 1)
    assertEquals(norma.nextNormaLevel.getLevel, 2)
  }
}
