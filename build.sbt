addCommandAlias("test", ";tests/test")

inThisBuild(
  List(
    description := "BLAS interface for Scala Native",
    organization := "org.ekrich",
    homepage := Some(url("https://github.com/ekrich/sblas")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        id = "ekrich",
        name = "Eric K Richardson",
        email = "ekrichardson@gmail.com",
        url = url("http://github.ekrich.org/")
      )
    )
  ))

lazy val commonSettings = Seq(
  scalaVersion := "2.11.12",
  logLevel := Level.Info, // Info, Debug
  nativeGC := "immix",
  nativeLinkStubs := true
//  nativeMode := "release"
)

lazy val sblas = project
  .in(file("."))
  .settings(
    skip in publish := true,
    doc / aggregate := false,
    doc := (blas / Compile / doc).value,
    packageDoc / aggregate := false,
    packageDoc := (blas / Compile / packageDoc).value
  )
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
    skip in publish := true,
    commonSettings,
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.6" % Test,
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaNativePlugin)
  .dependsOn(blas)
