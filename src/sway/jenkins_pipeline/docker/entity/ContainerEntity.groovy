package sway.jenkins_pipeline.docker.entity

import sway.jenkins_pipeline.docker.model.TargetPlatform

class ContainerEntity extends Entity {

  public final List<TargetPlatform> platforms

  ContainerEntity(String name, List<TargetPlatform> platforms) {
    super(name)

    this.platforms = platforms
  }

}
