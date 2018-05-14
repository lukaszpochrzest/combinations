package combination

import scala.collection.mutable.ListBuffer

object Combinations {

  /**
    * generates all combinations of k numbers from range [0,n)
    */
  def combinations(n: Int, k: Int): ListBuffer[List[Int]] = {
    if (k > n) {
      ListBuffer()
    } else {
      val res: ListBuffer[List[Int]] = ListBuffer()
      combinations(0, n, k, List(), res)
      res
    }
  }

  private def combinations(n1: Int, n2: Int, k: Int, currComb: List[Int], res: ListBuffer[List[Int]]): Unit =
    k match {
      case 0 => res += currComb
      case positiveK if positiveK > 0 =>
        for (i <- n1 until (n2 - (positiveK - 1)))
          combinations(i + 1, n2, positiveK - 1, i :: currComb, res)
      case negativeK if negativeK < 0 => throw new IllegalStateException()
    }
}