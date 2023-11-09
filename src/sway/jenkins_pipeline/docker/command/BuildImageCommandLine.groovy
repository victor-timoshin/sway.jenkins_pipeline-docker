package sway.jenkins_pipeline.docker.command

import com.cloudbees.groovy.cps.NonCPS
import java.util.stream.Collectors
import java.util.Optional
import java.util.function.BinaryOperator
import sway.jenkins_pipeline.docker.model.TargetPlatform

class BuildImageCommandLine {

  public Map<String, Object> parameters = new HashMap<String, Object>()

  @NonCPS
  public void addParameter(String name, Object value) {
    Objects.requireNonNull(name, "Parameter name [${name}] cannot be null")

    if (this.parameters.containsKey(name)) {
      if (value instanceof HashMap) {
        value.each { iter -> this.parameters.get(name).get().put(iter.key, iter.value) }
      }
    } else {
      this.parameters.put(name, Optional.ofNullable(value))
    }
  }

  @NonCPS
  public void addReferenceName(String referenceName) {
    this.addParameter("tag", referenceName)
  }

  @NonCPS
  public void addDockerfile(String dockerfile) {
    this.addParameter("file", dockerfile)
  }

  @NonCPS
  public void addTarget(String value) {
    this.addParameter("target", value)
  }

  @NonCPS
  public void addDefine(String name, String value) {
    this.addParameter("build-arg", ["${name.toUpperCase() }" : value] as HashMap)
  }

  private Boolean isHashMap(Object value) {
    return value.isPresent() && value.get() instanceof HashMap
  }

  private String parseMap(String key, Optional<Map<String, String>> values) {
    return values.get().entrySet().stream()
      .reduce("", (BinaryOperator<String>) { String acc, Map.Entry next -> acc + " --${key} ${next.getValue()}" })
      .substring(1)
  }

  private String parse(String key, Optional<String> value) {
    return value.isPresent() 
      ? "--${key} ${value.get()}" 
      : "--${key}"
  }

  public String toString() {
    return this.parameters.keySet().stream().map { key -> 
      Object value = this.parameters.get(key)
      if (this.isHashMap(value)) {
        return this.parseMap(key, value)
      } else {
        return this.parse(key, value)
      }
    }.collect(Collectors.joining(" "))
  }

}
