package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class PushEntityCommand implements Command {

  @CommandLineOption(skipped = true)
  public String imageReferenceName

  PushEntityCommand(ImageEntity image) {
    this.imageReferenceName = entity.nameWithTag(true)
  }
  
}
