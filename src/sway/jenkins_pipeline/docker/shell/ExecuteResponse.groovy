package sway.jenkins_pipeline.docker.shell

class ExecuteResponse implements Response {

  public final int code

  ExecuteResponse(int code) {
    this.code = code
  }

  @Override
  public int getCode() {
    return this.code
  }

}
