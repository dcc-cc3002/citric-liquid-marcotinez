package cl.uchile.dcc.citric
package model.norma

/** This class represents Norma level 6, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma6 extends AbstractNorma {
  override protected val level: Int = 6
  override protected val stars: Int = 0
  override protected val wins: Int = 0
  override val nextNormaLevel: Norma = this
}
