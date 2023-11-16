package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class CreateMultiarchCommand implements Command {

  @CommandLineOption(skipped = true)
  public String reference

  @CommandLineOption(name = "amend")
  public List<String> images

  CreateMultiarchCommand(MultiarchImageEntity entity, List<String> images) {
    this.reference = entity.nameWithTag()
    this.images = images
  }

}
