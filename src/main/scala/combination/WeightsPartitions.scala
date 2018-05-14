package combination

import java.lang.Math.abs

import combination.Combinations.combinations

import scala.collection.mutable.ListBuffer
import scala.math.ceil

object WeightsPartitions {

  val CHOOSE_COMBINATION_BEST_RANGE = 0.2f

  /**
    * return list of list of indexes indicating partitions which have similar weight sum
    */
  def partitions(partitionSize: Int, weights: List[Float], bestRange: Float = CHOOSE_COMBINATION_BEST_RANGE): List[List[Int]] =
    bestRange match {
      case validBestRange if validBestRange > 0.0f && validBestRange < 1.0f =>
        val target = weights.sum / ceil(weights.length.toFloat / partitionSize).toInt
        partitions(bestRange, partitionSize, target, weights.to[ListBuffer].zipWithIndex)
      case _ =>
        throw new IllegalArgumentException
    }

  private def partitions(bestRange: Float, partitionSize: Int, target: Float, localWeights: ListBuffer[(Float, Int)]): List[List[Int]] = {
    if (partitionSize >= localWeights.length) {
      List(localWeights.map(_._2).toList)
    } else {
      val partition =
        chooseCombination(
          bestRange,
          combinations(localWeights.length, partitionSize)
            .map(combinationLocalIndexes => {
              val combination = combinationLocalIndexes.map(localWeights.toIndexedSeq)
              val score = abs(target - combination.map(_._1).sum) // the lesser the better
               (combinationLocalIndexes, combination, score)
            })
        )

      partition._2.map(_._2) :: partitions(bestRange, partitionSize, target, removeByIndices(partition._1, localWeights))
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


  private def chooseCombination(bestRange:Float, combinations: ListBuffer[(List[Int], List[(Float, Int)], Float)]) = {
    val r = scala.util.Random
    val randomIndexWithinRange = r.nextInt(math.ceil(combinations.size * bestRange).toInt + 1)
    val sorted = combinations.sortBy(_._3)
    sorted(randomIndexWithinRange)
  }

}
