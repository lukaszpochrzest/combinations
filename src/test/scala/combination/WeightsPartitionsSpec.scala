package combination

import org.scalatest._

class WeightsPartitionsSpec extends FlatSpec {

  "Partitions" should "be returned for valid input" in {
    val weights = List(10, 8, 4, 2, 2.5f, 3.5f, 8.5f, 9.5f)

    val partitons = WeightsPartitions.partitions(4, weights, 0.01f)

    partitons.foreach(partition => assert(partition.size == 4))
    partitons.foreach(partition => assert(partition.map(weights.toIndexedSeq).sum == (weights.sum / 2)))
  }

  it should "be able to divide into partitions of not equal size" in {
    val weights = List(10, 8, 4, 2, 2.5f, 3.5f, 8.5f, 9.5f)

    val partitons = WeightsPartitions.partitions(3, weights)

    assert(partitons.size == 3)
    assert(((partitons head) size) == 3)
    assert((partitons(1) size) == 3)
    assert((partitons(2) size) == 2)
  }

}