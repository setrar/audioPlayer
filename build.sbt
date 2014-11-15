name := "scalaplayer"

organization  := "club.phantazia"

version       := "0.1"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "com.googlecode.soundlibs" % "mp3spi"  % "1.9.5-1",
    "com.googlecode.soundlibs" % "vorbisspi"  % "1.0.3-1"
  )
}

libraryDependencies ++= {
  Seq(
    "org.specs2" %% "specs2" % "2.4" % "test",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "junit" % "junit" % "4.10" % "test"
  )
}

seq(Revolver.settings: _*)

