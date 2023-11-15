package sway.jenkins_pipeline.docker.shell

interface Executor {

  Response execute(ScriptBuilder builder)

  String getOutString()

  String getErrString()

}
