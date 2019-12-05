lazy val prevVersion = "0.1.1"
lazy val nextVersion = "0.2.0"

lazy val scala211 = "2.11.12"

scalaVersion := scala211

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
  val tag = out.ref.dropV.value
  if (out.isCleanAfterTag) tag
  else nextVersion + "-SNAPSHOT"
}

lazy val commonSettings = Seq(
  libraryDependencies += "com.github.lolgab" %%% "minitest" % "2.5.0-5f3852e" % Test,
  testFrameworks += new TestFramework("minitest.runner.Framework"),
  scalaVersion := scala211,
  logLevel := Level.Info, // Info, Debug
  nativeLinkStubs := true,
//  nativeMode := "release",
  // mima settings
  mimaPreviousArtifacts := Set("org.ekrich" %%% "sblas" % prevVersion),
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
