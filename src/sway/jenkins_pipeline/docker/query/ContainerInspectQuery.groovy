package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class ContainerInspectQuery implements Query {

  @CommandLineOption(name = "name", required = true)
  public String name

  @CommandLineOption(name = "format", required = true)
  public String format

  ContainerInspectQuery(String name) {
    this.name = name
    this.format = "{\"status\":\"{{.State.Status}}\"}"
  }

}
