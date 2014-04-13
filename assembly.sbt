import sbtassembly.Plugin.AssemblyKeys._

assemblySettings

// Could add a merge strategy if something doesn't work when building the fat jar.

mainClass in assembly := Some("com.ivan.nikolov.JettyStart")

jarName in assembly := { s"${name.value}_${scalaVersion.value}-${version.value}-assembly.jar" }

artifact in (Compile, assembly) ~= {art =>
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)