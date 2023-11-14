package sway.jenkins_pipeline.docker.shell

import java.lang.reflect.Field
import java.lang.annotation.Annotation

class ScriptExecutor implements Executor {

  private final String executable

  public StringBuilder outStream = new StringBuilder()
  public StringBuilder errStream = new StringBuilder()

  ScriptExecutor(String executable) {
    this.executable = executable
  }

  @Override
  public Response execute(StringBuilder builder) {
    Process process = "${this.executable}/docker ${builder.toString()}".execute()
    process.waitForProcessOutput(outStream, errStream)
    return new ExecuteResponse(process.exitValue())
  }

  @Override
  public String getOutString() {
    return outStream.toString().trim()
  }

  @Override
  public String getErrString() {
    return errStream.toString().trim()
  }

}
