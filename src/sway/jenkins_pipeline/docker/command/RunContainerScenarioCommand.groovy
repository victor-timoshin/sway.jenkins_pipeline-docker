package sway.jenkins_pipeline.docker.command

import java.util.Optional
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.entity.ImageEntity
import sway.jenkins_pipeline.docker.entity.ContainerEntity

class RunContainerScenarioCommand implements Command {

  @CommandLineOption(skipped = true)
  public Optional<String> id

  @CommandLineOption(skipped = true)
  public String scenario

  RunContainerScenarioCommand(ContainerEntity cntr, String scenario) {
    this.id = cntr.getId()
    this.scenario = scenario
  }

}
