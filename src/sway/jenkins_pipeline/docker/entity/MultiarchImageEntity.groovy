package sway.jenkins_pipeline.docker.entity

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.model.TargetPlatform

class MultiarchImageEntity extends Entity {

  public final Map<String, ImageEntity> images = new HashMap<>()

  MultiarchImageEntity(String name, String tag) {
    super(name, tag)
  }

  // public void createImage(String name, String tag, TargetPlatform platform) {
  //   images.put(name, new ImageEntity(name, tag, platform));
  // }

}
