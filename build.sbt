// based on https://github.com/sifive/freedom/blob/master/build.sbt
// See LICENSE for license details.
//
// sbt project for building individual sifive blocks in order to fuzz test them.
name := "fuzzing"
version := "0.0.1"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.12",  // This needs to match rocket-chip's scalaVersion
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-language:reflectiveCalls"
  )
)

// A RootProject (not well-documented) tells sbt to treat the target directory
// as its own root project, reading its build settings. If we instead used the
// normal `project in file()` declaration, sbt would ignore all of rocket-chip's
// build settings, and therefore not understand that it has its own dependencies
// on chisel, etc.
lazy val rocketChip = RootProject(file("rocket-chip"))

lazy val sifiveBlocks = (project in file("blocks")).
  dependsOn(rocketChip).
  settings(commonSettings: _*)

lazy val fuzzStuff = (project in file(".")).
  dependsOn(rocketChip, sifiveBlocks).
  settings(commonSettings: _*)


