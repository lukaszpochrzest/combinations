package combination

import java.lang.Math.abs

import combination.Combinations.combinations

import scala.collection.mutable.ListBuffer
import scala.math.ceil

object WeightsPartitions {

  /**
    * return list of list of indexes indicating partitions which have similar weight sum
    */
  def partitions(partitionSize: Int, weights: List[Float]): List[List[Int]] = {
    val target = weights.sum / ceil(weights.length.toFloat / partitionSize).toInt
    partitions(partitionSize, target, weights.to[ListBuffer].zipWithIndex)
  }

  private def partitions(partitionSize: Int, target: Float, localWeights: ListBuffer[(Float, Int)]): List[List[Int]] = {
    if (partitionSize >= localWeights.length) {
      List(localWeights.map(_._2).toList)
    } else {
      val partition =
        combinations(localWeights.length, partitionSize)
          .map(combinationLocalIndexes => {
            val combination = combinationLocalIndexes.map(localWeights.toIndexedSeq)
            val score = abs(target - combination.map(_._1).sum) // the lesser the better
            (combinationLocalIndexes, combination, score)
          })
          .minBy(_._3)

      partition._2.map(_._2) :: partitions(partitionSize, target, removeByIndices(partition._1, localWeights))
    }
  }

  private def removeByIndices[A](indicesToRemove: List[Int], listBuffer: ListBuffer[A]): ListBuffer[A] = { // TODO improve
    for (i <- listBuffer.indices.reverse) {
      if (indicesToRemove contains i) {
        listBuffer remove i
      }
    }
    listBuffer
  }

}
