package cl.uchile.dcc.citric
package model.entities.character

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {

  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */

  /* Add any other constants you need here... */

  class TestPlayerCharacter( name: String,
                             maxHp: Int,
                             attack: Int,
                             defense: Int,
                             evasion: Int,
                             randomNumberGenerator: Random
                           ) extends PlayerCharacter(name, maxHp, attack, defense, evasion, randomNumberGenerator) {
    def publicSetHp(hp: Int): Unit = setHp(hp)
    def publicHeal(hp: Int): Unit = heal(hp)
    def publicDamage(hp: Int): Unit = damage(hp)
    def publicStarBonus(amount: Int): Unit = starBonus(amount)
    def publicStarDrop (amount: Int): Unit = starDrop(amount)
    def publicNormaClear(): Unit = normaClear()
  }

  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private var randomNumberGenerator: Random = _
  /*
  This is the object under test.
  We initialize it in the beforeEach method so we can reuse it in all the tests.
  This is a good practice because it will reset the object before each test, so you don't have
  to worry about the state of the object between tests.
  */
  private var character: TestPlayerCharacter = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    character = new TestPlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator)
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.getName, name)
    assertEquals(character.getMaxHp, maxHp)
    assertEquals(character.getAttack, attack)
    assertEquals(character.getDefense, defense)
    assertEquals(character.getEvasion, evasion)
    assertEquals(character.getNormaLevel, 1)
    assertEquals(character.getStarsAmount, 0)
    assertEquals(character.getWins,0)
  }

  // Two ways to test randomness (you can use any of them):

  // 1. Test invariant properties, e.g. the result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  // 2. Set a seed and test the result is always the same.
  // A seed sets a fixed succession of random numbers, so you can know that the next numbers
  // are always the same for the same seed.
   test ("A character should be able to roll a dice with a fixed seed") {
        val other =
          new PlayerCharacter(name, maxHp, attack, defense, evasion, new Random(11))
        for (_ <- 1 to 10) {
          assertEquals(character.rollDice(), other.rollDice())
    }
  }

  test("A player can increase their norma level") {
    character.publicNormaClear()
    assertEquals(character.getNormaLevel, 2)
    character.publicNormaClear()
    assertEquals(character.getNormaLevel, 3)
  }

  test("A player can increase their stars count in a given amount") {
    character.publicStarBonus(10)
    assertEquals(character.getStarsAmount, 10)
    character.publicStarBonus(10)
    assertEquals(character.getStarsAmount, 20)
  }

  test("A player can decrease their stars count in a given amount") {
    //we start with 20 stars for the last test
    character.publicStarBonus(10)
    assertEquals(character.getStarsAmount, 10)
    character.publicStarDrop(5)
    assertEquals(character.getStarsAmount, 5)
  }

  test("A player can't have negative stars") {
    character.publicStarDrop(5)
    assertEquals(character.getStarsAmount, 0)
  }

  test("A player have a initial Hp equal to their maxHp") {
    assertEquals(character.getHp, maxHp)
  }

  test("A player can set their hp"){
    character.publicSetHp(5)
    assertEquals(character.getHp, 5)
  }

  test("A player can´t set their hp higher than their maxHp"){
    character.publicSetHp(15)
    assertEquals(character.getHp, maxHp)
  }

  test("A player can heal their hp"){
    character.publicSetHp(5)
    character.publicHeal(5)
    assertEquals(character.getHp, 10)
  }

  test("A player can damage their hp"){
    character.publicSetHp(5)
    character.publicDamage(5)
    assertEquals(character.getHp, 0)
  }

  test("A player can't heal more than their maxHp"){
    character.publicSetHp(5)
    character.publicHeal(10)
    assertEquals(character.getHp, 10)
  }

  test("A player can't have negative hp"){
    character.publicSetHp(5)
    character.publicDamage(10)
    assertEquals(character.getHp, 0)
  }
}
