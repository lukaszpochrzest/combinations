package combination

import java.io.IOException

import combination.WeightsPartitions.partitions
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.io.Source

case class InputObject(desc: String, weight: Float) {
  override def toString: String = "[" + desc + ", " + weight + "]"
}

object Main {

  // for input parsing
  implicit val locationReads: Reads[InputObject] = (
    (JsPath \ "desc").read[String] and
      (JsPath \ "weight").read[Float]
    ) (InputObject.apply _)


  def process(args: Array[String]): Unit = {
    if (args.length < 2) {
      println("Provide input data and partition size")
    }

    val bufferedSource = Source.fromFile(args(0))

    val inputFile = bufferedSource.getLines.mkString

    Json.parse(inputFile).validate[Array[InputObject]] match {
      case s: JsSuccess[Array[InputObject]] =>
        val inputObjects: Array[InputObject] = s.get
        val parts = partitions(args(1).toInt, inputObjects.map(_.weight).toList)
        parts.foreach(p => println(p.map(inputObjects.toIndexedSeq)))
      case _: JsError =>
        println("Invalid input file format")
    }

    bufferedSource.close
  }

  def main(args: Array[String]): Unit = {
    try {
      process(args)
    } catch {
      case e: IOException => println("Failed to process file")
      case _: Throwable => println("Program error")
    }
  }
}
