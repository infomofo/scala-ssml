import Dependencies._

ThisBuild / scalaVersion     := "2.13.13"
ThisBuild / version          := "0.4.0"
ThisBuild / organization     := "com.infomofo"
ThisBuild / organizationName := "infomofo"
ThisBuild / crossScalaVersions := Seq("2.11.12", "2.12.11")

lazy val root = (project in file("."))
  .settings(
    name := "scala-ssml",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.3.0",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
