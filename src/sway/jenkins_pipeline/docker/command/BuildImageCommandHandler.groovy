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

    println "${this.executable}/docker build ${command.line.toString()} ."
    def proc = "${this.executable}/docker build ${command.line.toString()} .".execute()
    proc.waitForProcessOutput(outStream, errStream)

    // println "out > ${outStream}"
    // println "err > ${errStream}"

    return CommandResult.Successful("id", "success")
  }

}
