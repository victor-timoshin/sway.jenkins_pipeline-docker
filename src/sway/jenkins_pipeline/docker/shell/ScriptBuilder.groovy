package sway.jenkins_pipeline.docker.shell

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import java.util.Optional
import java.util.stream.Collectors
import java.util.function.BinaryOperator
import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionDescriptor
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class ScriptBuilder {

  private static final String prefix = "--"

  public final StringBuilder query

  public String workspace

  ScriptBuilder(String command) {
    this.query = new StringBuilder(command)
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace
  }

  @NonCPS
  public void addStrOption(String value) {
    this.query.append(" ").append(value)
  }

  @NonCPS
  private <T> BinaryOperator<String> listMerger(CommandLineOptionDescriptor<T> descriptor) {
    return { String acc, String next -> 
      return acc + " ${prefix}${descriptor.name} ${next}" }
  }

  @NonCPS
  public void addListOption(Field field, Object object) {
    Optional<List<String>> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, ArrayList.class)
    optionDescriptorOpt.ifPresent {
      if (it.data.size() > 0) {
        addStrOption(it.data.stream().reduce("", listMerger(it)).substring(1))
      }
    }
  }

  @NonCPS
  private <T> BinaryOperator<String> mapMerger(CommandLineOptionDescriptor<T> descriptor) {
    return { String acc, Map.Entry next -> 
      return acc + " ${prefix}${descriptor.name} ${next.getKey()}=${next.getValue()}" }
  }

  @NonCPS
  public void addMapOption(Field field, Object object) {
    Optional<Map<String, String>> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, HashMap.class)
    optionDescriptorOpt.ifPresent {
      if (it.data.size() > 0) {
        addStrOption(it.data.entrySet().stream().reduce("", mapMerger(it)).substring(1))
      }
    }
  }

  @NonCPS
  public void addOption(Field field, Object object) {
    Optional<String> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, String.class)
    optionDescriptorOpt.ifPresent {
      addStrOption(prefix + it.name)
      addStrOption(field.getAnnotation(CommandLineOption).workspaceDir() ? "${this.workspace}/${it.data}" : it.data)
    }
  }

  public String toString() {
    return this.query.toString()
  }

}
