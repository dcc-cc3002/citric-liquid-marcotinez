package cl.uchile.dcc.citric
package model.norma

/** This class represents Norma level 5, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma5 extends AbstractNorma {
  override protected val level: Int = 5
  override protected val stars: Int = 200
  override protected val wins: Int = 14
  override val nextNorma: Norma = new Norma6
}
