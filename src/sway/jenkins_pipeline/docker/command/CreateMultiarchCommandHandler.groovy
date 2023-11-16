package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response

class CreateMultiarchCommandHandler implements CommandHandler<CreateMultiarchCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  CreateMultiarchCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public CommandResult<String> handle(CreateMultiarchCommand command) {
    this.builder = new ScriptBuilder("manifest create")

    this.builder.query.append(" ").append(command.reference)
    this.builder.addListOption(CommandLineOptionUtils.findField(command, "amend"), command)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

