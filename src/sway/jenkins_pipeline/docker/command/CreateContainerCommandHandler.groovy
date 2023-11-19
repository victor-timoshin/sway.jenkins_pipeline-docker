package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class CreateContainerCommandHandler implements CommandHandler<CreateContainerCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  CreateContainerCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(CreateContainerCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "container create")

    this.builder.addOption(CommandLineOptionUtils.findField(command, "name"), command)
    this.builder.addStringOption(command.imageRefName, true)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

