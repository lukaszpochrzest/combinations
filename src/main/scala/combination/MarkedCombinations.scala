package combination

import scala.collection.mutable.ListBuffer

object MarkedCombinations {

  /**
    * Generates all combinations of k elements from all elements in array are marked with boolean flag true.
    * Combinations are represented as index lists.
    */
  def combinations(arr: Array[Boolean], k: Int): ListBuffer[List[Int]] = {
    val res: ListBuffer[List[Int]] = ListBuffer()
    combinations(arr, k, List(), res)
    res
  }

  private def combinations(arr: Array[Boolean], k: Int, currCombination: List[Int], res: ListBuffer[List[Int]]): Unit = {
    if (k == 0) {
      res += currCombination
      return
    }

    val arr2 = arr.clone()

    for (i <- arr2.indices) {
      if (arr2(i)) {
        arr2(i) = false
        combinations(arr2, k - 1, i :: currCombination, res)
      }
    }
  }
}