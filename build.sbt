name := "Lift 3.0 starter template"

version := "1.0.0"
organization := "com.myco"
scalaVersion := "2.12.6"

resolvers ++= Seq(
  "snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
  "releases"      at "https://oss.sonatype.org/content/repositories/releases")

scalacOptions ++= Seq("-deprecation", "-unchecked")

enablePlugins(JettyPlugin)

unmanagedResourceDirectories in Test += baseDirectory.value / "src/main/webapp"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "3.2.0"
  Seq(
    "net.liftweb"       %% "lift-webkit"            % liftVersion,
    "ch.qos.logback"    % "logback-classic"         % "1.2.3",
    "javax.servlet"     % "javax.servlet-api"       % "3.0.1"  % "provided",
    //    "net.liftweb"       %% "lift-mongodb-record"    % liftVersion,
    "org.mongodb"       %% "casbah"                 % "3.1.1",
    "com.typesafe.akka" %% "akka-http"              % "10.1.7",
    "com.typesafe.akka" %% "akka-stream" % "2.5.21",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
  )
}


//val AkkaVersion = "2.6.1"
//val AkkaHttpVersion = "10.2.1"
//
//libraryDependencies ++= Seq(
//  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
//  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
//)




/*
* error : Scanning from multiple location resolved.
* there was 2 depedancy of mongo was added was the main cause of the issue.
* */
//libraryDependencies += {
//  "net.liftweb" %% "lift-mongodb-record" % "3.3.0"
//}

/*
* "com.typesafe.akka" %% "akka-http"              % "10.1.11",
    "com.typesafe.akka" %% "akka-actor" % "2.5.9",
    "com.typesafe.akka" %% "akka-stream" % "2.5.9"
*
* */



scalacOptions in Test ++= Seq("-Yrangepos")


