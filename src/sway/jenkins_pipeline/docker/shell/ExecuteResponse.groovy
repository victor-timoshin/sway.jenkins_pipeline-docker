package sway.jenkins_pipeline.docker.shell

class ExecuteResponse implements Response {

  private final int exitCode

  ExecuteResponse(int code) {
    this.exitCode = code
  }

  @Override
  public int getCode() {
    return this.exitCode
  }

}
