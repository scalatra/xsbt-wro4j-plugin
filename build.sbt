import xml.Group

organization := "com.bowlingx"

name := "xsbt-wro4j-plugin"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

sbtPlugin := true

ScriptedPlugin.scriptedSettings

scriptedBufferLog := false

libraryDependencies ++= Seq(
   "commons-logging" % "commons-logging" % "1.1.1" % "provided",
   "org.slf4j" % "log4j-over-slf4j" % "1.6.4",
   "ch.qos.logback" % "logback-classic" % "1.0.2",
   "org.specs2" %% "specs2" % "1.11" % "test",
   "org.mockito" % "mockito-core" % "1.9.0",
   "javax.servlet" % "javax.servlet-api" % "3.0.1",
   "ro.isdc.wro4j" % "wro4j-core" % "1.4.7" excludeAll(ExclusionRule(organization = "org.slf4j")),
   "ro.isdc.wro4j" % "wro4j-extensions" % "1.4.7" excludeAll(ExclusionRule(organization = "org.slf4j"))
)

publishMavenStyle := false

publishTo <<= (version) { version: String =>
   val scalasbt = "http://scalasbt.artifactoryonline.com/scalasbt/"
   val (name, url) = if (version.contains("-SNAPSHOT"))
                       ("sbt-plugin-snapshots", scalasbt+"sbt-plugin-snapshots")
                     else
                       ("sbt-plugin-releases", scalasbt+"sbt-plugin-releases")
   Some(Resolver.url(name, new URL(url))(Resolver.ivyStylePatterns))
}

publishArtifact in Test := false

pomIncludeRepository := { x => false }

packageOptions <<= (packageOptions, name, version, organization) map {
  (opts, title, version, vendor) =>
     opts :+ Package.ManifestAttributes(
      "Created-By" -> "Simple Build Tool",
      "Built-By" -> System.getProperty("user.name"),
      "Build-Jdk" -> System.getProperty("java.version"),
      "Specification-Title" -> title,
      "Specification-Vendor" -> "Scalatra",
      "Specification-Version" -> version,
      "Implementation-Title" -> title,
      "Implementation-Version" -> version,
      "Implementation-Vendor-Id" -> vendor,
      "Implementation-Vendor" -> "Scalatra",
      "Implementation-Url" -> "https://github.com/scalatra/sbt-requirejs"
     )
}

homepage := Some(url("https://github.com/scalatra/sbt-requirejs"))

startYear := Some(2012)

licenses := Seq(("MIT", url("http://github.com/scalatra/sbt-requirejs/raw/HEAD/LICENSE")))

pomExtra <<= (pomExtra, name, description) {(pom, name, desc) => pom ++ Group(
  <scm>
    <connection>scm:git:git://github.com/BowlingX/xsbt-wro4j-plugin.git</connection>
    <developerConnection>scm:git:git@github.com:BowlingX/xsbt-wro4j-plugin.git</developerConnection>
    <url>https://github.com/BowlingX/xsbt-wro4j-plugin</url>
  </scm>
  <developers>
    <developer>
      <id>BowlingX</id>
      <name>David Heidrich</name>
      <url>http://www.myself-design.com/</url>
    </developer>
  </developers>
)}
