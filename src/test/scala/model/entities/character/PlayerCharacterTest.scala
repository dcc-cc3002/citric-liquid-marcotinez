package cl.uchile.dcc.citric
package model.entities.character
import cl.uchile.dcc.citric.model.controller.GameController
import cl.uchile.dcc.citric.model.entities.wildunit.{Chicken, RoboBall, Seagull}
import cl.uchile.dcc.citric.model.norma.objective.Wins

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {

  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */

  /* Add any other constants you need here... */
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
  private var character: PlayerCharacter = _  // <- x = _ is the same as x = null
  /* Add any other variables you need here... */

  // This method is executed before each `test(...)` method.
  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    character = new PlayerCharacter(
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
    assertEquals(character.getNormaLevel, 1)
    assertEquals(character.getEvasion, evasion)
    assertEquals(character.getStarsAmount, 0)
    assertEquals(character.getVictories,0)
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

  test("A player have a initial norma level equal to 1") {
    assertEquals(character.getNormaLevel, 1)
  }

  test("A player can increase their norma level") {
    val player = new PlayerCharacter("test", maxHp, attack, defense, evasion, randomNumberGenerator)
    player.getNorma.setObjetive(new Wins)
    player.setVictories(1)
    player.normaCheck()
    assertEquals(player.getNormaLevel, 2)
  }

  test("A player can increase their stars count in a given amount") {
    character.starBonus(10)
    assertEquals(character.getStarsAmount, 10)
    character.starBonus(10)
    assertEquals(character.getStarsAmount, 20)
  }

  test("A player can decrease their stars count in a given amount") {
    //we start with 20 stars for the last test
    character.starBonus(10)
    assertEquals(character.getStarsAmount, 10)
    character.starDrop(5)
    assertEquals(character.getStarsAmount, 5)
  }

  test("A player can't have negative stars") {
    character.starDrop(5)
    assertEquals(character.getStarsAmount, 0)
  }

  test("A player have a initial Hp equal to their maxHp") {
    assertEquals(character.getHp, maxHp)
  }

  test("A player can heal their hp"){
    character.damage(5)
    character.heal(5)
    assertEquals(character.getHp, 10)
  }

  test("A player can back to combat after healing"){
    character.damage(10)
    character.heal(5)
    assertEquals(character.enCombate, true)
  }

  test("A player can damage their hp"){
    character.damage(5)
    assertEquals(character.getHp, 5)
  }

  test("A player can't heal more than their maxHp"){
    character.heal(20)
    assertEquals(character.getHp, 10)
  }

  test("A player can't have negative hp"){
    character.damage(100)
    assertEquals(character.getHp, 0)
  }

  test("A player can only attack a character in combat"){
    val other = new PlayerCharacter("other", maxHp, attack, defense, evasion, randomNumberGenerator)
    assertNotEquals(character.ataque(other), 0)
    other.damage(100) //enCombate = False
    assertEquals(character.ataque(other), 0)
  }

  test("A player can be attacked by a Character, and it will defend or evade randomly (por ahora)") {
    val goku = new PlayerCharacter("goku", maxHp, attack, defense, evasion, randomNumberGenerator)
    val other = new PlayerCharacter("other", maxHp, attack, defense, evasion, randomNumberGenerator)
    for (_ <- 1 to 10) {
      other.ataque(goku)
      assert( goku.getHp <= 10 || goku.getHp >= 0)
    }
  }

  test("A player can defend an attack"){
    val ataque: Int = 3
    val other = new PlayerCharacter("other", maxHp, attack, defense, evasion, randomNumberGenerator)
    other.defend(ataque)
    assert(other.getHp < maxHp)
  }

  test("A player can evade an attack"){
    val ataque: Int = 3
    val other = new PlayerCharacter("other", maxHp, attack, defense, evasion, randomNumberGenerator)
    other.evade(ataque)
    assertEquals(other.getHp, maxHp-ataque)
  }

  test("A player can defeat a Chicken") {
    val chicken = new Chicken
    character.wildUnitKO(chicken)
    assertEquals(character.getStarsAmount, 3)
    assertEquals(character.getVictories, 1)
  }

  test("A player can defeat a seaGull") {
    val seaGull = new Seagull
    character.wildUnitKO(seaGull)
    assertEquals(character.getStarsAmount, 2)
    assertEquals(character.getVictories, 1)
  }

  test("A player can defeat a RoboBall") {
    val roboBall = new RoboBall
    character.wildUnitKO(roboBall)
    assertEquals(character.getStarsAmount, 2)
    assertEquals(character.getVictories, 1)
  }

  test("A player can defeat another player") {
    val other = new PlayerCharacter("other", maxHp, attack, defense, evasion, randomNumberGenerator)
    other.starBonus(10)
    character.playerCharacterKO(other)
    assertEquals(character.getStarsAmount, 5)
    assertEquals(character.getVictories, 2)
    assertEquals(other.getStarsAmount, 5)
  }

  test("We can set the victories of a player") {
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    player.setVictories(10)
    assertEquals(player.getVictories, 10)
  }

  test("A player can try to recover") {
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    player.damage(10)
    for(_ <- 1 to 10) {
      player.recovery()
    }
    assertEquals(player.getHp, 10)

  }

  test("A player can have a observer") {
    val player = new PlayerCharacter("Other", 10, 1, 1, 1, new scala.util.Random(11))
    val controller = new GameController
    player.register(controller)
    assertEquals(player.getObserver, Some(controller))
  }

}
