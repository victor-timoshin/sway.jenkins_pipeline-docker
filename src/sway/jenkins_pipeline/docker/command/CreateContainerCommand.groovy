package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class CreateContainerCommand implements Command {

  @CommandLineOption(name = "name", required = true)
  public String name

  @CommandLineOption(skipped = true)
  public String imgReferenceName

  CreateContainerCommand(ContainerEntity cntr, ImageEntity img) {
    this.name = cntr.name
    this.imgReferenceName = img.nameWithArchTag(true)
  }

}
