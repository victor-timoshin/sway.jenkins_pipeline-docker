package sway.jenkins_pipeline.docker.entity

import java.util.Optional
import com.cloudbees.groovy.cps.NonCPS

class Entity {

  public final String name

  public String tag

  public Optional<String> uid

  Entity(String name, String tag) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name can't be empty")
    }

    this.name = name
    this.tag = tag
    this.uid = Optional.empty()
  }

  @NonCPS
  public String nameWithTag() {
    return "${this.name}:${this.tag}"
  }

}
