// To test snapshots
resolvers += Resolver.sonatypeCentralSnapshots

// includes sbt-dynver sbt-pgp sbt-sonatype sbt-git
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.12.1")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.2.1")

// Scala Native support
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.5.12")
