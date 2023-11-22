package sway.jenkins_pipeline.docker.command

import java.util.stream.Collectors
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class CreateMultiarchCommand implements Command {

  @CommandLineOption(skipped = true)
  public String manifestName

  @CommandLineOption(name = "amend")
  public List<String> imageReferenceNames

  CreateMultiarchCommand(MultiarchImageEntity multiarchImage, List<ImageEntity> images) {
    this.manifestName = multiarchImage.nameWithTag(true)
    this.imageReferenceNames = images.stream().map { entity -> entity.nameWithArchTag(true) }.collect(Collectors.toList())
  }

}
