package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.entity.Entity
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class PushImageCommand implements Command {

  @CommandLineOption(skipped = true)
  public String imgReferenceName

  @CommandLineOption(skipped = true)
  public String imgNamespace

  @CommandLineOption(skipped = true)
  public String regNamespace

  PushImageCommand(ImageEntity img, String regNamespace) {
    this.imgReferenceName = img.nameWithArchTag(false)
    this.imgNamespace = img.getNamespace()
    this.regNamespace = regNamespace
  }

}
