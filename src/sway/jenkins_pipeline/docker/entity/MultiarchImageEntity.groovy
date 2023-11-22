package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.TargetPlatform

class MultiarchImageEntity extends Entity {

  public final Map<String, ImageEntity> images = new HashMap<>()

  MultiarchImageEntity(String namespace, String name, String tag) {
    super(name)

    this.setTag(tag)
    this.setNamespace(namespace)
  }

}
