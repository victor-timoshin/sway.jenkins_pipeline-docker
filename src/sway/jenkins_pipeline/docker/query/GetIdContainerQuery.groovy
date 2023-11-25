package sway.jenkins_pipeline.docker.query

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class GetIdContainerQuery implements Query {

  @CommandLineOption(name = "name", skipped = true)
  public String name

  GetIdContainerQuery(ContainerEntity cntr) {
    this.name = cntr.name
  }

}
