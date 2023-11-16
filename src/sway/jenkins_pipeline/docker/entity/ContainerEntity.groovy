package sway.jenkins_pipeline.docker.entity

import sway.jenkins_pipeline.docker.model.TargetPlatform

class ContainerEntity extends Entity {

  private static final String TAG_EMPTY_STRING = ""

  public final List<TargetPlatform> platforms

  ContainerEntity(String name, List<TargetPlatform> platforms) {
    super(name, TAG_EMPTY_STRING)

    this.platforms = platforms
  }

}
