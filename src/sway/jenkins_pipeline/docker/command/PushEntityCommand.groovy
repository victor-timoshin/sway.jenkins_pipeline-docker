package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class PushEntityCommand implements Command {

  @CommandLineOption(skipped = true)
  public String reference

  PushEntityCommand(ImageEntity entity) {
    this.reference = entity.nameWithTag()
  }
  
}
