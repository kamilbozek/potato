ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.0.1"

lazy val potato = (project in file("potato"))
  .settings(
    name := "potato",
    libraryDependencies ++= Dependencies.all,
    isSnapshot := true
  )
