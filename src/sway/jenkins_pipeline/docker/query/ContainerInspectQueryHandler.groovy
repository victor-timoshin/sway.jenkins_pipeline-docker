package sway.jenkins_pipeline.docker.query

import groovy.json.JsonSlurper
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class ContainerInspectQueryHandler implements QueryHandler<ContainerInspectQuery, Map<String, String>> {
  
  private final Executor executor

  private ScriptBuilder builder

  ContainerInspectQueryHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public Map<String, String> handle(ContainerInspectQuery query) {
    this.builder = ScriptBuilder.getInstance(this, "inspect")

    this.builder.addOption(CommandLineOptionUtils.findField(query, "name"), query)
    this.builder.addOption(CommandLineOptionUtils.findField(query, "format"), query)

    Response response = this.executor.execute(this.builder)
    if (this.executor.getOutString().isEmpty() || response.getCode() != 0) {
      return [:]
    }

    JsonSlurper json = new JsonSlurper()
    return json.parseText(this.executor.getOutString())
  }

}
