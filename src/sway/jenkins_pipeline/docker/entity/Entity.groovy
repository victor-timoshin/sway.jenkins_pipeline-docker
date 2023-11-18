package sway.jenkins_pipeline.docker.entity

import java.util.Optional
import com.cloudbees.groovy.cps.NonCPS

class Entity {

  public static final String ID_EMPTY_STRING = ""

  public static final String TAG_EMPTY_STRING = ""

  public static final String TAG_SEPARATE = ":"

  private String id

  private final String name

  private final String tag

  Entity(String name, String tag) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name can't be empty")
    }

    this.id = ID_EMPTY_STRING
    this.name = name
    this.tag = tag
  }

  @NonCPS
  public Optional<String> getId() {
    return Optional.ofNullable(this.id)
  }

  @NonCPS
  public void setId(String id) {
    this.id = id
  }

  @NonCPS
  public String getName() {
    return this.name
  }

  @NonCPS
  public Optional<String> getTag() {
    return Optional.ofNullable(this.tag)
  }

  @NonCPS
  public String nameWithTag() {
    return this.name + (this.tag.isEmpty() ? TAG_EMPTY_STRING : "${TAG_SEPARATE}${this.tag}")
  }

}
