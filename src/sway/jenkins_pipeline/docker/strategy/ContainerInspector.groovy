package sway.jenkins_pipeline.docker.strategy

import sway.jenkins_pipeline.docker.entity.ContainerEntity
import sway.jenkins_pipeline.docker.model.ContainerResponse

interface ContainerInspector {

  ContainerResponse inspect(String dockerPath, ContainerEntity cntr)

}
