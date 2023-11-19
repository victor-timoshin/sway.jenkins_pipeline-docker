package sway.jenkins_pipeline.docker.command

import sway.jenkins_pipeline.docker.shell.ScriptBuilderable

interface CommandHandler<TCommand, TCommandResultData> extends ScriptBuilderable {

  CommandResult<TCommandResultData> handle(TCommand command)
  
}
