package sway.jenkins_pipeline.docker.query

import groovy.json.JsonSlurper
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class ImageInspectQueryHandler implements QueryHandler<ImageInspectQuery, Map<String, String>> {
  
  private final Executor executor

  private ScriptBuilder builder

  ImageInspectQueryHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Map<String, String> handle(ImageInspectQuery query) {
    this.builder = new ScriptBuilder("images")

    this.builder.addOption(CommandLineOptionUtils.findField(query, "reference"), query)
    this.builder.addOption(CommandLineOptionUtils.findField(query, "format"), query)

    Response response = this.executor.execute(this.builder)
    if (this.executor.getOutString().isEmpty() || response.getCode() != 0) {
      return [:]
    }

    JsonSlurper json = new JsonSlurper()
    return json.parseText(this.executor.getOutString())
  }

}