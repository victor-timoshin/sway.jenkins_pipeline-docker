package sway.jenkins_pipeline.docker.command

class PushEntityCommandHandler implements CommandHandler<PushEntityCommand, String> {

  private final String executable_

  PushEntityCommandHandler(String executable) {
    this.executable_ = executable
  }

  @Override
  public CommandResult<String> handle(PushEntityCommand command) {
    def outStream = new StringBuilder()
    def errStream = new StringBuilder()

    def process = "${this.executable}/docker push ${command.entity.nameWithTag()}".execute()
    process.waitForProcessOutput(outStream, errStream)

    if (process.exitValue()) {
      return CommandResult.Unsuccessful(errStream.toString().trim())
    }

    return CommandResult.Successful("id", outStream.toString().trim())
  }
  
}

