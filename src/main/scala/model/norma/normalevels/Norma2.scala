package cl.uchile.dcc.citric
package model.norma.normalevels

import model.norma.{AbstractNorma, Norma}

/** This class represents Norma level 2, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma2 extends AbstractNorma {
  override protected val level: Int = 2
  override protected val stars: Int = 30
  override protected val wins: Int = 3
  override val nextNormaLevel: Norma = new Norma3
}
