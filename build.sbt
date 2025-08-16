import Dependencies._

ThisBuild / scalaVersion     := "2.13.13"
ThisBuild / version          := "0.4.0"
ThisBuild / organization     := "com.infomofo"
ThisBuild / organizationName := "infomofo"
ThisBuild / crossScalaVersions := Seq("2.11.12", "2.12.11")

// Enable scalafmt and other helpful compiler options
ThisBuild / scalafmtOnCompile := true
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

lazy val root = (project in file("."))
  .settings(
    name := "scala-ssml",
    description := "A Scala library for building Speech Synthesis Markup Language (SSML) expressions",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
    libraryDependencies += scalaTest % Test,
    
    // Compiler options for better IDE support and GitHub Copilot integration
    scalacOptions ++= Seq(
      "-deprecation",           // Emit warning for usages of deprecated APIs
      "-feature",              // Emit warning for features that require explicit import
      "-unchecked",            // Enable additional warnings where generated code depends on assumptions
      "-Xfatal-warnings",      // Fail compilation on warnings
      "-Ywarn-dead-code",      // Warn when dead code is identified
      "-Ywarn-numeric-widen",  // Warn when numerics are widened
      "-Ywarn-value-discard",  // Warn when non-Unit expression results are unused
      "-Ywarn-unused:imports", // Warn on unused imports
      "-Ymacro-annotations"    // Enable macro annotations
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
