package cl.uchile.dcc.citric
package model.norma.normalevels

import model.norma.{AbstractNorma, Norma}

/** This class represents Norma level 1, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma1 extends AbstractNorma {
  override protected val level: Int = 1
  override protected val stars: Int = 10
  override protected val wins: Int = 1
  override val nextNormaLevel: Norma = new Norma2
}
