organization := "org.sorm-framework"

name := "sorm"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.sorm-framework" % "sorm" % "0.3.21",
  "com.github.nikita-volkov" % "sext" % "0.2.5" force(),
  "org.slf4j" % "slf4j-simple" % "1.7.2" % "test",
  "postgresql" % "postgresql" % "9.1-901.jdbc4" % "test",
  "org.hsqldb" % "hsqldb" % "2.2.8" % "test",
  "com.h2database" % "h2" % "1.3.168" % "test",
  "mysql" % "mysql-connector-java" % "5.1.19" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "junit" % "junit" % "4.7" % "test"
)
