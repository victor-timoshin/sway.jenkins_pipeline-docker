package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class CreateMultiarchCommandHandler implements CommandHandler<CreateMultiarchCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  CreateMultiarchCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(CreateMultiarchCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "manifest create")

    this.builder.addStringOption(command.name, true)
    this.builder.addListOption(CommandLineOptionUtils.findField(command, "imageRefNames"), command)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

