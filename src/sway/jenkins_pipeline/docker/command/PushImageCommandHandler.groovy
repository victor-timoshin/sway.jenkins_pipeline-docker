package sway.jenkins_pipeline.docker.command

class PullImageCommandHandler implements CommandHandler<PullImageCommand, String> {

  private final String executable_

  PullImageCommandHandler(String executable) {
    this.executable_ = executable
  }

  @Override
  public CommandResult<String> execute(PullImageCommand command) {
    return CommandResult.Successful("success")
  }
  
}

