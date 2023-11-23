package sway.jenkins_pipeline.docker.command

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class CreateMultiarchImageCommandHandler implements CommandHandler<CreateMultiarchImageCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  CreateMultiarchImageCommandHandler(Executor executor) {
    this.executor = executor
  }

  @NonCPS
  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(CreateMultiarchImageCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "manifest create")

    this.builder.addStringOption(command.manifestName, true)
    this.builder.addListOption(CommandLineOptionUtils.findField(command, "imgReferenceNames"), command)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

