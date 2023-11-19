package sway.jenkins_pipeline.docker.command

import java.util.Optional
import com.cloudbees.groovy.cps.NonCPS

class CommandResult<TCommandResultData> {

  private final TCommandResultData data

  public final String message

  public final Boolean succeeded

  CommandResult(TCommandResultData data, String message, Boolean succeeded) {
    this.data = data
    this.message = message
    this.succeeded = succeeded
  }

  public static <TCommandResultData> CommandResult<TCommandResultData> Successful(TCommandResultData data, String message) {
    return new CommandResult<TCommandResultData>(data, message, true)
  }

  public static CommandResult Unsuccessful(String message) {
    return new CommandResult(null, message, false)
  }

  @NonCPS
  public Optional<TCommandResultData> getData() {
    return Optional.ofNullable(this.data)
  }

}
