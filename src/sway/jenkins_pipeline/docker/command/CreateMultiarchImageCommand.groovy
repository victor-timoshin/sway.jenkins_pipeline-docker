package sway.jenkins_pipeline.docker.command

import java.util.stream.Collectors
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class CreateMultiarchImageCommand implements Command {

  @CommandLineOption(skipped = true)
  public String manifestName

  @CommandLineOption(name = "amend")
  public List<String> imgReferenceNames

  CreateMultiarchImageCommand(MultiarchImageEntity multiarchImage, List<ImageEntity> images) {
    this.manifestName = multiarchImage.nameWithTag(true)
    this.imgReferenceNames = images.stream().map { entity -> entity.nameWithArchTag(true) }.collect(Collectors.toList())
  }

}
