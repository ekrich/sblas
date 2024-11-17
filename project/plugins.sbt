// to test snapshots
resolvers ++= Resolver.sonatypeOssRepos("snapshots")

// includes sbt-dynver sbt-pgp sbt-sonatype sbt-git
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.9.0")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.1.4")

// Scala Native support
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.6")
