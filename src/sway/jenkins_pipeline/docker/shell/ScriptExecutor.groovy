package sway.jenkins_pipeline.docker.shell

import java.lang.reflect.Field
import java.lang.annotation.Annotation
import com.cloudbees.groovy.cps.NonCPS

class ScriptExecutor implements Executor {

  private final String executable

  public StringBuilder outStream = new StringBuilder()
  public StringBuilder errStream = new StringBuilder()

  ScriptExecutor(String executable) {
    this.executable = executable
  }

  @NonCPS
  @Override
  public Response execute(ScriptBuilder builder) {
    Optional<String> workspaceOpt = builder.getWorkspace()
    workspaceOpt.ifPresent {
      if (it.trim().isEmpty()) {
        return
      }

      builder.addStringOption(it, true)
    }

    Process process = "${this.executable}/docker ${builder.toString()}".execute()
    process.waitForProcessOutput(outStream, errStream)
    return new ExecuteResponse(process.exitValue())
  }

  @NonCPS
  @Override
  public String getOutString() {
    return outStream.toString().trim()
  }

  @NonCPS
  @Override
  public String getErrString() {
    return errStream.toString().trim()
  }

}
