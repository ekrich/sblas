import scalanative.sbtplugin.ScalaNativePluginInternal._

// the following and commented out setting was a first attempt to support LTO
// thin for parrallel compiling and linking
// mac gets /usr/lib by default
lazy val ldOpt = Option(sys props "os.name") match {
  case Some("Linux") =>
    Seq("-fuse-ld=bfd") // gold failed - bfd works - but not with compile -flto
  case _ => Seq.empty
}

// eventually will need different compile options
lazy val compOpt = Option(sys props "os.name") match {
  case Some("Linux") => Seq.empty
  case _             => Seq.empty
}

//addCommandAlias("test", ";tests/test")

crossSbtVersions := Vector("0.13.16", "1.0.4")

lazy val commonSettings = Seq(
  organization := "org.ekrich",
  version := "0.1.0",
  scalaVersion := "2.11.12",
  logLevel := Level.Info, // Info, Debug
//  nativeLinkingOptions ++= Seq("-flto=thin") ++ ldOpt,
//  nativeCompileOptions ++= Seq("-flto=thin") ++ compOpt,
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
    name := "scala-native-blas",
    nativeOptimizerReporter := scala.scalanative.optimizer.Reporter
      .toDirectory(file("/tmp"))
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
