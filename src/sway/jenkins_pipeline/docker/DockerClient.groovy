package sway.jenkins_pipeline.docker

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.ContainerResponse
import sway.jenkins_pipeline.docker.model.TargetPlatform
import sway.jenkins_pipeline.docker.strategy.ContainerInspector

class DockerClient {

  private final String executable

  private ContainerEntity container

  DockerClient(String executable) {
    this.executable = executable
  }

  public createContainer(String name) {
    this.container = new ContainerEntity(name)
  }

  public ContainerResponse inspect(ContainerInspector inspector) {
    return inspector.inspect(this.executable, this.container)
  }

  public createImage() {
    
  }

}
