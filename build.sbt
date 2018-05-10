name := "partitions"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.9"
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"


mainClass in assembly := Some("combination.Main")