package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.ContainerAction

class UpdateContainerStateCommand implements Command {

  @CommandLineOption(skipped = true)
  public Optional<String> id

  @CommandLineOption(skipped = true)
  public String action

  UpdateContainerStateCommand(ContainerEntity cntr, ContainerAction action) {
    this.id = cntr.getId()
    this.action = action.name
  }

}
