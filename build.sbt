import com.typesafe.sbt.SbtScalariform._
import sbt.Resolver

name := "amazon-service"

version := "0.1"

scalaVersion := "2.11.4"

scalariformSettings

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= Seq(
  "com.tumblr" %% "colossus" % "0.5.1",
  "org.specs2" %% "specs2-core" % "2.4.11" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.11" % "test",
  "net.codingwell" %% "scala-guice" % "4.0.0-beta5",
  "com.github.nscala-time" %% "nscala-time" % "1.6.0",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.6"
)

scalacOptions in Test ++= Seq("-Yrangepos")