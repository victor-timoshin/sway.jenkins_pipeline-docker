package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class PushMultiarchImageCommand implements Command {

  @CommandLineOption(skipped = true)
  public String imgReferenceName

  @CommandLineOption(skipped = true)
  public String regNamespace

  PushMultiarchImageCommand(MultiarchImageEntity img, String regNamespace) {
    this.imgReferenceName = img.nameWithArchTag(false)
    this.regNamespace = regNamespace
  }

}
