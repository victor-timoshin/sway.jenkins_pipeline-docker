package sway.jenkins_pipeline.docker.command

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonSlurper
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class UpdateContainerStateCommandHandler implements CommandHandler<UpdateContainerStateCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  UpdateContainerStateCommandHandler(Executor executor) {
    this.executor = executor
  }

  @NonCPS
  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(UpdateContainerStateCommand command) {
    this.builder = ScriptBuilder.getInstance(this, command.action)
    this.builder.addStringOption(command.id.get(), true)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

