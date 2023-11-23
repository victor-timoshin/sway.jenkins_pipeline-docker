package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class ContainerInspectQuery implements Query {

  @CommandLineOption(name = "format", required = true)
  public String format = "{\"status\":\"{{.State.Status}}\"}"

  @CommandLineOption(name = "name", skipped = true)
  public String name

  ContainerInspectQuery(ContainerEntity cntr) {
    this.name = cntr.name
  }

}
