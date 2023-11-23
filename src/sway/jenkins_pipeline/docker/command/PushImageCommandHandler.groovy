package sway.jenkins_pipeline.docker.command

import com.cloudbees.groovy.cps.NonCPS
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response

class PushImageCommandCommandHandler implements CommandHandler<PushImageCommand, String> {

  private final Executor executor

  private ScriptBuilder builder

  PushImageCommandHandler(Executor executor) {
    this.executor = executor
  }

  @NonCPS
  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(PushImageCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "tag")
    this.builder.addStringOption("${command.imgNamespace}/${command.imgReferenceName}", true)
    this.builder.addStringOption("${command.regNamespace}/${command.imgReferenceName}", true)

    this.builder = ScriptBuilder.getInstance(this, "push")
    this.builder.addStringOption("${command.regNamespace}/${command.imgReferenceName}", true)

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }
  
}

