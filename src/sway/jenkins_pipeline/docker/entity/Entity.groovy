package sway.jenkins_pipeline.docker.entity

import java.util.Optional
import com.cloudbees.groovy.cps.NonCPS

class Entity {

  public static final String EMPTY_STRING = ""

  public static final String NAMESPACE_SEPARATE = "/"

  public static final String TAG_SEPARATE = ":"

  private final String name

  private String id = EMPTY_STRING

  private String namespace = EMPTY_STRING

  private String tag = EMPTY_STRING

  Entity(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Name can't be empty")
    }

    this.name = name
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
  public void setNamespace(String namespace) {
    this.namespace = namespace
  }

  @NonCPS
  public String getNamespace() {
    return Optional.of(this.namespace).orElse("local")
  }

  @NonCPS
  public String getName() {
    return this.name
  }

  @NonCPS
  public void setTag(String tag) {
    this.tag = tag
  }

  @NonCPS
  public String getTag() {
    return Optional.of(this.tag).orElse("latest")
  }

  @NonCPS
  public String nameWithTag(boolean ns) {
    StringBuilder builder = new StringBuilder(this.name)
    if (ns) {
      builder.insert(0, "${this.getNamespace()}${NAMESPACE_SEPARATE}")
    }

    builder.append("${TAG_SEPARATE}${this.getTag()}")
    return builder.toString()
  }

}
