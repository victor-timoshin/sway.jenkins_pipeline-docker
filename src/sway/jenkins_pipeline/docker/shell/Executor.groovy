package sway.jenkins_pipeline.docker.shell

interface Executor {

  Response execute(StringBuilder builder)

  String getOutString()

  String getErrString()

}