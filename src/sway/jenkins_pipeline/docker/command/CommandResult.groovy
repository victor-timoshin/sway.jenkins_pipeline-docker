package sway.jenkins_pipeline.docker.command

class CommandResult<TCommandResultData> {

  public final Optional<TCommandResultData> data

  public final String message

  public final Boolean succeeded

  CommandResult(TCommandResultData data, String message, Boolean succeeded) {
    this.data = Optional.ofNullable(data)
    this.message = message
    this.succeeded = succeeded
  }

  public static <TCommandResultData> CommandResult<TCommandResultData> Successful(TCommandResultData data, String message) {
    return new CommandResult<TCommandResultData>(data, message, true)
  }

  public static CommandResult Unsuccessful(String message) {
    return new CommandResult(null, message, false)
  }

}
