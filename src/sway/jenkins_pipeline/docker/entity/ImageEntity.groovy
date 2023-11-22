package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.TargetPlatform

class ImageEntity extends Entity {

  public final TargetPlatform platform

  ImageEntity(String namespace, String name, String tag, TargetPlatform platform) {
    super(name)

    this.setTag(tag)
    this.setNamespace(namespace)
  
    this.platform = platform
  }

  @NonCPS
  public String nameWithArchTag(boolean ns) {
    return this.nameWithTag(ns) + "-" + this.platform.arch.alias.replace("/", "")
  }

}
