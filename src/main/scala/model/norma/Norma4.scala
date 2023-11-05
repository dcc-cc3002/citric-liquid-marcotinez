package cl.uchile.dcc.citric
package model.norma

/** This class represents Norma level 4, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma4 extends AbstractNorma {
  override protected val level: Int = 4
  override protected val stars: Int = 120
  override protected val wins: Int = 10
  override val nextNormaLevel: Norma = new Norma5
}
