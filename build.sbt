name := "ML Test"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "io.spray" % "spray-routing" % "1.2.0",
  "io.spray" % "spray-can" % "1.2.0"
)

autoCompilerPlugins := true

addCompilerPlugin("tv.cntt" %% "xgettext" % "1.3")

scalacOptions += "-P:xgettext:I18n.I18n"