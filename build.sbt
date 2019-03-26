addCommandAlias("test", ";tests/test")

lazy val commonSettings = Seq(
  organization := "org.ekrich",
  version := "0.1.0",
  scalaVersion := "2.11.12",
  logLevel := Level.Info, // Info, Debug
  nativeGC := "immix",
  nativeLinkStubs := true
//  nativeMode := "release"
)

lazy val sblas = project
  .in(file("."))
  .settings(
    commonSettings
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas, tests)
  .aggregate(blas)

lazy val blas = project
  .in(file("blas"))
  .settings(
    commonSettings
  )
  .enablePlugins(ScalaNativePlugin)

lazy val tests = project
  .in(file("unit-tests"))
  .settings(
    commonSettings,
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.6" % Test,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas)
