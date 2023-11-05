package cl.uchile.dcc.citric
package model.norma.normalevels

import model.norma.{AbstractNorma, Norma}

/** This class represents Norma level 5, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma5 extends AbstractNorma {
  override protected val level: Int = 5
  override protected val stars: Int = 200
  override protected val wins: Int = 14
  override val nextNormaLevel: Norma = new Norma6
}
