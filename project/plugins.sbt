// to test Scala Native snapshots
resolvers += Resolver.sonatypeRepo("snapshots")

// includes sbt-dynver sbt-pgp sbt-sonatype sbt-git
addSbtPlugin("com.github.sbt" % "sbt-ci-release"  % "1.5.10")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.0.1")

// Scala Native support
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.3")
