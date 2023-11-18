package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response

class CreateContainerCommandHandler implements CommandHandler<CreateContainerCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  CreateContainerCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public CommandResult<String> handle(CreateContainerCommand command) {
    this.builder = new ScriptBuilder("container create")

    this.builder.addStrOption(command.name)
    this.builder.addStrOption(command.interactive)
    this.builder.addStrOption(command.tty)
    this.builder.addStrOption(command.imageUid)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

