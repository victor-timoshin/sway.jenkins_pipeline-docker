package sway.jenkins_pipeline.docker.query

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class GetIdContainerQueryHandler implements QueryHandler<GetIdContainerQuery, Optional<String>> {
  
  private final Executor executor

  private ScriptBuilder builder

  GetIdContainerQueryHandler(Executor executor) {
    this.executor = executor
  }

  @NonCPS
  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @NonCPS
  @Override
  public Optional<String> handle(GetIdContainerQuery query) {
    this.builder = ScriptBuilder.getInstance(this, "ps")
    this.builder.addStringOption("--quiet", true)
    this.builder.addStringOption("--all", true)
    this.builder.addStringOption("--filter name=${query.name}", true)
    println this.builder.toString()
    Response response = this.executor.execute(this.builder)
    if (this.executor.getOutString().isEmpty() || response.getCode() != 0) {
      return Optional.empty()
    }

    return Optional.of(this.executor.getOutString())
  }

}
