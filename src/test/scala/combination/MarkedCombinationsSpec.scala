package combination

import org.scalatest._

class MarkedCombinationsSpec extends FlatSpec {

  "Combinations" should "be generated for valid input" in {
    assert(MarkedCombinations.combinations(Array(false, true, false, false, true, true, true, true, false, true), 4).size == 15)
  }

}
