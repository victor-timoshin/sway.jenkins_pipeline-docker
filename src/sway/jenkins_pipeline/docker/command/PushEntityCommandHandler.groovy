package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response

class PushEntityCommandHandler implements CommandHandler<PushEntityCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  PushEntityCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(PushEntityCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "push")
    this.builder.addStringOption(command.reference, true)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

