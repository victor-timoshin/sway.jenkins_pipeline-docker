package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ImageEntity extends Entity {

  public final String tag

  public final TargetPlatform platform

  ImageEntity(String name, String tag, TargetPlatform platform) {
    super(name)

    this.tag = tag
    this.platform = platform
  }

  @NonCPS
  public String nameWithTag() { return "${this.name}:${this.tag}" }

}
