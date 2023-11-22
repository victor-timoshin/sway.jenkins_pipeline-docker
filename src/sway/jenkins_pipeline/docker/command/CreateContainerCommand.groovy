package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class CreateContainerCommand implements Command {

  @CommandLineOption(name = "name", required = true)
  public String name

  @CommandLineOption(skipped = true)
  public String imageRefName

  CreateContainerCommand(ContainerEntity container, ImageEntity image) {
    this.name = container.name
    this.imageRefName = image.nameWithArchTag(true)
  }

}
