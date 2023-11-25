package sway.jenkins_pipeline.docker.command

import java.util.Optional
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class RemoveContainerCommand implements Command {

  @CommandLineOption(skipped = true)
  public Optional<String> id

  RemoveContainerCommand(ContainerEntity cntr) {
    this.id = cntr.getId()
  }

}
