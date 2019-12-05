// to test Scala Native snapshots
resolvers += Resolver.sonatypeRepo("snapshots")

// includes sbt-dynver sbt-pgp sbt-sonatype sbt-git
addSbtPlugin("com.geirsson" % "sbt-ci-release"  % "1.4.31")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "0.6.1")

// Scala Native support
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.0-M2")
