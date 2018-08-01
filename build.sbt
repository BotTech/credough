enablePlugins(ScalaJSBundlerPlugin)

sourceGenerators in Compile += Def.task {
  val _ = (npmUpdate in Compile).value
  Seq.empty[File]
}
