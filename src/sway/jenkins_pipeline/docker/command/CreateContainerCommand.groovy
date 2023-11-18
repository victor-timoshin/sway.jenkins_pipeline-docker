package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class CreateContainerCommand implements Command {

  @CommandLineOption(name = "name", required = true)
  public String name

  @CommandLineOption(name = "interactive", skipped = true)
  public boolean interactive

  @CommandLineOption(name = "tty", skipped = true)
  public boolean tty

  @CommandLineOption(skipped = true)
  public String imageId

  CreateContainerCommand(ContainerEntity entity, boolean interactive, boolean tty, String imageId) {
    this.name = entity.name
    this.interactive = interactive
    this.tty = tty
    this.imageId = imageId
  }

}
