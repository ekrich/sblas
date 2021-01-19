// sblas build
val prevVersion = "0.2.0"
val nextVersion = "0.3.0"

val scala211 = "2.11.12"
val scala212 = "2.12.13"
val scala213 = "2.13.5"
val scala300 = "3.0.0"

val versionsNative = Seq(scala212, scala211, scala213)

ThisBuild / scalaVersion := scala213
ThisBuild / crossScalaVersions := versionsNative

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
    ),
    version := dynverGitDescribeOutput.value.mkVersion(versionFmt, ""),
    dynver := sbtdynver.DynVer
      .getGitDescribeOutput(new java.util.Date)
      .mkVersion(versionFmt, "")
  ))

// stable snapshot is not great for publish local
def versionFmt(out: sbtdynver.GitDescribeOutput): String = {
  val tag = out.ref.dropPrefix
  if (out.isCleanAfterTag) tag
  else nextVersion + "-SNAPSHOT"
}

lazy val commonSettings = Seq(
  logLevel := Level.Info, // Info, Debug
  nativeLinkStubs := true,
  addCompilerPlugin(
    "org.scala-native" % "junit-plugin" % nativeVersion cross CrossVersion.full
  ),
  libraryDependencies += "org.scala-native" %%% "junit-runtime" % nativeVersion,
  testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-s", "-v"),
  // mima settings
  mimaPreviousArtifacts := Set("org.ekrich" %%% "sblas" % prevVersion)
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "sblas-root",
    publish / skip := true,
    doc / aggregate := false,
    doc := (sblas / Compile / doc).value,
    packageDoc / aggregate := false,
    packageDoc := (sblas / Compile / packageDoc).value
  )
  .aggregate(sblas)

lazy val sblas = project
  .in(file("sblas"))
  .settings(
    commonSettings
  )
  .enablePlugins(ScalaNativePlugin)
