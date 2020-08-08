import com.typesafe.sbt.packager.docker.Cmd

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.0.1"

lazy val potato = (project in file("potato"))
  .settings(
    name := "potato",
    libraryDependencies ++= Dependencies.all,
    isSnapshot := false
  )
  .settings(dockerSettings: _*)
  .enablePlugins(JavaAppPackaging, DockerPlugin)

lazy val dockerSettings = Seq(
  version in Docker := "latest",
  dockerExposedPorts in Docker := Seq(8080),
  dockerRepository := None,
  dockerBaseImage := "openjdk:11-jre",
  dockerCommands in Docker ++= Seq(
    Cmd(
      "HEALTHCHECK",
      "--start-period=1m --retries=5 --interval=10s --timeout=3s \\\n  CMD wget localhost:8080/health -q -O - > /dev/null 2>&1 || exit 1"
    )
  )
)
