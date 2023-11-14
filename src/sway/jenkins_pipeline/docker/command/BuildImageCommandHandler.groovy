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
  public CommandResult<String> handle(BuildImageCommand command) {
    this.builder = new ScriptBuilder("build")
    this.builder.setWorkspace(command.dockerWorkspace)
    this.builder.addOption(CommandLineOptionUtils.findField(command, "reference"), command)
    this.builder.addOption(CommandLineOptionUtils.findField(command, "target"), command)

    CommandLineOptionUtils.findFields(command).each { field -> 
      this.builder.addOptionGroup(field, command)
    }

    Response response = executor.execute()
    if (response.getCode() != 0) {
      return CommandResult.Unsuccessful(executor.getErrString())
    }

    return CommandResult.Successful("id", executor.getOutString())
  }

}
