package sway.jenkins_pipeline.docker.shell

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import java.util.Optional
import java.util.stream.Collectors
import java.util.function.BinaryOperator
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class ScriptBuilder {

  private static final String prefix = "--"

  private final StringBuilder query

  private String workspace

  ScriptBuilder(String command) {
    this.query = new StringBuilder(command)
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace
  }

  public void addOptionGroup(Field field, Object object) {
    Optional<Map<String, String>> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, HashMap.class)
    optionDescriptorOpt.ifPresent {
      // BinaryOperator<String> operator = (String acc, Map.Entry next) { 
      //   return acc + " ${prefix}${it.name} ${next.getKey()}=${next.getValue()}"
      // }

      if (it.data.size() > 0) {
        BinaryOperator<String> operator = (BinaryOperator<String>) { String acc, Map.Entry next -> 
          return acc + " ${prefix}${it.name} ${next.getKey()}=${next.getValue()}" }
        query.append(" ").append(it.data.entrySet().stream().reduce("", operator).substring(1))
      }
    }
  }

  public void addOption(Field field, Object object) {
    Optional<String> optionDescriptorOpt = CommandLineOptionUtils.getDescriptor(field, object, String.class)
    optionDescriptorOpt.ifPresent {
      query.append(" ").append(prefix + it.name)
           .append(" ").append(it.data)
    }
  }

  public String toString() {
    return query.append(this.workspace).toString()
  }

}
