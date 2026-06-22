package cl.uchile.dcc.citric
package model.norma

import model.norma.normalevels.Norma6

class Norma6Test extends munit.FunSuite{

  private val norma = new Norma6()

  test("A norma should have correctly set their attributes") {
    assertEquals(norma.getLevel, 6)
    assertEquals(norma.getStars, 0)
    assertEquals(norma.getWins, 0)
    assertEquals(norma.nextNormaLevel.getLevel, 6)
  }
}
