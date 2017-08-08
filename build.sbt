import scalanative.sbtplugin.ScalaNativePluginInternal.nativeOptimizerReporter

lazy val commonSettings = Seq(
  organization := "org.ekrich",
  version := "0.1.0",
  scalaVersion := "2.11.11",
  logLevel := Level.Info, // Info, Debug
  nativeGC := "immix"
)

lazy val ml = project
  .in(file("."))
  .settings(
    commonSettings,
    name := "scala-native-ml"
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas, tests)
  .aggregate(blas)

lazy val blas = project
  .in(file("blas"))
  .settings(
    commonSettings,
    name := "scala-native-blas",
    nativeOptimizerReporter := scala.scalanative.optimizer.Reporter
      .toDirectory(file("/tmp"))
  )
  .enablePlugins(ScalaNativePlugin)

lazy val tests = project
  .in(file("unit-tests"))
  .settings(
    commonSettings,
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.4.8" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas)
