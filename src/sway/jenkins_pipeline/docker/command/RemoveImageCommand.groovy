package sway.jenkins_pipeline.docker.command

import java.util.Optional
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity

class RemoveImageCommand implements Command {

  @CommandLineOption(skipped = true)
  public String id

  RemoveImageCommand(ImageEntity img) {
    this.id = img.getId().get()
  }

}
