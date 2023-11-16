package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ImageEntity extends Entity {

  public final TargetPlatform platform

  ImageEntity(String name, String tag, TargetPlatform platform) {
    super(name, tag)
  
    this.platform = platform
  }

}
