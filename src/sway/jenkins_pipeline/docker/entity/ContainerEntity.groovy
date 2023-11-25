package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.ContainerState

class ContainerEntity extends Entity {

  private ContainerState state

  ContainerEntity(String name) {
    super(name)
  }

  @NonCPS
  public void setState(ContainerState state) {
    this.state = state
  }

  @NonCPS
  public ContainerState getState() {
    return this.state
  }

}
