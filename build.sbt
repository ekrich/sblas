

addCommandAlias("test", ";tests/test")

crossSbtVersions := Vector("0.13.17", "1.0.4")

lazy val commonSettings = Seq(
  organization := "org.ekrich",
  version := "0.1.0",
  scalaVersion := "2.11.12",
  logLevel := Level.Info, // Info, Debug
  nativeGC := "immix",
  nativeLinkStubs := true
//  nativeMode := "release"
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
    name := "scala-native-blas"
  )
  .enablePlugins(ScalaNativePlugin)

lazy val tests = project
  .in(file("unit-tests"))
  .settings(
    commonSettings,
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.5.3" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas)
