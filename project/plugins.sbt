// to test Scala Native snapshots
resolvers += Resolver.sonatypeRepo("snapshots")

// includes sbt-dynver sbt-pgp sbt-sonatype sbt-git
addSbtPlugin("com.geirsson" % "sbt-ci-release"  % "1.5.7")
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.0.0")

// Scala Native support
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.0")
