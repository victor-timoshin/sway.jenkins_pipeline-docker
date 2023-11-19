package sway.jenkins_pipeline.docker.command

import java.lang.reflect.Field
import sway.jenkins_pipeline.docker.shell.Executor
import sway.jenkins_pipeline.docker.shell.Response
import sway.jenkins_pipeline.docker.shell.ExecuteResponse
import sway.jenkins_pipeline.docker.shell.ScriptBuilder
import sway.jenkins_pipeline.docker.shell.ScriptExecutor
import sway.jenkins_pipeline.docker.annotations.CommandLineOption
import sway.jenkins_pipeline.docker.annotations.CommandLineOptionUtils

class BuildImageCommandHandler implements CommandHandler<BuildImageCommand, String> {
  
  private final Executor executor

  private ScriptBuilder builder

  BuildImageCommandHandler(Executor executor) {
    this.executor = executor
  }

  @Override
  public Optional<ScriptBuilder> getScriptBuilder() {
    return Optional.ofNullable(this.builder)
  }

  @Override
  public CommandResult<String> handle(BuildImageCommand command) {
    this.builder = ScriptBuilder.getInstance(this, "build")
    this.builder.setWorkspace(command.dockerWorkspace)
    this.builder.addStringOption("${ScriptBuilder.OPTION_PREFIX}no-cache", command.noCache)
    this.builder.addStringOption("${ScriptBuilder.OPTION_PREFIX}pull", command.pull)
    this.builder.addStringOption("${ScriptBuilder.OPTION_PREFIX}rm", command.rm)

    CommandLineOptionUtils.findFields(command).each { field -> 
      this.builder.addOption(field, command)
      this.builder.addMapOption(field, command)
    }

    Response response = this.executor.execute(this.builder)
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(this.executor.getErrString())
    }

    return CommandResult.Successful(null, this.executor.getOutString())
  }

}
