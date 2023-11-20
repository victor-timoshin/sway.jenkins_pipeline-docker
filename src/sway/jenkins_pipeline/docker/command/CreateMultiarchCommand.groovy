package sway.jenkins_pipeline.docker.command

import java.util.stream.Collectors
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.MultiarchImageEntity
import sway.jenkins_pipeline.docker.annotations.CommandLineOption

class CreateMultiarchCommand implements Command {

  @CommandLineOption(skipped = true)
  public String name

  @CommandLineOption(name = "amend")
  public List<String> imageRefNames

  CreateMultiarchCommand(MultiarchImageEntity multiarchImage, List<ImageEntity> images) {
    this.name = multiarchImage.nameWithTag()
    this.imageRefNames = images.stream().map { entity -> entity.getReferenceName() }.collect(Collectors.toList())
  }

}
