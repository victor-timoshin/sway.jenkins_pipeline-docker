package sway.jenkins_pipeline.docker.command

class BuildImageCommandHandler implements CommandHandler<BuildImageCommand, String> {
  
  private final String executable

  BuildImageCommandHandler(String executable) {
    this.executable = executable
  }

  @Override
  public CommandResult<String> execute(BuildImageCommand command) {
    def outStream = new StringBuilder()
    def errStream = new StringBuilder()

    def process = "${this.executable}/docker build ${command.line.toString()} ${command.workspace}".execute()
    process.waitForProcessOutput(outStream, errStream)

    // println "out > ${outStream}"
    // println "err > ${errStream}"

    if (process.exitValue()) {
      return CommandResult.Unsuccessful(errStream.toString().trim())
    }

    return CommandResult.Successful("id", outStream.toString().trim())
  }

}
