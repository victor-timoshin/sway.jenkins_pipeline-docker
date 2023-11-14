package sway.jenkins_pipeline.docker.command

class CreateMultiarchCommandHandler implements CommandHandler<CreateMultiarchCommand, String> {

  private final String executable

  CreateMultiarchCommandHandler(String executable) {
    this.executable = executable
  }

  @Override
  public CommandResult<String> handle(CreateMultiarchCommand command) {
    def outStream = new StringBuilder()
    def errStream = new StringBuilder()

      // docker manifest create \
      //           sway/MODULE_GAPI_GL_IMAGE_NAME:MODULE_GAPI_GL_IMAGE_TAG \
      //   --amend sway/MODULE_GAPI_GL_IMAGE_NAME:MODULE_GAPI_GL_IMAGE_TAG-amd64 \
      //   --amend sway/MODULE_GAPI_GL_IMAGE_NAME:MODULE_GAPI_GL_IMAGE_TAG-arm32v7 \
      //   --amend sway/MODULE_GAPI_GL_IMAGE_NAME:MODULE_GAPI_GL_IMAGE_TAG-arm64v8

    def process = "${this.executable}/docker manifest create ${command.entity.nameWithTag()}".execute()
    process.waitForProcessOutput(outStream, errStream)

    if (process.exitValue()) {
      return CommandResult.Unsuccessful(errStream.toString().trim())
    }

    return CommandResult.Successful("id", outStream.toString().trim())
  }
  
}

