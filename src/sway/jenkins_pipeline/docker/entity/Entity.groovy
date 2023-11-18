package sway.jenkins_pipeline.docker.entity

import java.util.Optional
import com.cloudbees.groovy.cps.NonCPS

class Entity {

  public static final String TAG_EMPTY_STRING = ""

  public static final String TAG_SEPARATE = ":"

  public final String name

  public Optional<String> tag = Optional.empty()

  public Optional<String> uid = Optional.empty()

  Entity(String name, String tag) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name can't be empty")
    }

    this.name = name
    this.tag = Optional.ofNullable(tag.trim().isEmpty() ? null : tag)
  }

  @NonCPS
  public String nameWithTag() {
    return this.name + (tag.isPresent() ? "${TAG_SEPARATE}${this.tag.get()}" : TAG_EMPTY_STRING)
  }

}
