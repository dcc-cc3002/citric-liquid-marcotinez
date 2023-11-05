package cl.uchile.dcc.citric
package model.norma

/** This class represents Norma level 3, and it contains the values
 * for the number of stars and wins required to advance to the next rule level. */
class Norma3 extends AbstractNorma {
  override protected val level: Int = 3
  override protected val stars: Int = 70
  override protected val wins: Int = 6
  override val nextNorma: Norma = new Norma4
}
