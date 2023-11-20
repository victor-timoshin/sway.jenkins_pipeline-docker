package sway.jenkins_pipeline.docker.shell

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import java.util.Optional
import java.util.function.BinaryOperator
import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionDescriptor
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class ScriptBuilder {

  public static final String OPTION_PREFIX = "--"

  public final StringBuilder query

  private String workspace

  ScriptBuilder(String command) {
    this.query = new StringBuilder(command)
  }

  public static ScriptBuilder getInstance(ScriptBuilderable builderable, String command) {
    Optional<ScriptBuilder> builder = builderable.getScriptBuilder()
    builder.ifPresent {
      it.query.delete(0, it.query.length())
      it.query.append(command)
      return it
    }

    return new ScriptBuilder(command)
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace
  }

  @NonCPS
  public Optional<String> getWorkspace() {
    return Optional.ofNullable(this.workspace)
  }

  @NonCPS
  public void addStringOption(String value, boolean isEnabled) {
    if (isEnabled) {
      this.query.append(" ").append(value)
    }
  }

  @NonCPS
  private <T> BinaryOperator<String> listMerger(CommandLineOptionDescriptor<T> descriptor) {
    return { String acc, String next -> 
      return acc + " ${OPTION_PREFIX}${descriptor.name} ${next}" }
  }

  @NonCPS
  public void addListOption(Field field, Object object) {
    Optional<List<String>> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, ArrayList.class)
    optionDescriptorOpt.ifPresent {
      if (it.data.size() > 0) {
        addStringOption(it.data.stream().reduce("", listMerger(it)).substring(1), true)
      }
    }
  }

  @NonCPS
  private <T> BinaryOperator<String> mapMerger(CommandLineOptionDescriptor<T> descriptor) {
    return { String acc, Map.Entry next -> 
      return acc + " ${OPTION_PREFIX}${descriptor.name} ${next.getKey()}=${next.getValue()}" }
  }

  @NonCPS
  public void addMapOption(Field field, Object object) {
    Optional<Map<String, String>> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, HashMap.class)
    optionDescriptorOpt.ifPresent {
      if (it.data.size() > 0) {
        addStringOption(it.data.entrySet().stream().reduce("", mapMerger(it)).substring(1), true)
      }
    }
  }

  @NonCPS
  public void addOption(Field field, Object object) {
    Optional<String> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, String.class)
    optionDescriptorOpt.ifPresent {
      addStringOption(OPTION_PREFIX + it.name, true)
      addStringOption(field.getAnnotation(CommandLineOption).workspaceDir() 
        ? "${getWorkspace().get()}/${it.data}" 
        : it.data, true)
    }
  }

  @NonCPS
  public String toString() {
    return this.query.toString()
  }

}
