package sway.jenkins_pipeline.docker.command

interface CommandHandler<TCommand, TCommandResultData> {

  CommandResult<TCommandResultData> execute(TCommand command)
  
}
