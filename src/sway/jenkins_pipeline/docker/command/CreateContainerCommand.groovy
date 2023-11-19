package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class CreateContainerCommand implements Command {

  @CommandLineOption(name = "name", required = true)
  public String name

  @CommandLineOption(skipped = true)
  public String reference

  CreateContainerCommand(ContainerEntity entity, String reference) {
    this.name = entity.name
    this.reference = reference
  }

}
